package com.news.app.interactor

import com.news.app.repository.NewsListRepository
import io.reactivex.Completable
import javax.inject.Inject

class FetchTopArticlesInteractor @Inject constructor(
    private val repository: NewsListRepository,
) {
    operator fun invoke(): Completable =
        repository.fetchTopArticles()
}