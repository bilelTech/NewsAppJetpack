package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(private val userRepository: UserRepository) {
    operator fun invoke(): Flow<Boolean> {
        return userRepository.readAppEntry()
    }
}