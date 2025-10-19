package com.newfeed.app.data.local

import com.newfeed.app.data.local.dao.PostDao
import com.newfeed.app.data.local.dao.UserDao
import com.newfeed.app.data.local.entity.PostEntity
import com.newfeed.app.data.local.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseInitializer @Inject constructor(
    private val userDao: UserDao,
    private val postDao: PostDao
) {
    fun initializeDatabase(scope: CoroutineScope) {
        scope.launch {
            // Check if database is already initialized
            val userCount = userDao.getUserCount()
            if (userCount > 0) {
                // Database already has data, skip initialization
                return@launch
            }
            
            // Add sample data
            val user1Id = userDao.insertUser(
                UserEntity(
                    name = "John Doe",
                    email = "john@example.com",
                    profileImageUrl = null,
                    bio = "Software Developer | Travel Enthusiast",
                    friendsCount = 245,
                    photosCount = 89
                )
            )
            
            val user2Id = userDao.insertUser(
                UserEntity(
                    name = "Jane Smith",
                    email = "jane@example.com",
                    profileImageUrl = null,
                    bio = "Designer | Coffee Lover ☕",
                    friendsCount = 532,
                    photosCount = 156
                )
            )
            
            val user3Id = userDao.insertUser(
                UserEntity(
                    name = "Mike Johnson",
                    email = "mike@example.com",
                    profileImageUrl = null,
                    bio = "Photographer | Nature Lover 🌲",
                    friendsCount = 389,
                    photosCount = 234
                )
            )
            
            // Add sample posts
            postDao.insertPost(
                PostEntity(
                    userId = user1Id,
                    content = "Just finished working on a new Android app! 🚀 Excited to share it with everyone soon.",
                    likesCount = 45,
                    commentsCount = 12,
                    sharesCount = 3,
                    createdAt = System.currentTimeMillis() - 3600000 // 1 hour ago
                )
            )
            
            postDao.insertPost(
                PostEntity(
                    userId = user2Id,
                    content = "Beautiful sunset today! 🌅 Remember to take time to appreciate the little things in life.",
                    likesCount = 128,
                    commentsCount = 24,
                    sharesCount = 8,
                    createdAt = System.currentTimeMillis() - 7200000 // 2 hours ago
                )
            )
            
            postDao.insertPost(
                PostEntity(
                    userId = user3Id,
                    content = "New photo project coming soon! Stay tuned for some amazing nature photography 📸",
                    likesCount = 89,
                    commentsCount = 15,
                    sharesCount = 5,
                    createdAt = System.currentTimeMillis() - 10800000 // 3 hours ago
                )
            )
            
            postDao.insertPost(
                PostEntity(
                    userId = user1Id,
                    content = "Learning Jetpack Compose has been an amazing experience. The declarative UI approach makes building apps so much easier!",
                    likesCount = 76,
                    commentsCount = 19,
                    sharesCount = 11,
                    createdAt = System.currentTimeMillis() - 21600000 // 6 hours ago
                )
            )
            
            postDao.insertPost(
                PostEntity(
                    userId = user2Id,
                    content = "Weekend vibes! 🎉 Hope everyone is having a great time. Remember to rest and recharge for the week ahead.",
                    likesCount = 234,
                    commentsCount = 45,
                    sharesCount = 12,
                    createdAt = System.currentTimeMillis() - 43200000 // 12 hours ago
                )
            )
            
            postDao.insertPost(
                PostEntity(
                    userId = user3Id,
                    content = "Captured this amazing moment during my morning hike. Nature never ceases to amaze me! 🏔️",
                    likesCount = 456,
                    commentsCount = 67,
                    sharesCount = 23,
                    createdAt = System.currentTimeMillis() - 86400000 // 1 day ago
                )
            )
        }
    }
}



