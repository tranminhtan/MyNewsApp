package com.news.app.interactor

import com.news.app.model.ArticleItem
import com.news.app.model.toArticleItem
import com.news.app.repository.NewsListRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchArticleInteractor @Inject constructor(
    private val repository: NewsListRepository,
) {
    operator fun invoke(id: Int): Single<ArticleItem> =
        repository.fetchArticleById(id)
            .map { it.toArticleItem() }
}