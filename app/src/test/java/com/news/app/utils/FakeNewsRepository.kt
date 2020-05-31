package com.news.app.utils

import com.news.app.model.Article
import com.news.app.ui.list.NewsListRepository
import io.reactivex.Flowable

class FakeNewsRepositorySuccess : NewsListRepository {
    override fun fetchArticles(countryCode: String): Flowable<List<Article>> {
        return Flowable.just(FakeDataProvider.mockArticles())
    }
}

class FakeNewsRepositoryError : NewsListRepository {
    override fun fetchArticles(countryCode: String): Flowable<List<Article>> {
        return Flowable.error(Throwable())
    }
}