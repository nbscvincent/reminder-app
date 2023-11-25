package com.nbscollege_jenjosh.schdulix.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class ReminderModel(val title: String, val startDate: String, val endDate: String, val timeList: SnapshotStateList<AddTimeModel>)
data class AddTimeModel (val time: String)
var reminderData = mutableStateListOf<ReminderModel>()
var timeData = mutableStateListOf<AddTimeModel>()
