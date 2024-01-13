package com.nbscollege_jenjosh.schdulix.ui.theme.reminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbscollege_jenjosh.schdulix.data.repository.ScheduleRepository
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.AddTimeTmpModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import com.nbscollege_jenjosh.schdulix.ui.theme.user.UserDetails
import com.nbscollege_jenjosh.schdulix.ui.theme.user.toUser
import kotlinx.coroutines.flow.Flow

class ScheduleScreenViewModel(private val scheduleRepository: ScheduleRepository ): ViewModel() {
    /**
     * Holds current user ui state
     */
    var reminderUiState by mutableStateOf(ScheduleUiState())
        private set

    suspend fun insertSchedule() {
        if (validateInput()) {
            scheduleRepository.insertSchedule(reminderUiState.reminderDetails.toReminder())
            scheduleRepository.insertScheduleDtl(reminderUiState.detailTime.toList())
        }
    }

    fun getSchedule(title: String): Flow<ReminderModel> {
        return scheduleRepository.getScheduleStream(title)
    }

    suspend fun getAllSchedule(){
        scheduleRepository.getAllScheduleStream().collect{
            reminderUiState.reminderDetailsList = it
        }
    }
    val schedItems = scheduleRepository.getAllScheduleStream()

    private fun validateInput(uiState: ReminderDetails = reminderUiState.reminderDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank()
        }
    }

    suspend fun deleteSchedule(){
        scheduleRepository.deleteSchedule(reminderUiState.reminderDetails.toReminder())
        scheduleRepository.deleteScheduleDtl(reminderUiState.reminderDetails.title)
    }

    fun getAllScheduleDtl(title: String): Flow<List<AddTimeModel>> {
        return scheduleRepository.getAllScheduleDtl(title)
    }

}
/**
 * Represents Ui State for a Schedule
 */
data class ScheduleUiState(
    var reminderDetails: ReminderDetails = ReminderDetails(),
    val isEntryValid: Boolean = false,
    var reminderDetailsList: List<ReminderModel> = emptyList(),
    var detailTime: List<AddTimeModel> = emptyList(),
)
data class ReminderDetails(
    val title: String = "",
    val startDate: String = "",
    val endDate: String = "",
)
data class ReminderTimeDetails(
    val id: Int = 0,
    val title: String = "",
    val line: Int = 0,
    val time: String = "",
)

fun ReminderDetails.toReminder(): ReminderModel = ReminderModel(
    title = title,
    startDate = startDate,
    endDate = endDate,
)
fun ReminderTimeDetails.toReminder(): AddTimeModel = AddTimeModel(
    id = id,
    title = title,
    line = line,
    time = time
)
