package com.nbscollege_jenjosh.schdulix.data.repository

import android.icu.text.CaseMap.Title
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getAllScheduleStream(): Flow<List<ReminderModel>>
    suspend fun insertSchedule(schedule: ReminderModel)
    suspend fun insertScheduleTmp(time: AddTimeTmpModel)
    suspend fun deleteSchedule(user: ReminderModel)
    suspend fun updateSchedule(user: ReminderModel)
    fun getScheduleStream(title: String): Flow<ReminderModel>

    suspend fun insertScheduleDtl(scheduledtl: List<AddTimeModel>)
    suspend fun deleteScheduleDtl(title: String)
    fun getAllScheduleDtl(title: String): Flow<List<AddTimeModel>>
}