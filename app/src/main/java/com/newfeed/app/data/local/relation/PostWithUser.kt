package com.newfeed.app.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.newfeed.app.data.local.entity.PostEntity
import com.newfeed.app.data.local.entity.UserEntity

data class PostWithUser(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val user: UserEntity
)



