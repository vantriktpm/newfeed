package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val postRepository: PostRepository
) {
    operator fun invoke(): Flow<List<Post>> {
        return postRepository.getAllPosts()
    }
}



