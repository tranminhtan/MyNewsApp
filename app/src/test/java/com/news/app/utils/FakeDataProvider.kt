package com.news.app.utils

import com.news.app.model.Article
import com.news.app.ui.list.support.ArticleItem

object FakeDataProvider {
    fun mockArticles(): List<Article> {
        return arrayListOf(
            Article(1, "title1", "author1", "des1", "url1", null, null, null),
            Article(2, "title2", "author2", "des2", "url2", null, null, null)
        )
    }

    fun mockArticleItems(): List<ArticleItem> {
        return arrayListOf(
            ArticleItem("title1", "author1", "des1", "url1", "", ""),
            ArticleItem("title2", "author2", "des2", "url2", "", "")
        )
    }
}