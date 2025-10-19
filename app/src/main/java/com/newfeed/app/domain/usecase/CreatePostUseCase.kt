package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.repository.PostRepository
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(userId: Long, content: String, imageUrl: String? = null): Long {
        return postRepository.createPost(userId, content, imageUrl)
    }
}



