package com.newfeed.app.di

import android.content.Context
import androidx.room.Room
import com.newfeed.app.data.local.NewsFeedDatabase
import com.newfeed.app.data.local.dao.CommentDao
import com.newfeed.app.data.local.dao.LikeDao
import com.newfeed.app.data.local.dao.PostDao
import com.newfeed.app.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideNewfeedDatabase(
        @ApplicationContext context: Context
    ): NewsFeedDatabase {
        return Room.databaseBuilder(
            context,
            NewsFeedDatabase::class.java,
            "newfeed_database"
        ).build()
    }
    
    @Provides
    fun provideUserDao(database: NewsFeedDatabase): UserDao {
        return database.userDao()
    }
    
    @Provides
    fun providePostDao(database: NewsFeedDatabase): PostDao {
        return database.postDao()
    }
    
    @Provides
    fun provideCommentDao(database: NewsFeedDatabase): CommentDao {
        return database.commentDao()
    }
    
    @Provides
    fun provideLikeDao(database: NewsFeedDatabase): LikeDao {
        return database.likeDao()
    }
}



