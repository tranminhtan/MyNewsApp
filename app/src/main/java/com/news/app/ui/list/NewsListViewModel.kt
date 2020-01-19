package com.news.app.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.news.app.ui.list.usecase.FetchArticlesUseCase
import com.news.app.ui.list.usecase.ObserveArticlesUseCase
import io.reactivex.Completable
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val fetchArticlesUseCase: FetchArticlesUseCase,
    private val observeArticlesUseCase: ObserveArticlesUseCase
) : ViewModel() {

    private val _articles = MutableLiveData<List<ArticleItem>>()
    val articles: LiveData<List<ArticleItem>> = _articles

    fun fetchArticles(): Completable {
        return fetchArticlesUseCase.fetchArticles()
    }

    fun observeArticles(): Completable {
        return observeArticlesUseCase.observe()
            .doOnNext { _articles.postValue(it) }
            .ignoreElements()
    }
}