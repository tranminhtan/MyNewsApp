package com.news.app.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.app.TestBase
import com.news.app.interactor.ObserveArticlesInteractor
import com.news.app.utils.FakeDataProvider
import io.reactivex.Flowable
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
    lateinit var observeInteractor: ObserveArticlesInteractor
    private lateinit var viewModel: NewsListViewModel

    override fun setup() {
        super.setup()
        viewModel = NewsListViewModel(observeInteractor)
    }

    @Test
    fun fetchArticles_returnError_assertArticlesNull() {
        given(observeInteractor.observeTopArticles()).willReturn(Flowable.error(Throwable()))

        viewModel.fetchTopArticles()

        Assert.assertNull(viewModel.articles.value)
    }

    @Test
    fun fetchArticles_returnEmpty_assertArticlesEmpty() {
        given(observeInteractor.observeTopArticles()).willReturn(Flowable.just(emptyList()))

        viewModel.fetchTopArticles()

        Assert.assertTrue(viewModel.articles.value!!.isEmpty())
    }

    @Test
    fun fetchArticles_returnArticles_assertEqualThat() {
        given(observeInteractor.observeTopArticles()).willReturn(Flowable.just(FakeDataProvider.mockArticleItems()))

        viewModel.fetchTopArticles()

        Assert.assertEquals(FakeDataProvider.mockArticleItems(), viewModel.articles.value)
    }


}