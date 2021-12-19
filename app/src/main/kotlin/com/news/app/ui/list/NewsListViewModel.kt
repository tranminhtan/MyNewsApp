package com.news.app.ui.list

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.news.app.interactor.FetchTopArticlesInteractor
import com.news.app.interactor.ObserveArticlesInteractor
import com.news.app.model.ArticleItem
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val observeArticlesInteractor: ObserveArticlesInteractor,
    private val fetchTopArticlesInteractor: FetchTopArticlesInteractor
) : ViewModel(), LifecycleObserver {

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    private val _shimmerVisibility = MutableLiveData(View.VISIBLE)
    val shimmerVisibility: LiveData<Int> = _shimmerVisibility

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun observeTopArticles() {
        observeArticlesInteractor()
            .doOnSubscribe { _shimmerVisibility.postValue(View.VISIBLE) }
            .subscribe({ article ->
                _articles.postValue(article)
                _shimmerVisibility.postValue(View.GONE)
            }, { Timber.w(it) })
            .let { disposables.add(it) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchTopArticles() {
        fetchTopArticlesInteractor()
            .subscribe({}, { Timber.w(it) })
            .let { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.dispose()
    }
}