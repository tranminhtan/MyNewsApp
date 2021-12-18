package com.news.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.news.app.model.Article
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewsDao {

    @Query("SELECT * FROM article")
    fun getArticles(): Single<List<Article>>

    @Query("SELECT * FROM article WHERE id = :id LIMIT 1")
    fun getArticleById(id: Long): Single<Article>

    @Insert
    fun insertAll(articles: List<Article>): Completable

    @Query("DELETE FROM article")
    fun deleteAll(): Completable
}