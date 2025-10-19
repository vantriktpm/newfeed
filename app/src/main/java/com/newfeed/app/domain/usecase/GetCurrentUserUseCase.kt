package com.newfeed.app.domain.usecase

import com.newfeed.app.domain.model.User
import com.newfeed.app.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): User? {
        return userRepository.getCurrentUser()
    }
}



