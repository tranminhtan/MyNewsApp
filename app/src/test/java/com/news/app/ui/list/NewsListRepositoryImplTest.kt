package com.news.app.ui.list

import com.news.app.BuildConfig
import com.news.app.TestBase
import com.news.app.base.MockSchedulerProvider
import com.news.app.model.Status
import com.news.app.model.TopHeadlinesResponse
import com.news.app.network.NewsService
import com.news.app.utils.FakeDataBase
import com.news.app.utils.FakeDataProvider
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

class NewsListRepositoryImplTest : TestBase() {
    @Mock
    lateinit var service: NewsService
    private val dataBase = FakeDataBase()
    private val schedulersProvider = MockSchedulerProvider()

    private val countryCode = "sg"
    private val articles = FakeDataProvider.mockArticles()

    private lateinit var repository: NewsListRepositoryImpl

    override fun setup() {
        super.setup()
        repository = NewsListRepositoryImpl(dataBase, service, schedulersProvider)

        given(dataBase.newsDao().insertAll(articles))
            .willReturn(Completable.complete())
        given(dataBase.newsDao().deleteAll())
            .willReturn(Completable.complete())
    }

    @Test
    fun fetchArticles_bothReturnError_assertNoValues() {
        given(dataBase.newsDao().getArticles())
            .willReturn(Single.error(Throwable()))
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.error(Throwable()))

        repository.fetchArticles(countryCode)
            .test()
            .assertNoErrors()
            .assertNoValues()
            .assertTerminated()
            .dispose()

        verify(dataBase.newsDao(), never()).deleteAll()
        verify(dataBase.newsDao(), never()).insertAll(articles)
    }

    @Test
    fun fetchArticles_bothReturnData_assertValuesTwice() {
        given(dataBase.newsDao().getArticles())
            .willReturn(Single.just(articles))
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.just(TopHeadlinesResponse(Status.Ok, articles)))

        repository.fetchArticles(countryCode)
            .test()
            .assertNoErrors()
            .assertValueCount(2)
            .assertValues(articles, articles)
            .assertComplete()
            .dispose()

        verify(dataBase.newsDao()).deleteAll()
        verify(dataBase.newsDao()).insertAll(articles)
    }

    @Test
    fun fetchArticles_fetchFromDBSuccess_fetchFromServerError_assertValueFromDB() {
        given(dataBase.newsDao().getArticles())
            .willReturn(Single.just(articles))
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.error(Throwable()))

        repository.fetchArticles(countryCode)
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(articles)
            .assertComplete()
            .dispose()

        verify(dataBase.newsDao(), never()).deleteAll()
        verify(dataBase.newsDao(), never()).insertAll(articles)
    }

    @Test
    fun fetchArticles_fetchFromDBError_fetchFromServerSuccess_assertValueFromServer() {
        given(dataBase.newsDao().getArticles())
            .willReturn(Single.error(Throwable()))
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.just(TopHeadlinesResponse(Status.Ok, articles)))

        repository.fetchArticles(countryCode)
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValues(articles)
            .assertComplete()
            .dispose()

        verify(dataBase.newsDao()).deleteAll()
        verify(dataBase.newsDao()).insertAll(articles)
    }
}