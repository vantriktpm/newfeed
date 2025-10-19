package com.newfeed.app.domain.repository

import com.newfeed.app.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getAllPosts(): Flow<List<Post>>
    fun getPostById(postId: Long): Flow<Post?>
    fun getPostsByUser(userId: Long): Flow<List<Post>>
    suspend fun createPost(userId: Long, content: String, imageUrl: String?): Long
    suspend fun updatePost(post: Post)
    suspend fun deletePost(postId: Long)
    suspend fun likePost(postId: Long, userId: Long)
    suspend fun unlikePost(postId: Long, userId: Long)
}



