package com.newfeed.app.data.mapper

import com.newfeed.app.data.local.entity.CommentEntity
import com.newfeed.app.data.local.relation.CommentWithUser
import com.newfeed.app.domain.model.Comment

fun CommentWithUser.toDomain(): Comment {
    return Comment(
        id = comment.id,
        postId = comment.postId,
        user = user.toDomain(),
        content = comment.content,
        likesCount = comment.likesCount,
        createdAt = comment.createdAt
    )
}

fun Comment.toEntity(): CommentEntity {
    return CommentEntity(
        id = id,
        postId = postId,
        userId = user.id,
        content = content,
        likesCount = likesCount,
        createdAt = createdAt
    )
}



