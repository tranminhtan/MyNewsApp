package com.news.app.ui.list

import com.news.app.BuildConfig
import com.news.app.dao.NewsDao
import com.news.app.model.Article
import com.news.app.model.Status
import com.news.app.network.NewsService
import io.reactivex.Completable
import io.reactivex.Observable

interface NewsListRepository {
    fun observeArticles(): Observable<List<Article>>
    fun fetchArticles(countryCode: String): Completable
}

class NewsListRepositoryImpl(
    private val newsDao: NewsDao,
    private val newsService: NewsService
) : NewsListRepository {

    override fun observeArticles(): Observable<List<Article>> {
        return newsDao.getArticles().filter { it.isNotEmpty() }
    }

    override fun fetchArticles(countryCode: String): Completable {
        return newsService.getTopHeadlines(BuildConfig.API_KEY, countryCode)
            .map { response ->
                if (response.status == Status.Error || response.articles.isNullOrEmpty()) {
                    throw IllegalStateException("There is no data")
                } else {
                    response.articles
                }
            }
            .flatMapCompletable { articles -> newsDao.deleteAll().andThen(newsDao.insertAll(articles)) }
    }

}