package com.nbscollege_jenjosh.schdulix.data.repository

import com.nbscollege_jenjosh.schdulix.data.ReminderModelDao
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.AddTimeTmpModeletails
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val timeTmpDAO: ReminderModelDao) : ScheduleRepository {
    override fun getAllScheduleStream(): Flow<List<ReminderModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }

    //override suspend fun insertScheduleTmp(time: AddTimeTmpModel) = timeTmpDAO.insertDtlTmp(time)
    override suspend fun insertScheduleTmp(time: AddTimeTmpModeletails) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateSchedule(user: ReminderModel) {
        TODO("Not yet implemented")
    }
}