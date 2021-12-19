package com.news.app.ui.list

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.app.TestBase
import com.news.app.interactor.FetchTopArticlesInteractor
import com.news.app.interactor.ObserveArticlesInteractor
import com.news.app.utils.FakeDataProvider
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
    lateinit var observeArticlesInteractor: ObserveArticlesInteractor

    @Mock
    lateinit var fetchTopArticlesInteractor: FetchTopArticlesInteractor
    private lateinit var viewModel: NewsListViewModel

    override fun setup() {
        super.setup()
        viewModel = NewsListViewModel(observeArticlesInteractor, fetchTopArticlesInteractor)
        assertDefaultValues()
    }

    @Test
    fun observeTopArticles_success() {
        given(observeArticlesInteractor()).willReturn(Observable.just(FakeDataProvider.mockArticleItems()))

        viewModel.observeTopArticles()

        Assert.assertEquals(FakeDataProvider.mockArticleItems(), viewModel.articles.value)
        Assert.assertEquals(View.GONE, viewModel.shimmerVisibility.value)
    }

    @Test
    fun observeTopArticles_error() {
        given(observeArticlesInteractor()).willReturn(Observable.error(Throwable()))

        viewModel.observeTopArticles()

        assertDefaultValues()
    }

    private fun assertDefaultValues() {
        Assert.assertEquals(View.VISIBLE, viewModel.shimmerVisibility.value)
        Assert.assertNull(viewModel.articles.value)
    }

}