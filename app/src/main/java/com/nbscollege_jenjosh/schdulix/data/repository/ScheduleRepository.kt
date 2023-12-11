package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.AddTimeTmpModeletails
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getAllScheduleStream(): Flow<List<ReminderModel>>

    /**
     * Insert user in the data source
     */
    suspend fun insertSchedule(user: ReminderModel)
    suspend fun insertScheduleTmp(time: AddTimeTmpModeletails)

    /**
     * Delete user from the data source
     */
    suspend fun deleteSchedule(user: ReminderModel)

    /**
     * Update user in the data source
     */
    suspend fun updateSchedule(user: ReminderModel)
}