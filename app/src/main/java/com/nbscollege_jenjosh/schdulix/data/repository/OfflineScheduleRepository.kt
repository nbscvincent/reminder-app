package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.data.ReminderModelDao
import com.nbscollege_jenjosh.schdulix.data.online.ResponseAPIDefault
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val reminderDao: ReminderModelDao) : ScheduleRepository {
    override suspend fun getAllScheduleStream(username: String): Flow<List<ReminderModel>> = reminderDao.getAllSchedule(username)
    override suspend fun insertSchedule(
        schedule: ReminderModel,
        scheduledtl: List<AddTimeModel>
    ): ResponseAPIDefault {
        TODO("Not yet implemented")
    }

    //override suspend fun insertSchedule(schedule: ReminderModel) = reminderDao.insert(schedule)

    override suspend fun insertScheduleTmp(time: AddTimeTmpModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(username: String, title: String) {
        TODO("Not yet implemented")
    }

    //override suspend fun deleteSchedule(schedule: ReminderModel) = reminderDao.delete(schedule)

    override suspend fun updateSchedule(schedule: ReminderModel) = reminderDao.update(schedule)

    override fun getScheduleStream(title: String): Flow<ReminderModel> = reminderDao.getSchedule(title)

    override suspend fun insertScheduleDtl(scheduledtl: List<AddTimeModel>) = reminderDao.insertdtl(scheduledtl)
    override suspend fun insertScheduleDtl(scheduledtl: AddTimeModel) = reminderDao.insertdtlLine(scheduledtl)

    override suspend fun deleteScheduleDtl(title: String) = reminderDao.deletedtl(title)
    override suspend fun deleteScheduleDtl(id: Int?) = reminderDao.deleteScheduleDtl(id)
    override suspend fun getAllScheduleDtl(
        username: String,
        title: String
    ): Flow<List<AddTimeModel>> {
        TODO("Not yet implemented")
    }

    //override fun getAllScheduleDtl(title: String): Flow<List<AddTimeModel>> = reminderDao.getAllScheduleDetail(title)

}