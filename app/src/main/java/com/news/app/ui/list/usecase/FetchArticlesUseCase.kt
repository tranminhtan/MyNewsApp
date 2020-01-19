package com.news.app.ui.list.usecase

import com.news.app.ui.list.NewsListRepository
import io.reactivex.Completable
import javax.inject.Inject

class FetchArticlesUseCase @Inject constructor(
    private val repository: NewsListRepository,
    private val supportedCountry: SupportedCountry
) {
    fun fetchArticles(): Completable = repository.fetchArticles(supportedCountry.getCountryCode())
}