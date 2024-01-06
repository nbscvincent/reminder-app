package com.nbscollege_jenjosh.schdulix

import android.annotation.SuppressLint
import android.provider.CalendarContract.Colors
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.model.reminderData
import com.nbscollege_jenjosh.schdulix.model.timeData
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ScheduleScreenViewModel
import com.nbscollege_jenjosh.schdulix.ui.theme.user.AppViewModelProvider
import com.nbscollege_jenjosh.schdulix.viewmodel.ScreenViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: ScheduleScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val homePageState = viewModel.reminderUiState

    coroutineScope.launch{
        viewModel.getAllSchedule()
    }
    
    Scaffold (
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
                            coroutineScope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color(0xFF6562DF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(MainScreen.Profile.name)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile",
                            tint = Color(0xFF6562DF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
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
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "My Schdulix",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color(0xFF6562DF),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 25.dp, end = 25.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn {
                itemsIndexed(homePageState.reminderDetailsList) { index, data ->
                    ElevatedCard(
                        onClick = {
                            navController.navigate("EditSchedule/$index")
                        },
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 1.dp, bottom = 5.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp, top = 10.dp, bottom = 10.dp),
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = data.title,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                                IconButton(
                                    onClick = {
                                        reminderData.removeAt(index)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.Black
                                    )
                                }
                            }
                            Text(
                                text = data.startDate,
                                color = Color.Black
                            )
                            Text(
                                text = data.endDate,
                                color = Color.Black
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = {
                    navController.navigate(MainScreen.AddSchedule.name)
                },
                modifier = Modifier
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6562DF)
                ),
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Add New",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 0.dp, end = 8.dp, top = 10.dp, bottom = 10.dp),
                )
            }
            Spacer(modifier = Modifier.height(200.dp))
            /*ExtendedFloatingActionButton(
                text = { Text(text = "Add New") },
                icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Add",) },
                onClick = { navController.navigate(MainScreen.AddSchedule.name) },
                containerColor = Color(0xFF6562DF)
            )*/
        }
    }
}
