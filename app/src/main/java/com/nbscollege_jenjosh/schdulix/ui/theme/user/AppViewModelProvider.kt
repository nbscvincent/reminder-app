package com.nbscollege_jenjosh.schdulix.ui.theme.user

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nbscollege_jenjosh.schdulix.Schdulix
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ScheduleScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LoginScreenViewModel(
                schdulixApplication().container.userRepository
            )
            RegistrationScreenViewModel(
                schdulixApplication().container.userRepository
            )
        }
        initializer {
            ScheduleScreenViewModel(
                schdulixApplication().container.timeTmp
            )
        }
    }
}
fun CreationExtras.schdulixApplication(): Schdulix =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Schdulix)
