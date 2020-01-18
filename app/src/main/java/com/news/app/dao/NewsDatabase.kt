package com.news.app.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.news.app.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}