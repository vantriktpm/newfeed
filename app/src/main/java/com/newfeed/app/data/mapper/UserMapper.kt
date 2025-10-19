package com.newfeed.app.data.mapper

import com.newfeed.app.data.local.entity.UserEntity
import com.newfeed.app.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        profileImageUrl = profileImageUrl,
        coverImageUrl = coverImageUrl,
        bio = bio,
        email = email,
        friendsCount = friendsCount,
        photosCount = photosCount,
        createdAt = createdAt
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        profileImageUrl = profileImageUrl,
        coverImageUrl = coverImageUrl,
        bio = bio,
        email = email,
        friendsCount = friendsCount,
        photosCount = photosCount,
        createdAt = createdAt
    )
}



