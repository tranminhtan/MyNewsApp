package com.news.app.interactor

import com.news.app.model.ArticleItem
import com.news.app.model.toArticleItem
import com.news.app.repository.NewsListRepository
import io.reactivex.Observable
import javax.inject.Inject

class ObserveArticlesInteractor @Inject constructor(
    private val repository: NewsListRepository,
) {
    operator fun invoke(): Observable<List<ArticleItem>> {
        return repository.observeTopArticles()
            .switchMapSingle { articles ->
                Observable.fromIterable(articles)
                    .map { it.toArticleItem() }
                    .toList()
            }
    }
}