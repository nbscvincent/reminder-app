package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.data.UserProfileDao
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserProfileDao) : UserRepository {
    override fun getAllUsersStream(): Flow<List<UserProfile>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<UserProfile?> = userDao.getUsers(id)

    override suspend fun insertUser(user: UserProfile) = userDao.insert(user)
    override suspend fun deleteUser(user: UserProfile) = userDao.delete(user)

    override suspend fun updateUser(user: UserProfile) = userDao.update(user)
}
