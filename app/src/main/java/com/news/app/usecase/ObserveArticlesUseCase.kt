package com.news.app.usecase

import com.news.app.model.ArticleItem
import com.news.app.model.toArticleItem
import com.news.app.repository.NewsListRepository
import io.reactivex.Observable
import javax.inject.Inject

class ObserveArticlesUseCase @Inject constructor(
    private val repository: NewsListRepository,
) {
    fun fetchArticles(): Observable<List<ArticleItem>> {
        return repository.observeArticles()
            .switchMapSingle { articles ->
                Observable.fromIterable(articles)
                    .map { it.toArticleItem() }
                    .toList()
            }
    }
}