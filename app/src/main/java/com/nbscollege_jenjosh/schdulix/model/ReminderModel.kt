package com.nbscollege_jenjosh.schdulix.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//data class ReminderModel(val title: String, val startDate: String, val endDate: String, val timeList: SnapshotStateList<AddTimeModel>)
data class TimeTmpModel (val time: String)
var reminderData = mutableStateListOf<ReminderModel>()
var timeData = mutableStateListOf<AddTimeModel>()
var timeTmpData = mutableStateListOf<TimeTmpModel>()

@Entity(tableName = "schedule")
data class ReminderModel(
    @PrimaryKey val title: String,
    val startDate: String,
    val endDate: String,
)

@Entity(tableName = "time")
data class AddTimeModel (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String,
    val line: Int,
    val time: String
)

@Entity(tableName = "time_tmp")
data class AddTimeTmpModel (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val time: String
)

