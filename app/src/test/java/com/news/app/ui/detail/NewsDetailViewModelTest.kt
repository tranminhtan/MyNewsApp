package com.news.app.ui.detail

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.app.TestBase
import com.news.app.interactor.FetchArticleInteractor
import com.news.app.utils.FakeDataProvider
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock

class NewsDetailViewModelTest : TestBase() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchArticleInteractor: FetchArticleInteractor
    private lateinit var viewModel: NewsDetailViewModel

    override fun setup() {
        super.setup()
        viewModel = NewsDetailViewModel(fetchArticleInteractor)

        Assert.assertNull(viewModel.article.value)
        Assert.assertEquals(View.GONE, viewModel.contentVisibility.value)
    }

    @Test
    fun fetchArticle_success() {
        val id = 123
        given(fetchArticleInteractor(id))
            .willReturn(Single.just(FakeDataProvider.mockArticleItems()[0]))

        viewModel.fetchArticle(id)

        Assert.assertEquals(FakeDataProvider.mockArticleItems()[0], viewModel.article.value)
        Assert.assertEquals(View.VISIBLE, viewModel.contentVisibility.value)
    }

    @Test
    fun fetchArticle_error() {
        val id = 123
        given(fetchArticleInteractor(id))
            .willReturn(Single.error(Throwable()))

        viewModel.fetchArticle(id)

        Assert.assertNull(viewModel.article.value)
        Assert.assertEquals(View.GONE, viewModel.contentVisibility.value)
    }
}