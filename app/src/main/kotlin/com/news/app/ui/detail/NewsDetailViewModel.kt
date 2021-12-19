package com.news.app.ui.detail

import android.view.View
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
) : ViewModel() {

    private lateinit var disposable: Disposable

    private val _article = MutableLiveData<ArticleItem>()
    val article: LiveData<ArticleItem> = _article

    private val _contentVisibility = MutableLiveData(View.GONE)
    val contentVisibility: LiveData<Int> = _contentVisibility

    fun fetchArticle(id: Int) {
        disposable = fetchArticleInteractor(id)
            .subscribe({ article ->
                _article.postValue(article)
                _contentVisibility.postValue(
                    if (article.content.isEmpty()) {
                        View.GONE
                    } else {
                        View.VISIBLE
                    }
                )
            }, { Timber.w(it) })
    }

    override fun onCleared() {
        disposable.dispose()
    }
}