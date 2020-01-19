package com.news.app.utils

import com.news.app.model.Article
import com.news.app.ui.list.NewsListRepository
import io.reactivex.Completable
import io.reactivex.Observable

class FakeNewsRepository : NewsListRepository {
    override fun observeArticles(): Observable<List<Article>> {
        return Observable.just(
            arrayListOf(
                Article(0, "title1", "author1", "", "", "", "", ""),
                Article(1, "title2", "author2", "", "", "", "", "")
            )
        )
    }

    override fun fetchArticles(countryCode: String): Completable {
        return Completable.complete()
    }
}