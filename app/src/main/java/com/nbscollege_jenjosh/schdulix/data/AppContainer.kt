package com.nbscollege_jenjosh.schdulix.data

import android.content.Context
import com.nbscollege_jenjosh.schdulix.data.repository.OfflineScheduleRepository
import com.nbscollege_jenjosh.schdulix.data.repository.OfflineUserRepository
import com.nbscollege_jenjosh.schdulix.data.repository.ScheduleRepository
import com.nbscollege_jenjosh.schdulix.data.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val timeTmp: ScheduleRepository

    class AppDataContainer(private val context: Context) : AppContainer {
        /**
         * Implementation for [userRepository]
         */
        override val userRepository: UserRepository by lazy {
            OfflineUserRepository(SchdulixDatabase.getDatabase(context).UserProfileDao())
        }
        override val timeTmp: ScheduleRepository by lazy {
            OfflineScheduleRepository(SchdulixDatabase.getDatabase(context).ReminderModelDao())
        }
    }
}