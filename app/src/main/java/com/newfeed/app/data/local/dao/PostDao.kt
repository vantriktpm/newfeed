package com.newfeed.app.data.local.dao

import androidx.room.*
import com.newfeed.app.data.local.entity.PostEntity
import com.newfeed.app.data.local.relation.PostWithUser
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Transaction
    @Query("SELECT * FROM posts ORDER BY createdAt DESC")
    fun getAllPostsWithUser(): Flow<List<PostWithUser>>
    
    @Transaction
    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPostById(postId: Long): Flow<PostWithUser?>
    
    @Transaction
    @Query("SELECT * FROM posts WHERE userId = :userId ORDER BY createdAt DESC")
    fun getPostsByUser(userId: Long): Flow<List<PostWithUser>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity): Long
    
    @Update
    suspend fun updatePost(post: PostEntity)
    
    @Delete
    suspend fun deletePost(post: PostEntity)
    
    @Query("UPDATE posts SET likesCount = :count WHERE id = :postId")
    suspend fun updateLikesCount(postId: Long, count: Int)
    
    @Query("UPDATE posts SET commentsCount = :count WHERE id = :postId")
    suspend fun updateCommentsCount(postId: Long, count: Int)
}




