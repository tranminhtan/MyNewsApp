package com.news.app.ui.list

import androidx.lifecycle.*
import com.news.app.ui.list.support.ArticleItem
import com.news.app.ui.list.usecase.FetchArticlesUseCase
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val fetchArticlesUseCase: FetchArticlesUseCase
) : ViewModel(), LifecycleObserver {

    private lateinit var disposable: Disposable

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun fetchArticles() {
        disposable = fetchArticlesUseCase.fetchArticles()
            .subscribe({ _articles.postValue(it) }, { Timber.e(it) })
    }

    override fun onCleared() {
        disposable.dispose()
    }
}