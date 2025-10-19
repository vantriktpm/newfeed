package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.repository.PostRepository
import javax.inject.Inject

class ToggleLikeUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(postId: Long, userId: Long, isLiked: Boolean) {
        if (isLiked) {
            postRepository.unlikePost(postId, userId)
        } else {
            postRepository.likePost(postId, userId)
        }
    }
}



