package com.news.app.utils

import com.news.app.model.Article
import com.news.app.repository.NewsListRepository
import io.reactivex.Flowable

class FakeNewsRepositorySuccess : NewsListRepository {
    override fun fetchTopArticles(countryCode: String): Flowable<List<Article>> {
        return Flowable.just(FakeDataProvider.mockArticles())
    }
}

class FakeNewsRepositoryError : NewsListRepository {
    override fun fetchTopArticles(countryCode: String): Flowable<List<Article>> {
        return Flowable.error(Throwable())
    }
}