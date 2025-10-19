package com.newfeed.app.domain.repository

import com.newfeed.app.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<List<User>>
    fun getUserById(userId: Long): Flow<User?>
    suspend fun createUser(name: String, email: String, profileImageUrl: String?): Long
    suspend fun updateUser(user: User)
    suspend fun deleteUser(userId: Long)
    suspend fun getCurrentUser(): User?
}



