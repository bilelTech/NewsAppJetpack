package com.test.newsappjetpack.domain.repository

import kotlinx.coroutines.flow.Flow


interface UserRepository {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}