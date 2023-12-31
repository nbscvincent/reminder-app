package com.nbscollege_jenjosh.schdulix.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class ReminderModel(val title: String, val startDate: String, val endDate: String, val timeList: SnapshotStateList<AddTimeModel>)
//data class AddTimeModel (val time: String)
var reminderData = mutableStateListOf<ReminderModel>()
var timeData = mutableStateListOf<AddTimeModel>()

@Entity(tableName = "schedule")
data class ReminderModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
)

@Entity(tableName = "time")
data class AddTimeModel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val head_id: Int,
    val time: String
)

@Entity(tableName = "time_tmp")
data class AddTimeTmpModel (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val time: String
)

