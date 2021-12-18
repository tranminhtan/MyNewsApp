package com.news.app.ui.list

import com.news.app.BuildConfig
import com.news.app.base.SchedulersProvider
import com.news.app.db.NewsDatabase
import com.news.app.model.Article
import com.news.app.model.Status
import com.news.app.network.NewsService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

interface NewsListRepository {
    fun fetchArticles(countryCode: String = "gb"): Flowable<List<Article>>
    fun fetchArticleById(id: Long): Single<Article>
}

class NewsListRepositoryImpl @Inject constructor(
    private val database: NewsDatabase,
    private val newsService: NewsService,
    private val schedulersProvider: SchedulersProvider
) : NewsListRepository {

    // Expecting return data from DB, then from server
    override fun fetchArticles(countryCode: String): Flowable<List<Article>> {
        return Single.merge(fetchArticlesFromDB(), fetchArticlesFromServer(countryCode))
            .subscribeOn(schedulersProvider.io())
            .filter { list -> list.isNotEmpty() }
            .onBackpressureLatest()
    }

    override fun fetchArticleById(id: Long): Single<Article> =
        database.newsDao().getArticleById(id)
            .subscribeOn(schedulersProvider.io())

    private fun fetchArticlesFromDB(): Single<List<Article>> {
        return database.newsDao().getArticles()
            .doOnError { e -> Timber.e(e) }
            .onErrorReturn { emptyList() }
    }

    private fun fetchArticlesFromServer(countryCode: String): Single<List<Article>> {
        return newsService.getTopHeadlines(BuildConfig.API_KEY, countryCode)
            .filter { response -> response.status == Status.Ok && !response.articles.isNullOrEmpty() }
            .map { response -> response.articles }
            .flatMapSingle { articles ->
                saveArticles(articles)
                    .andThen(Single.just(articles))
            }
            .doOnError { e -> Timber.e(e) }
            .onErrorReturn { emptyList() }
    }

    private fun saveArticles(articles: List<Article>): Completable {
        return database.newsDao().deleteAll()
            .andThen(database.newsDao().insertAll(articles))
            .doOnError { e -> Timber.e(e) }
            .onErrorComplete()
    }
}