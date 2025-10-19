package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.repository.CommentRepository
import javax.inject.Inject

class CreateCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend operator fun invoke(postId: Long, userId: Long, content: String): Long {
        return commentRepository.createComment(postId, userId, content)
    }
}



