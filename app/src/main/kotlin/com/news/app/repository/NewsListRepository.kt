package com.news.app.repository

import com.news.app.provider.SchedulersProvider
import com.news.app.db.NewsDao
import com.news.app.model.Article
import com.news.app.model.Status
import com.news.app.network.NewsService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

interface NewsListRepository {
    fun fetchArticleById(id: Int): Single<Article>
    fun fetchTopArticles(countryCode: String = "gb"): Completable
    fun observeTopArticles(): Observable<List<Article>>
}

class NewsListRepositoryImpl @Inject constructor(
    private val newsDao: NewsDao,
    private val newsService: NewsService,
    private val schedulersProvider: SchedulersProvider
) : NewsListRepository {

    override fun fetchArticleById(id: Int): Single<Article> =
        newsDao.getArticleById(id)
            .subscribeOn(schedulersProvider.io())

    override fun fetchTopArticles(countryCode: String): Completable {
        return newsService.getTopHeadlines(countryCode)
            .subscribeOn(schedulersProvider.io())
            .filter { response -> response.status == Status.Ok && !response.articles.isNullOrEmpty() }
            .map { response -> response.articles }
            .flatMapCompletable { articles ->
                saveArticles(articles)
            }
            .doOnError { e -> Timber.w(e) }
            .onErrorComplete()
    }

    override fun observeTopArticles(): Observable<List<Article>> =
        newsDao.getArticles()
            .subscribeOn(schedulersProvider.io())
            .filter { list -> list.isNotEmpty() }


    private fun saveArticles(articles: List<Article>): Completable {
        return Completable.fromCallable { newsDao.updateData(articles) }
            .doOnError { e -> Timber.w(e) }
            .onErrorComplete()
    }
}