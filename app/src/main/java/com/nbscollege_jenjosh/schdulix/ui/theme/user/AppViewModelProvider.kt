package com.nbscollege_jenjosh.schdulix.ui.theme.user

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nbscollege_jenjosh.schdulix.Schdulix

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
    }
}
fun CreationExtras.schdulixApplication(): Schdulix =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Schdulix)
