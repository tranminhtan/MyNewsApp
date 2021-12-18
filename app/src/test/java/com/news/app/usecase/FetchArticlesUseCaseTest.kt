package com.news.app.usecase

import com.news.app.ui.list.support.SupportedCountry
import com.news.app.utils.FakeDataProvider
import com.news.app.utils.FakeNewsRepositoryError
import com.news.app.utils.FakeNewsRepositorySuccess
import org.junit.Test

class FetchArticlesUseCaseTest {

    @Test
    fun fetchArticles_error_assertError() {
        val useCase = ObserveArticlesUseCase(FakeNewsRepositoryError(), SupportedCountry())
        useCase.fetchArticles().test()
            .assertError(Throwable::class.java)
            .assertTerminated()
            .assertNoValues()
            .dispose()
    }

    @Test
    fun fetchArticles_success_assertThat() {
        val useCase = ObserveArticlesUseCase(FakeNewsRepositorySuccess(), SupportedCountry())
        useCase.fetchArticles().test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(FakeDataProvider.mockArticleItems())
            .dispose()
    }
}