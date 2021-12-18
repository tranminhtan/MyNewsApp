package com.news.app.ui.list

import androidx.lifecycle.*
import com.news.app.model.ArticleItem
import com.news.app.usecase.FetchTopHeadlinesUseCase
import com.news.app.usecase.ObserveArticlesUseCase
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val observeArticlesUseCase: ObserveArticlesUseCase,
    private val fetchTopHeadlinesUseCase: FetchTopHeadlinesUseCase
) : ViewModel(), LifecycleObserver {

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun observeTopHeadlines() {
        observeArticlesUseCase.fetchArticles()
            .subscribe({ _articles.postValue(it) }, { Timber.e(it) })
            .let { disposables.add(it) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchTopHeadlines() {
        fetchTopHeadlinesUseCase.fetchTopHeadlines()
            .subscribe({}, { Timber.e(it) })
            .let { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.dispose()
    }
}