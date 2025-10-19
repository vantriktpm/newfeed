package com.newfeed.app.domain.model

data class Comment(
    val id: Long = 0,
    val postId: Long,
    val user: User,
    val content: String,
    val likesCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)



