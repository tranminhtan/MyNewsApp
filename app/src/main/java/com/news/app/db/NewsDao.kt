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

    @Insert
    fun insertAll(articles: List<Article>): Completable

    @Query("DELETE FROM article")
    fun deleteAll(): Completable
}