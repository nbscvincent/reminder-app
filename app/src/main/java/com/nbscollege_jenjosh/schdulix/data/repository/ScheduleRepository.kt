package com.nbscollege_jenjosh.schdulix.data.repository

import android.icu.text.CaseMap.Title
import com.nbscollege_jenjosh.schdulix.data.online.ResponseAPIDefault
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    suspend fun getAllScheduleStream(username: String = ""): Flow<List<ReminderModel>>
    //suspend fun insertSchedule(schedule: ReminderModel)
    suspend fun insertSchedule(schedule: ReminderModel, scheduledtl: List<AddTimeModel>): ResponseAPIDefault
    suspend fun insertScheduleTmp(time: AddTimeTmpModel)
    suspend fun deleteSchedule(username: String, title: String)
    suspend fun updateSchedule(schedule: ReminderModel) : ResponseAPIDefault
    suspend fun getScheduleStream(username: String, title: String): Flow<ReminderModel>

    suspend fun insertScheduleDtl(scheduledtl: List<AddTimeModel>)
    suspend fun deleteScheduleDtl(title: String)
    suspend fun getAllScheduleDtl(username: String, title: String): Flow<List<AddTimeModel>>
    suspend fun insertScheduleDtl(username: String, scheduledtl: AddTimeModel)
    suspend fun deleteScheduleDtl(username: String, title: String, id: Int?)
}