package com.newfeed.app.data.local.dao

import androidx.room.*
import com.newfeed.app.data.local.entity.CommentEntity
import com.newfeed.app.data.local.relation.CommentWithUser
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Transaction
    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY createdAt ASC")
    fun getCommentsByPost(postId: Long): Flow<List<CommentWithUser>>
    
    @Query("SELECT COUNT(*) FROM comments WHERE postId = :postId")
    suspend fun getCommentsCount(postId: Long): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity): Long
    
    @Update
    suspend fun updateComment(comment: CommentEntity)
    
    @Delete
    suspend fun deleteComment(comment: CommentEntity)
}




