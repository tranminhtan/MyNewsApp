package com.news.app.ui.list.usecase

import com.news.app.utils.FakeNewsRepository
import org.junit.Test

class FetchArticlesUseCaseTest {

    @Test
    fun fetchArticles() {
        val useCase = FetchArticlesUseCase(FakeNewsRepository(), SupportedCountry())
        useCase.fetchArticles().test()
            .assertNoErrors()
            .assertComplete()
            .dispose()
    }
}