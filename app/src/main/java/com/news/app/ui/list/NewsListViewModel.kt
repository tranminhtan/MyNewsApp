package com.news.app.ui.list

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.news.app.ui.list.support.ArticleItem
import com.news.app.ui.list.usecase.FetchArticlesUseCase
import com.news.app.ui.list.usecase.ObserveArticlesUseCase
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.Functions
import timber.log.Timber
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val fetchArticlesUseCase: FetchArticlesUseCase,
    private val observeArticlesUseCase: ObserveArticlesUseCase
) : ViewModel(), LifecycleObserver {

    private lateinit var disposable: Disposable

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        disposable = Completable.mergeArray(observeArticles(), fetchArticles())
            .subscribe(Functions.EMPTY_ACTION, Consumer { Timber.e(it) })
    }

    override fun onCleared() {
        disposable.dispose()
    }

    private fun fetchArticles(): Completable {
        return fetchArticlesUseCase.fetchArticles()
    }

    private fun observeArticles(): Completable {
        return observeArticlesUseCase.observe()
            .doOnNext { _articles.postValue(it) }
            .ignoreElements()
    }
}