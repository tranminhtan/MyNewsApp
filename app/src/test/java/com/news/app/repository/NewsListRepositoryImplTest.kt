package com.news.app.repository

import com.news.app.TestBase
import com.news.app.db.NewsDao
import com.news.app.model.Article
import com.news.app.model.Status
import com.news.app.model.TopHeadlinesResponse
import com.news.app.network.NewsService
import com.news.app.provider.MockSchedulerProvider
import com.news.app.utils.FakeDataProvider
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify

class NewsListRepositoryImplTest : TestBase() {
    @Mock
    lateinit var newsService: NewsService

    @Mock
    lateinit var newsDao: NewsDao

    private val schedulersProvider = MockSchedulerProvider()

    private val articles = FakeDataProvider.mockArticles()

    private lateinit var repository: NewsListRepositoryImpl

    override fun setup() {
        super.setup()
        repository = NewsListRepositoryImpl(newsDao, newsService, schedulersProvider)
    }

    @Test
    fun fetchArticleById_success() {
        given(newsDao.getArticleById(Mockito.anyInt()))
            .willReturn(Single.just(articles[0]))

        repository.fetchArticleById(100)
            .test()
            .assertNoErrors()
            .assertTerminated()
            .assertValue(articles[0])
            .dispose()
    }

    @Test
    fun observeTopArticles_success() {
        val subject = PublishSubject.create<List<Article>>()
        given(newsDao.getArticles())
            .willReturn(subject)

        val observer = repository.observeTopArticles()
            .test()
            .assertNotTerminated()
            .assertNoErrors()
            .assertNoValues()

        subject.onNext(emptyList())
        observer.assertNoValues()

        subject.onNext(articles)
        observer.assertValue(articles)
            .dispose()
    }

    @Test
    fun fetchTopArticles_success() {
        Mockito.doNothing().`when`(newsDao).updateData(articles)

        given(newsService.getTopHeadlines("gb"))
            .willReturn(Single.just(TopHeadlinesResponse(Status.Ok, articles)))

        repository.fetchTopArticles()
            .test()
            .assertNoErrors()
            .assertNoValues()
            .assertComplete()
            .dispose()

        verify(newsDao).updateData(articles)
    }
}