package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getAllScheduleStream(): Flow<List<ReminderModel>>
    suspend fun insertSchedule(schedule: ReminderModel)
    suspend fun insertScheduleTmp(time: AddTimeTmpModel)
    suspend fun deleteSchedule(user: ReminderModel)
    suspend fun updateSchedule(user: ReminderModel)
}