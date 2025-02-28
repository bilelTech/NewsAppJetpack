package com.test.newsappjetpack.domain.usecases

import com.test.newsappjetpack.domain.repository.UserRepository

class SaveAppEntryUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(){
        userRepository.saveAppEntry()
    }

}