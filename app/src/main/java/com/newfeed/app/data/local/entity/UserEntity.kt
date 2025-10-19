package com.newfeed.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val profileImageUrl: String? = null,
    val coverImageUrl: String? = null,
    val bio: String? = null,
    val email: String,
    val friendsCount: Int = 0,
    val photosCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)




