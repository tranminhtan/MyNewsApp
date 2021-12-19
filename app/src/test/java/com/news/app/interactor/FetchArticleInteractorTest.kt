package com.news.app.interactor

import com.news.app.TestBase
import com.news.app.utils.FakeDataProvider
import com.news.app.utils.FakeNewsRepositoryError
import com.news.app.utils.FakeNewsRepositorySuccess
import org.junit.Test

class FetchArticleInteractorTest : TestBase() {

    @Test
    fun fetchArticles_success() {
        val fetchArticle = FetchArticleInteractor(FakeNewsRepositorySuccess())
        fetchArticle(100)
            .test()
            .assertNoErrors()
            .assertTerminated()
            .assertValue(FakeDataProvider.mockArticleItems()[0])
            .dispose()
    }

    @Test
    fun fetchArticles_error() {
        val fetchArticle = FetchArticleInteractor(FakeNewsRepositoryError())
        fetchArticle(100)
            .test()
            .assertError(Throwable::class.java)
            .assertTerminated()
            .assertNoValues()
            .dispose()
    }
}