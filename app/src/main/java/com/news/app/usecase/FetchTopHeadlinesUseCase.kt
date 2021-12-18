package com.news.app.usecase

import com.news.app.repository.NewsListRepository
import io.reactivex.Completable
import javax.inject.Inject

class FetchTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsListRepository,
) {
    fun fetchTopHeadlines(): Completable =
        repository.fetchTopHeadlines()
}