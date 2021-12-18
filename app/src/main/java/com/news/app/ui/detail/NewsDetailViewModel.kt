package com.news.app.ui.detail

import androidx.lifecycle.*
import com.news.app.model.ArticleItem
import com.news.app.usecase.ObserveArticlesUseCase
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val observeArticlesUseCase: ObserveArticlesUseCase
) : ViewModel(), LifecycleObserver {

    private lateinit var disposable: Disposable

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchArticles() {
        disposable = observeArticlesUseCase.fetchArticles()
            .subscribe({ _articles.postValue(it) }, { Timber.e(it) })
    }

    override fun onCleared() {
        disposable.dispose()
    }
}