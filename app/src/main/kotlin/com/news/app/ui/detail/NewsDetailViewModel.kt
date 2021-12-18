package com.news.app.ui.detail

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.app.interactor.FetchArticleInteractor
import com.news.app.model.ArticleItem
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val fetchArticleInteractor: FetchArticleInteractor,
) : ViewModel(), LifecycleObserver {

    private lateinit var disposable: Disposable

    private val _article = MutableLiveData<ArticleItem>()
    val article: LiveData<ArticleItem> = _article

    fun fetchArticle(id: Int) {
        disposable = fetchArticleInteractor(id)
            .subscribe({ _article.postValue(it) }, { Timber.w(it) })
    }

    override fun onCleared() {
        disposable.dispose()
    }
}