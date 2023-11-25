package com.nbscollege_jenjosh.schdulix.data

import android.content.Context
import com.nbscollege_jenjosh.schdulix.data.repository.OfflineUserRepository
import com.nbscollege_jenjosh.schdulix.data.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository

    class AppDataContainer(private val context: Context) : AppContainer {
        /**
         * Implementation for [userRepository]
         */
        override val userRepository: UserRepository by lazy {
            OfflineUserRepository(SchdulixDatabase.getDatabase(context).UserProfileDao())
        }
    }
}