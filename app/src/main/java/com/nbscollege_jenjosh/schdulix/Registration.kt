package com.nbscollege_jenjosh.schdulix

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.model.UserProfile
import com.nbscollege_jenjosh.schdulix.model.checkLogin
import com.nbscollege_jenjosh.schdulix.model.userList
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import com.nbscollege_jenjosh.schdulix.screens.registrationAlert
import com.nbscollege_jenjosh.schdulix.ui.theme.user.AppViewModelProvider
import com.nbscollege_jenjosh.schdulix.ui.theme.user.LoginScreenViewModel
import com.nbscollege_jenjosh.schdulix.ui.theme.user.RegistrationScreenViewModel
import com.nbscollege_jenjosh.schdulix.ui.theme.user.UserDetails
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var message = remember { mutableStateOf( "" ) }
    var isSuccess = remember { mutableStateOf( false ) }
    var passwordShow: Boolean by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    val showDialog = remember { mutableStateOf( false ) }
    if (showDialog.value){
        registrationAlert(
            message = message,
            showDialog = showDialog.value,
            isSuccess = isSuccess.value,
            navController = navController,
        ) {
            showDialog.value = false
            if (isSuccess.value) {
                navController.navigate(MainScreen.Splash.name)
            }
        }
    }


    Scaffold (
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.schdulix_logo),
                contentDescription = "Schdulix",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp)
            )
            Text(
                text = "Register to have an account",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF6562DF),
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
                },
                placeholder = { Text(text = "Username") },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(10.dp),
                /*trailingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
                },*/
                placeholder = { Text(text = "Strong Password") },
                label = { Text(text = "Strong Password") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                ),
                // visualTransformation = PasswordVisualTransformation()
                trailingIcon = {
                    val image = if (passwordShow)
                        Icons.Filled.Visibility
                    else
                        Icons.Filled.VisibilityOff

                    val description = if (passwordShow) "Hide Password" else "Show Password"

                    IconButton(onClick = {
                        passwordShow = !passwordShow
                    }) {
                        Icon(imageVector = image, contentDescription =  description)
                    }
                },
                singleLine = false,
                visualTransformation = if (passwordShow) VisualTransformation.None else PasswordVisualTransformation(),

            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "First Name")
                },
                placeholder = { Text(text = "First Name") },
                label = { Text(text = "First Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Last Name")
                },
                placeholder = { Text(text = "Last Name") },
                label = { Text(text = "Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        // check if user is existing


                        // save data
                        val userUiState = viewModel.userUiState
                        userUiState.userDetails = UserDetails(username,password,firstName,lastName)
                        viewModel.saveUser()
                        //Log.i("userUiState", userUiState.userDetails.toString())
                    }
                    //navController.navigate(MainRoute.MainScreen.name)
                },
                /*onClick = {
                    // check if user is existing
                    if (checkLogin(username)){
                        message.value = "User already exist"
                        showDialog.value = true
                    }else {
                        message.value = "Successfully registered"
                        showDialog.value = true
                        isSuccess.value = true
                        Register(username, password, firstName, lastName)
                    }
                },*/
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6562DF)
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "SIGN UP",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    navController.navigate(MainScreen.Splash.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6562DF)
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "BACK",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
        }
    }
}
fun Register(username: String, password: String, firstName: String, lastName: String) {
    val user = UserProfile(username, password, firstName, lastName)
    userList.add(user)
}





