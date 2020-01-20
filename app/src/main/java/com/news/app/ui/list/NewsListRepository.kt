package com.news.app.ui.list

import com.news.app.BuildConfig
import com.news.app.db.NewsDatabase
import com.news.app.model.Article
import com.news.app.model.Status
import com.news.app.network.NewsService
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

interface NewsListRepository {
    fun observeArticles(): Observable<List<Article>>
    fun fetchArticles(countryCode: String): Completable
}

class NewsListRepositoryImpl @Inject constructor(
    private val database: NewsDatabase,
    private val newsService: NewsService
) : NewsListRepository {

    override fun observeArticles(): Observable<List<Article>> {
        return database.newsDao().getArticles().filter { it.isNotEmpty() }
    }

    override fun fetchArticles(countryCode: String): Completable {
        return newsService.getTopHeadlines(BuildConfig.API_KEY, countryCode)
            .filter { response -> response.status == Status.Ok && !response.articles.isNullOrEmpty() }
            .map { it.articles }
            .flatMapCompletable { articles ->
                database.newsDao().deleteAll()
                    .andThen(database.newsDao().insertAll(articles))
            }
            .onErrorComplete()
    }

}