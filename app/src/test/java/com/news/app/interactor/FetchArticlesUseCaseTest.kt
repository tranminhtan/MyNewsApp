package com.news.app.interactor

import com.news.app.ui.list.support.SupportedCountry
import com.news.app.utils.FakeDataProvider
import com.news.app.utils.FakeNewsRepositoryError
import com.news.app.utils.FakeNewsRepositorySuccess
import org.junit.Test

class FetchArticlesUseCaseTest {

    @Test
    fun fetchArticles_error_assertError() {
        val useCase = ObserveArticlesInteractor(FakeNewsRepositoryError(), SupportedCountry())
        useCase.observeTopArticles().test()
            .assertError(Throwable::class.java)
            .assertTerminated()
            .assertNoValues()
            .dispose()
    }

    @Test
    fun fetchArticles_success_assertThat() {
        val useCase = ObserveArticlesInteractor(FakeNewsRepositorySuccess(), SupportedCountry())
        useCase.observeTopArticles().test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(FakeDataProvider.mockArticleItems())
            .dispose()
    }
}