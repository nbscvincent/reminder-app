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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.model.userList
import com.nbscollege_jenjosh.schdulix.model.usernameIndex
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import com.nbscollege_jenjosh.schdulix.preferences.PreferencesManager
import com.nbscollege_jenjosh.schdulix.viewmodel.ScreenViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    navController: NavController,
    screenViewModel: ScreenViewModel
) {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

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
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(MainScreen.HomePage.name)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Close",
                            tint = Color(0xFF6562DF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
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
                text = "My Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color(0xFF6562DF),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 25.dp, end = 25.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Username",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
                Text(
                    //text = userList[usernameIndex].username,
                    text = preferencesManager.getData("username", ""),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "First Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
                Text(
                    //text = userList[usernameIndex].firstName,
                    text = preferencesManager.getData("firstName", ""),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Last Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
                Text(
                    //text = userList[usernameIndex].lastName,
                    text = preferencesManager.getData("lastName", ""),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(end = 25.dp)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    screenViewModel.unsetLogin()

                    preferencesManager.saveData("login", "")
                    preferencesManager.saveData("username", "")
                    preferencesManager.saveData("firstName", "")
                    preferencesManager.saveData("lastName", "")

                    navController.navigate(MainScreen.Splash.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6562DF)
                ),
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout", tint = Color.White)
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Sign Out",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 0.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
                )
            }
        }
    }
}