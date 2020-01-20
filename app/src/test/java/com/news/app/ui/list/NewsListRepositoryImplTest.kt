package com.news.app.ui.list

import com.news.app.BuildConfig
import com.news.app.TestBase
import com.news.app.model.Article
import com.news.app.model.Status
import com.news.app.model.TopHeadlinesResponse
import com.news.app.network.NewsService
import com.news.app.utils.FakeDataBase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito

class NewsListRepositoryImplTest : TestBase() {
    @Mock
    lateinit var service: NewsService

    private val dataBase = FakeDataBase()
    private val countryCode = "sg"
    private val articles = arrayListOf(
        Article(1, "", "", null, "", "", "", ""),
        Article(2, "", "", null, "", "", "", "")
    )

    private lateinit var repository: NewsListRepositoryImpl

    override fun setup() {
        super.setup()
        repository = NewsListRepositoryImpl(dataBase, service)
    }


    /**
     * The observeArticles() should not be terminated, we can achieve that with a RxJava Subject but keep it simple for now
     */
    @Test
    fun observeArticles_empty_emmitNothing() {
        given(dataBase.newsDao().getArticles()).willReturn(Observable.just(emptyList()))

        repository.observeArticles().test()
            .assertNoErrors()
            .assertNoValues()
            .assertTerminated()
            .dispose()
    }

    @Test
    fun observeArticles_hasArticles_emmitIt() {
        given(dataBase.newsDao().getArticles()).willReturn(Observable.just(articles))

        repository.observeArticles().test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(articles)
            .assertTerminated()
            .dispose()
    }

    @Test
    fun fetchArticles_emptyData_emmitNothing() {
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.just(TopHeadlinesResponse(Status.Ok, emptyList())))

        repository.fetchArticles(countryCode).test()
            .assertNoValues()
            .assertNoErrors()
            .assertTerminated()
            .dispose()
        Mockito.verifyNoMoreInteractions(dataBase.newsDao())
    }

    @Test
    fun fetchArticles_statusError_emmitNothing() {
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.just(TopHeadlinesResponse(Status.Error, emptyList())))

        repository.fetchArticles(countryCode).test()
            .assertNoValues()
            .assertNoErrors()
            .assertTerminated()
            .dispose()
        Mockito.verifyNoMoreInteractions(dataBase.newsDao())
    }

    @Test
    fun fetchArticles_otherError_complete() {
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.error(Throwable()))

        repository.fetchArticles(countryCode).test()
            .assertNoValues()
            .assertNoErrors()
            .assertTerminated()
            .dispose()
        Mockito.verifyNoMoreInteractions(dataBase.newsDao())
    }

    @Test
    fun fetchArticles_succeed_emmitData() {
        given(service.getTopHeadlines(BuildConfig.API_KEY, countryCode))
            .willReturn(Single.just(TopHeadlinesResponse(Status.Ok, articles)))
        given(dataBase.newsDao().deleteAll()).willReturn(Completable.complete())

        repository.fetchArticles(countryCode).test()
            .assertNoValues()
            .assertNoErrors()
            .assertTerminated()
            .dispose()
        Mockito.verify(dataBase.newsDao()).deleteAll()
        Mockito.verify(dataBase.newsDao()).insertAll(articles)
    }
}