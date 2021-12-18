package com.news.app.usecase

import com.news.app.model.ArticleItem
import com.news.app.model.toArticleItem
import com.news.app.repository.NewsListRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchArticleUseCase @Inject constructor(
    private val repository: NewsListRepository,
) {
    fun fetchArticleById(id: Long): Single<ArticleItem> =
        repository.fetchArticleById(id)
            .map { it.toArticleItem() }
}