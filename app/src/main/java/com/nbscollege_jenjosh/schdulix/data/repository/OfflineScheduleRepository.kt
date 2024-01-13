package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.data.ReminderModelDao
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
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

    override suspend fun deleteSchedule(schedule: ReminderModel) = reminderDao.delete(schedule)

    override suspend fun updateSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }

    override fun getScheduleStream(title: String): Flow<ReminderModel> = reminderDao.getSchedule(title)

    override suspend fun insertScheduleDtl(scheduledtl: List<AddTimeModel>) = reminderDao.insertdtl(scheduledtl)
    override suspend fun deleteScheduleDtl(title: String) = reminderDao.deletedtl(title)
    override fun getAllScheduleDtl(title: String): Flow<List<AddTimeModel>> = reminderDao.getAllScheduleDetail(title)

}