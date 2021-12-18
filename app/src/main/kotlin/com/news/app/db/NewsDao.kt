package com.news.app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.news.app.model.Article
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface NewsDao {

    @Query("SELECT * FROM article")
    fun getArticles(): Observable<List<Article>>

    @Query("SELECT * FROM article WHERE id = :id LIMIT 1")
    fun getArticleById(id: Int): Single<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSync(articles: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll(): Completable

    @Query("DELETE FROM article")
    fun deleteAllSync()

    @Transaction
    fun updateData(articles: List<Article>) {
        deleteAllSync()
        insertAllSync(articles)
    }
}