package com.news.app.interactor

import com.news.app.utils.FakeDataProvider
import com.news.app.utils.FakeNewsRepositoryError
import com.news.app.utils.FakeNewsRepositorySuccess
import org.junit.Test

class ObserveArticlesInteractorTest {

    @Test
    fun observeArticles_success() {
        val observeArticles = ObserveArticlesInteractor(FakeNewsRepositorySuccess())
        observeArticles().test()
            .assertNoErrors()
            .assertNotTerminated()
            .assertValue(FakeDataProvider.mockArticleItems())
            .dispose()
    }

    @Test
    fun observeArticles_error() {
        val observeArticles = ObserveArticlesInteractor(FakeNewsRepositoryError())
        observeArticles().test()
            .assertError(Throwable::class.java)
            .assertTerminated()
            .assertNoValues()
            .dispose()
    }
}