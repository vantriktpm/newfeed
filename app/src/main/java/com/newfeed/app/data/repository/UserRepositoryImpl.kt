package com.newfeed.app.data.repository

import com.newfeed.app.data.local.dao.UserDao
import com.newfeed.app.data.local.entity.UserEntity
import com.newfeed.app.data.mapper.toDomain
import com.newfeed.app.domain.model.User
import com.newfeed.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { users ->
            users.map { it.toDomain() }
        }
    }
    
    override fun getUserById(userId: Long): Flow<User?> {
        return userDao.getUserById(userId).map { it?.toDomain() }
    }
    
    override suspend fun createUser(name: String, email: String, profileImageUrl: String?): Long {
        val user = UserEntity(
            name = name,
            email = email,
            profileImageUrl = profileImageUrl
        )
        return userDao.insertUser(user)
    }
    
    override suspend fun updateUser(user: User) {
        val entity = UserEntity(
            id = user.id,
            name = user.name,
            email = user.email,
            profileImageUrl = user.profileImageUrl,
            coverImageUrl = user.coverImageUrl,
            bio = user.bio,
            friendsCount = user.friendsCount,
            photosCount = user.photosCount,
            createdAt = user.createdAt
        )
        userDao.updateUser(entity)
    }
    
    override suspend fun deleteUser(userId: Long) {
        // Implementation for delete user
    }
    
    override suspend fun getCurrentUser(): User? {
        // For now, return user with ID 1 as the current user
        return userDao.getUserByIdSync(1L)?.toDomain()
    }
}



