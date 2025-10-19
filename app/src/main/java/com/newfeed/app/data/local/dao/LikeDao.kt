package com.newfeed.app.data.local.dao

import androidx.room.*
import com.newfeed.app.data.local.entity.LikeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeDao {
    @Query("SELECT * FROM likes WHERE postId = :postId")
    fun getLikesByPost(postId: Long): Flow<List<LikeEntity>>
    
    @Query("SELECT COUNT(*) FROM likes WHERE postId = :postId")
    suspend fun getLikesCount(postId: Long): Int
    
    @Query("SELECT * FROM likes WHERE postId = :postId AND userId = :userId")
    suspend fun getLikeByUserAndPost(postId: Long, userId: Long): LikeEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLike(like: LikeEntity): Long
    
    @Delete
    suspend fun deleteLike(like: LikeEntity)
    
    @Query("DELETE FROM likes WHERE postId = :postId AND userId = :userId")
    suspend fun deleteLikeByUserAndPost(postId: Long, userId: Long)
}



