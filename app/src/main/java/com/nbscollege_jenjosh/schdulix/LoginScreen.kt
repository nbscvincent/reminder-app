package com.nbscollege_jenjosh.schdulix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import com.nbscollege_jenjosh.schdulix.ui.theme.SchdulixTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen( navController: NavController ) {
    var email by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    val isChecked = remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(120.dp),
                containerColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp)
                        .fillMaxSize()
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Button(
                        onClick = { navController.navigate(MainScreen.HomePage.name) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6562DF)
                        ),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = "Sign In",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Don't have account? ",
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = Color.Black,
                        )
                        Text(
                            text = "Sign Up",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.Red,
                        )
                        TextButton(onClick = { navController.navigate(MainScreen.RegistrationScreen.name) }) {
                            Text(
                                text = "Sign Up",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.Red,
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Spacer(modifier = Modifier.height(0.dp))
            Image(
                painter = painterResource(id = R.drawable.schdulix_loginphoto),
                contentDescription = "Schdulix",
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = "Welcome back!",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.Black
            )
            Text(
                text = "Sign in to access your account",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                value = email,
                shape = RoundedCornerShape(10.dp),
                onValueChange = { email = it },
                label = { Text(text = "Enter your email") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6562DF),
                    unfocusedBorderColor = Color(0xFF6562DF)),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                value = password,
                shape = RoundedCornerShape(10.dp),
                onValueChange = {},
                label = { Text(text = "Password") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF6562DF),
                    unfocusedBorderColor = Color(0xFF6562DF)),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = { isChecked.value = it },
                        enabled = true,
                        colors = CheckboxDefaults.colors(Color(0xFF6562DF))
                    )
                    Text(
                        text = "Remember me",
                        color = Color.Black
                    )
                }
                Text(
                    color = Color.Red,
                    text = "Forgot password?"
                )
            }
        }
    }
}

data class LoginRequest( val username: String, val password: String )