package com.news.app.ui.list.usecase

import com.news.app.ui.list.support.SupportedCountry
import com.news.app.utils.FakeDataProvider
import com.news.app.utils.FakeNewsRepositoryError
import com.news.app.utils.FakeNewsRepositorySuccess
import org.junit.Test

class FetchArticlesUseCaseTest {

    @Test
    fun fetchArticles_error_assertError() {
        val useCase = FetchArticlesUseCase(FakeNewsRepositoryError(), SupportedCountry())
        useCase.fetchArticles().test()
            .assertError(Throwable::class.java)
            .assertTerminated()
            .assertNoValues()
            .dispose()
    }

    @Test
    fun fetchArticles_success_assertThat() {
        val useCase = FetchArticlesUseCase(FakeNewsRepositorySuccess(), SupportedCountry())
        useCase.fetchArticles().test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(FakeDataProvider.mockArticleItems())
            .dispose()
    }
}