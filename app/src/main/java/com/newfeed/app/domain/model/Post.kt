package com.newfeed.app.domain.model

data class Post(
    val id: Long = 0,
    val user: User,
    val content: String,
    val imageUrl: String? = null,
    val likesCount: Int = 0,
    val commentsCount: Int = 0,
    val sharesCount: Int = 0,
    val isLiked: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)



