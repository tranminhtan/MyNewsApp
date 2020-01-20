package com.news.app.ui.list.usecase

import com.news.app.model.Article
import com.news.app.ui.list.support.ArticleItem
import com.news.app.ui.list.NewsListRepository
import io.reactivex.Observable
import javax.inject.Inject

class ObserveArticlesUseCase @Inject constructor(private val repository: NewsListRepository) {
    fun observe(): Observable<List<ArticleItem>> {
        return repository.observeArticles()
            .switchMapSingle {
                Observable.fromIterable(it)
                    .map { article -> toArticleItem(article) }
                    .toList()
            }
    }

    private fun toArticleItem(article: Article): ArticleItem {
        return article.run {
            ArticleItem(
                title,
                author ?: "",
                description ?: "",
                url ?: "",
                imageUrl ?: "",
                content ?: ""
            )
        }
    }
}