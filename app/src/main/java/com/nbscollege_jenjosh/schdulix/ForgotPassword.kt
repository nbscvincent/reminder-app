package com.nbscollege_jenjosh.schdulix

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.schdulix_logo),
                        contentDescription = "Schdulix",
                        modifier = Modifier
                            .height(40.dp)
                            .width(150.dp)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(MainScreen.Login.name)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF6562DF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Forgot Password",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color(0xFF6562DF),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 25.dp, end = 25.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Send code to email",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color(0xFF6562DF),
                modifier = Modifier
                    .padding(end = 25.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                },
                placeholder = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6562DF)
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "Verify",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}