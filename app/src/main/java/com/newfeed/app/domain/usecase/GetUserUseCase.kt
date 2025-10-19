package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.model.User
import com.newfeed.app.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: Long): Flow<User?> {
        return userRepository.getUserById(userId)
    }
}



