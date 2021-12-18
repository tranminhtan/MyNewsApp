package com.news.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.news.app.model.Article

@Database(entities = [Article::class], version = 2, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}