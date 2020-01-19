package com.news.app.utils

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.news.app.db.NewsDao
import com.news.app.db.NewsDatabase
import org.mockito.Mockito

class FakeDataBase : NewsDatabase() {
    override fun newsDao(): NewsDao {
        return Mockito.mock(NewsDao::class.java)
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        return Mockito.mock(SupportSQLiteOpenHelper::class.java)
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return Mockito.mock(InvalidationTracker::class.java)
    }

    override fun clearAllTables() {}
}