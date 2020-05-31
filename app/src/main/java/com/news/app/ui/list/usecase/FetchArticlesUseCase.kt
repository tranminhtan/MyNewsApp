package com.news.app.ui.list.usecase

import com.news.app.model.Article
import com.news.app.ui.list.NewsListRepository
import com.news.app.ui.list.support.ArticleItem
import com.news.app.ui.list.support.SupportedCountry
import io.reactivex.Flowable
import javax.inject.Inject

class FetchArticlesUseCase @Inject constructor(
    private val repository: NewsListRepository,
    private val supportedCountry: SupportedCountry
) {
    fun fetchArticles(): Flowable<List<ArticleItem>> {
        return repository.fetchArticles(supportedCountry.getCountryCode())
            .switchMapSingle { articles ->
                Flowable.fromIterable(articles)
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