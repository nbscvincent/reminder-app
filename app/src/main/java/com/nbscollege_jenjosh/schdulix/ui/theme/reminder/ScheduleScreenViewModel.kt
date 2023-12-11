package com.nbscollege_jenjosh.schdulix.ui.theme.reminder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbscollege_jenjosh.schdulix.data.repository.ScheduleRepository

class ScheduleScreenViewModel(private val scheduleRepository: ScheduleRepository ): ViewModel() {
    /**
     * Holds current user ui state
     */
    var reminderUiState by mutableStateOf(ScheduleUiState())
        private set
    var reminderDtlUiState by mutableStateOf(ScheduleDtlUiState())
        private set
    var reminderDtlTmpUiState by mutableStateOf(ScheduleDtlTmpUiState())
        private set

    fun updateUiState(reminderDetails: ReminderDetails) {
        reminderUiState =
            ScheduleUiState(reminderDetails = reminderDetails, isEntryValid = validateInput(reminderDetails))
    }
    fun updateDtlUiState(reminderDtlDetails: ReminderDetailsDtl) {
        reminderDtlUiState =
            ScheduleDtlUiState(reminderDtlDetails = reminderDtlDetails, isEntryValid = validateDtlInput(reminderDtlDetails))
    }
    fun updateDtlTmpUiState(reminderDtlTmpDetails: AddTimeTmpModeletails) {
        reminderDtlTmpUiState =
            ScheduleDtlTmpUiState(reminderDtlTmpDetails = reminderDtlTmpDetails, isEntryValid = validateDtlTmpInput(reminderDtlTmpDetails))
    }

    private fun validateInput(uiState: ReminderDetails = reminderUiState.reminderDetails): Boolean {
        return with(uiState) {
            title.isNotBlank() && startDate.isNotBlank() && endDate.isNotBlank()
        }
    }
    private fun validateDtlInput(uiState: ReminderDetailsDtl = reminderDtlUiState.reminderDtlDetails): Boolean {
        return with(uiState) {
            time.isNotBlank()
        }
    }
    private fun validateDtlTmpInput(uiState: AddTimeTmpModeletails = reminderDtlTmpUiState.reminderDtlTmpDetails): Boolean {
        return with(uiState) {
            time.isNotBlank()
        }
    }

    suspend fun saveTimeTmp() {
        if (validateDtlTmpInput()) {
            scheduleRepository.insertScheduleTmp(reminderDtlTmpUiState.reminderDtlTmpDetails.toTime())
        }
    }

}
/**
 * Represents Ui State for a Schedule
 */
data class ScheduleUiState(
    var reminderDetails: ReminderDetails = ReminderDetails(),
    val isEntryValid: Boolean = false
)
data class ReminderDetails(
    val title: String = "",
    val startDate: String = "",
    val endDate: String = "",
)

data class ScheduleDtlUiState(
    var reminderDtlDetails: ReminderDetailsDtl = ReminderDetailsDtl(),
    val isEntryValid: Boolean = false
)
data class ReminderDetailsDtl(
    val time: String = ""
)

data class ScheduleDtlTmpUiState(
    var reminderDtlTmpDetails: AddTimeTmpModeletails = AddTimeTmpModeletails(),
    val isEntryValid: Boolean = false
)
data class AddTimeTmpModeletails(
    val time: String = ""
)

fun AddTimeTmpModeletails.toTime(): AddTimeTmpModeletails = AddTimeTmpModeletails(
    time = time,
)