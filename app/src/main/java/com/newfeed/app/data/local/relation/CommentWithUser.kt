package com.newfeed.app.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.newfeed.app.data.local.entity.CommentEntity
import com.newfeed.app.data.local.entity.UserEntity

data class CommentWithUser(
    @Embedded val comment: CommentEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "id"
    )
    val user: UserEntity
)



