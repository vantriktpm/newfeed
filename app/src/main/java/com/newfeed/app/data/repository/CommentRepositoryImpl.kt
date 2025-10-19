package com.newfeed.app.data.repository

import com.newfeed.app.data.local.dao.CommentDao
import com.newfeed.app.data.local.dao.PostDao
import com.newfeed.app.data.local.entity.CommentEntity
import com.newfeed.app.data.mapper.toDomain
import com.newfeed.app.domain.model.Comment
import com.newfeed.app.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentDao: CommentDao,
    private val postDao: PostDao
) : CommentRepository {
    
    override fun getCommentsByPost(postId: Long): Flow<List<Comment>> {
        return commentDao.getCommentsByPost(postId).map { commentsWithUser ->
            commentsWithUser.map { it.toDomain() }
        }
    }
    
    override suspend fun createComment(postId: Long, userId: Long, content: String): Long {
        val comment = CommentEntity(
            postId = postId,
            userId = userId,
            content = content
        )
        val commentId = commentDao.insertComment(comment)
        
        // Update comments count
        val count = commentDao.getCommentsCount(postId)
        postDao.updateCommentsCount(postId, count)
        
        return commentId
    }
    
    override suspend fun deleteComment(commentId: Long) {
        // Implementation for delete comment
    }
}



