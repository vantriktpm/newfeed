package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.model.Comment
import com.newfeed.app.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    operator fun invoke(postId: Long): Flow<List<Comment>> {
        return commentRepository.getCommentsByPost(postId)
    }
}



