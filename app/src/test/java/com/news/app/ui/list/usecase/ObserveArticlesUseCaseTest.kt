package com.news.app.ui.list.usecase

import com.news.app.utils.FakeNewsRepository
import org.junit.Test

class ObserveArticlesUseCaseTest {

    @Test
    fun observe() {
        val useCase = ObserveArticlesUseCase(FakeNewsRepository())
        useCase.observe().test()
            .assertValueCount(1)
            .assertValue { values -> values.size == 2 }
            .assertTerminated()
            .dispose()
    }
}