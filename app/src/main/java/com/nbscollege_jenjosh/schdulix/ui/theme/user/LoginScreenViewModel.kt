package com.nbscollege_jenjosh.schdulix.ui.theme.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nbscollege_jenjosh.schdulix.data.repository.UserRepository
import com.nbscollege_jenjosh.schdulix.model.UserProfile

class LoginScreenViewModel(private val usersRepository: UserRepository) : ViewModel() {

    /**
     * Holds current user ui state
     */
    var userUiState by mutableStateOf(UserUiState())
        private set
    /**
     * Updates the [userUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(userDetails: UserDetails) {
        userUiState =
            UserUiState(userDetails = userDetails, isEntryValid = validateInput(userDetails))
    }
    /**
     * Inserts an [User] in the Room database
     */
    suspend fun selectUser(username: String, password: String) {
        if (validateInput()) {
            usersRepository.getUserPasswordStream(username, password)
        }
    }
    private fun validateInput(uiState: UserDetails = userUiState.userDetails): Boolean {
        return with(uiState) {
            username.isNotBlank() && password.isNotBlank()
        }
    }
}
/**
 * Represents Ui State for an User.
 */
data class UserUiState(
    var userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)
data class UserDetails(
    val username: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = ""
)
/**
 * Extension function to convert [UserUiState] to [User]. If the value of [UserDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [UserUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun UserDetails.toUser(): UserProfile = UserProfile(
    username = username,
    password = password,
    firstName = firstName,
    lastName = lastName
)
/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun UserProfile.toUserUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)
/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun UserProfile.toUserDetails(): UserDetails = UserDetails(
    username = username,
    password  = password,
    firstName = firstName,
    lastName = lastName
)