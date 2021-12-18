package com.news.app.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.news.app.model.ArticleItem
import javax.inject.Inject

interface OnArticleClickListener {
    fun onClickArticle(articleItem: ArticleItem)
    val onClickArticle: LiveData<ArticleItem>
}

class OnArticleClickListenerImpl @Inject constructor() : OnArticleClickListener {
    private val _onClickArticle = MutableLiveData<ArticleItem>()

    override val onClickArticle: LiveData<ArticleItem> = _onClickArticle

    override fun onClickArticle(articleItem: ArticleItem) {
        _onClickArticle.value = articleItem
    }
}