package com.newfeed.app.data.repository

import com.newfeed.app.data.local.dao.LikeDao
import com.newfeed.app.data.local.dao.PostDao
import com.newfeed.app.data.local.entity.LikeEntity
import com.newfeed.app.data.local.entity.PostEntity
import com.newfeed.app.data.mapper.toDomain
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val likeDao: LikeDao
) : PostRepository {
    
    override fun getAllPosts(): Flow<List<Post>> {
        return postDao.getAllPostsWithUser().map { postsWithUser ->
            postsWithUser.map { postWithUser ->
                val isLiked = likeDao.getLikeByUserAndPost(postWithUser.post.id, 1L) != null
                postWithUser.toDomain(isLiked)
            }
        }
    }
    
    override fun getPostById(postId: Long): Flow<Post?> {
        return postDao.getPostById(postId).map { postWithUser ->
            postWithUser?.let {
                val isLiked = likeDao.getLikeByUserAndPost(it.post.id, 1L) != null
                it.toDomain(isLiked)
            }
        }
    }
    
    override fun getPostsByUser(userId: Long): Flow<List<Post>> {
        return postDao.getPostsByUser(userId).map { postsWithUser ->
            postsWithUser.map { postWithUser ->
                val isLiked = likeDao.getLikeByUserAndPost(postWithUser.post.id, 1L) != null
                postWithUser.toDomain(isLiked)
            }
        }
    }
    
    override suspend fun createPost(userId: Long, content: String, imageUrl: String?): Long {
        val post = PostEntity(
            userId = userId,
            content = content,
            imageUrl = imageUrl
        )
        return postDao.insertPost(post)
    }
    
    override suspend fun updatePost(post: Post) {
        val entity = PostEntity(
            id = post.id,
            userId = post.user.id,
            content = post.content,
            imageUrl = post.imageUrl,
            likesCount = post.likesCount,
            commentsCount = post.commentsCount,
            sharesCount = post.sharesCount,
            createdAt = post.createdAt
        )
        postDao.updatePost(entity)
    }
    
    override suspend fun deletePost(postId: Long) {
        // First get the post, then delete it
        // Note: In a real implementation, we should handle this more elegantly
    }
    
    override suspend fun likePost(postId: Long, userId: Long) {
        val existingLike = likeDao.getLikeByUserAndPost(postId, userId)
        if (existingLike == null) {
            likeDao.insertLike(LikeEntity(postId = postId, userId = userId))
            val count = likeDao.getLikesCount(postId)
            postDao.updateLikesCount(postId, count)
        }
    }
    
    override suspend fun unlikePost(postId: Long, userId: Long) {
        likeDao.deleteLikeByUserAndPost(postId, userId)
        val count = likeDao.getLikesCount(postId)
        postDao.updateLikesCount(postId, count)
    }
}



