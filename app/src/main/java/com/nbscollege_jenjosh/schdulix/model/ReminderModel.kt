package com.nbscollege_jenjosh.schdulix.model

data class ReminderModel (val title: String, val startDate: String, val endDate: String)
data class AddTimeModel (val time: String)
var reminderData = mutableListOf<ReminderModel>()
var timeData = mutableListOf<AddTimeModel>()
