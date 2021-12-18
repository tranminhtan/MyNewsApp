package com.news.app.usecase

import com.news.app.model.Article
import com.news.app.model.ArticleItem
import com.news.app.ui.list.NewsListRepository
import io.reactivex.Flowable
import javax.inject.Inject

class FetchArticlesUseCase @Inject constructor(
    private val repository: NewsListRepository,
) {
    fun fetchArticles(): Flowable<List<ArticleItem>> {
        return repository.fetchArticles()
            .concatMapSingle { articles ->
                Flowable.fromIterable(articles)
                    .map { it.toArticleItem() }
                    .toList()
            }
    }
}

fun Article.toArticleItem() =
    ArticleItem(
        id = id,
        title = title,
        author = author ?: "",
        description = description ?: "",
        url = url ?: "",
        imageUrl = imageUrl ?: "",
        content = content ?: ""
    )