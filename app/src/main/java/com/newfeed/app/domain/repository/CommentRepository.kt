package com.newfeed.app.domain.repository

import com.newfeed.app.domain.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun getCommentsByPost(postId: Long): Flow<List<Comment>>
    suspend fun createComment(postId: Long, userId: Long, content: String): Long
    suspend fun deleteComment(commentId: Long)
}



