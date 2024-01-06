package com.nbscollege_jenjosh.schdulix.ui.theme.reminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbscollege_jenjosh.schdulix.data.repository.ScheduleRepository
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
        }
    }

    /*suspend fun getAllSchedule() : Flow<List<ReminderModel>> {
        return scheduleRepository.getAllScheduleStream()
    }*/
    suspend fun getAllSchedule(){
        scheduleRepository.getAllScheduleStream().collect{
            reminderUiState.reminderDetailsList = it
        }
    }

    private fun validateInput(uiState: ReminderDetails = reminderUiState.reminderDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank()
        }
    }

}
/**
 * Represents Ui State for a Schedule
 */
data class ScheduleUiState(
    var reminderDetails: ReminderDetails = ReminderDetails(),
    val isEntryValid: Boolean = false,
    var reminderDetailsList: List<ReminderModel> = emptyList()
)
data class ReminderDetails(
    val title: String = "",
    val startDate: String = "",
    val endDate: String = "",
)

fun ReminderDetails.toReminder(): ReminderModel = ReminderModel(
    title = title,
    startDate = startDate,
    endDate = endDate,
)
