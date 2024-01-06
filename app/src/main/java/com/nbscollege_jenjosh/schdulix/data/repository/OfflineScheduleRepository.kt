package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.data.ReminderModelDao
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val reminderDao: ReminderModelDao) : ScheduleRepository {
    override fun getAllScheduleStream(): Flow<List<ReminderModel>> = reminderDao.getAllSchedule()
    override suspend fun insertSchedule(schedule: ReminderModel) = reminderDao.insert(schedule)
    override suspend fun insertScheduleTmp(time: AddTimeTmpModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }

}