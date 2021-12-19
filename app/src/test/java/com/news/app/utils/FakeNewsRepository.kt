package com.news.app.utils

import com.news.app.model.Article
import com.news.app.repository.NewsListRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class FakeNewsRepositorySuccess : NewsListRepository {
    private val subject = BehaviorSubject.create<List<Article>>()

    init {
        subject.onNext(FakeDataProvider.mockArticles())
    }

    override fun fetchArticleById(id: Int): Single<Article> = Single.just(FakeDataProvider.mockArticles()[0])

    override fun fetchTopArticles(countryCode: String): Completable = Completable.complete()

    override fun observeTopArticles(): Observable<List<Article>> = subject
}

class FakeNewsRepositoryError : NewsListRepository {
    override fun fetchArticleById(id: Int): Single<Article> =
        Single.error(Throwable())

    override fun fetchTopArticles(countryCode: String): Completable =
        Completable.error(Throwable())

    override fun observeTopArticles(): Observable<List<Article>> =
        Observable.error(Throwable())
}