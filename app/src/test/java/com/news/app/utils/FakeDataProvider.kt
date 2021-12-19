package com.news.app.utils

import com.news.app.model.Article
import com.news.app.model.ArticleItem

object FakeDataProvider {
    private const val t1 = "title1"
    private const val t2 = "title2"

    fun mockArticles(): List<Article> {
        return arrayListOf(
            Article(t1, t1.hashCode(), "author1", "des1", "url1", null, null, "content"),
            Article(t2, t2.hashCode(), "author2", "des2", "url2", null, null, "content")
        )
    }

    fun mockArticleItems(): List<ArticleItem> {
        return arrayListOf(
            ArticleItem(t1.hashCode(), t1, "author1", "des1", "url1", "", "content"),
            ArticleItem(t2.hashCode(), t2, "author2", "des2", "url2", "", "content")
        )
    }
}