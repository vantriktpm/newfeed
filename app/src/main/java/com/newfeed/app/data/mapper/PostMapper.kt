package com.newfeed.app.data.mapper

import com.newfeed.app.data.local.entity.PostEntity
import com.newfeed.app.data.local.relation.PostWithUser
import com.newfeed.app.domain.model.Post

fun PostWithUser.toDomain(isLiked: Boolean = false): Post {
    return Post(
        id = post.id,
        user = user.toDomain(),
        content = post.content,
        imageUrl = post.imageUrl,
        likesCount = post.likesCount,
        commentsCount = post.commentsCount,
        sharesCount = post.sharesCount,
        isLiked = isLiked,
        createdAt = post.createdAt
    )
}

fun Post.toEntity(): PostEntity {
    return PostEntity(
        id = id,
        userId = user.id,
        content = content,
        imageUrl = imageUrl,
        likesCount = likesCount,
        commentsCount = commentsCount,
        sharesCount = sharesCount,
        createdAt = createdAt
    )
}



