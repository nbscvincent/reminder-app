package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     * Retrieve all the users from the the given data source.
     */
    fun getAllUsersStream(): Flow<List<UserProfile>>

    /**
     * Retrieve an user from the given data source that matches with the [id].
     */
    suspend fun getUserStream(id: String): Flow<UserProfile?>

    /**
     * Retrieve an user and password.
     */
    suspend fun getUserPasswordStream(username: String, password:String): Flow<UserProfile?>

    /**
     * Insert user in the data source
     */
    suspend fun insertUser(user: UserProfile)

    /**
     * Delete user from the data source
     */
    suspend fun deleteUser(user: UserProfile)

    /**
     * Update user in the data source
     */
    suspend fun updateUser(user: UserProfile)
}