package com.newfeed.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newfeed.app.data.local.dao.CommentDao
import com.newfeed.app.data.local.dao.LikeDao
import com.newfeed.app.data.local.dao.PostDao
import com.newfeed.app.data.local.dao.UserDao
import com.newfeed.app.data.local.entity.CommentEntity
import com.newfeed.app.data.local.entity.LikeEntity
import com.newfeed.app.data.local.entity.PostEntity
import com.newfeed.app.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PostEntity::class,
        CommentEntity::class,
        LikeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class NewsFeedDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
    abstract fun likeDao(): LikeDao
}



