package com.news.app.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.app.TestBase
import com.news.app.ui.list.support.ArticleItem
import com.news.app.ui.list.usecase.FetchArticlesUseCase
import com.news.app.ui.list.usecase.ObserveArticlesUseCase
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock

class NewsListViewModelTest : TestBase() {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchUseCase: FetchArticlesUseCase
    @Mock
    lateinit var observeUseCase: ObserveArticlesUseCase

    private val articles = arrayListOf(
        ArticleItem("title1", "", "", "", "", ""),
        ArticleItem("title2", "", "", "", "", "")
    )
    private lateinit var viewModel: NewsListViewModel

    override fun setup() {
        super.setup()
        viewModel = NewsListViewModel(fetchUseCase, observeUseCase)
    }

    @Test
    fun fetchArticles() {
        given(fetchUseCase.fetchArticles()).willReturn(Completable.complete())
        viewModel.fetchArticles().test()
            .assertNoValues()
            .assertTerminated()
            .assertNoValues()
            .dispose()
    }

    /**
     * The observeArticles() should not be terminated, we can achieve that with a RxJava Subject but keep it simple for now
     */
    @Test
    fun observeArticles() {
        Assert.assertNull(viewModel.articles.value)
        given(observeUseCase.observe()).willReturn(Observable.just(articles))

        viewModel.observeArticles()
            .test()
            .assertNoErrors()
            .assertNoValues()
            .assertTerminated()
            .dispose()

        Assert.assertEquals(articles, viewModel.articles.value)
    }
}