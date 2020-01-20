package com.news.app.ui.list.support

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class OnArticleClickListenerImplTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getOnClickArticle() {
        val listener = OnArticleClickListenerImpl()
        Assert.assertNull(listener.onClickArticle.value)

        val article = ArticleItem("", "", "", "", "", "")
        listener.onClickArticle(article)
        Assert.assertEquals(article, listener.onClickArticle.value)
    }
}