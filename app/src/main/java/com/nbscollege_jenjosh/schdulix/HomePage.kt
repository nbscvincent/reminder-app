package com.nbscollege_jenjosh.schdulix

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.nbscollege_jenjosh.schdulix.model.AddTimeModel
import com.nbscollege_jenjosh.schdulix.model.ReminderModel
import com.nbscollege_jenjosh.schdulix.model.reminderData
import com.nbscollege_jenjosh.schdulix.model.timeData
import com.nbscollege_jenjosh.schdulix.model.timeTmpData
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import com.nbscollege_jenjosh.schdulix.preferences.PreferencesManager
import com.nbscollege_jenjosh.schdulix.screens.confirmationScreen
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ReminderDetails
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ScheduleScreenViewModel
import com.nbscollege_jenjosh.schdulix.ui.theme.user.AppViewModelProvider
import com.nbscollege_jenjosh.schdulix.viewmodel.ScreenViewModel
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit
import java.util.logging.SimpleFormatter

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: ScheduleScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    timeTmpData.clear()
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val username = preferencesManager.getData("username", "")
    var isLoading by remember { mutableStateOf(true) }
    var isConfirm by remember { mutableStateOf(false) }
    
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit){
        viewModel.fetchSchedule(username)
        isLoading = false
    }
    //val schedItems by viewModel.scheduleList.collectAsState(initial = emptyList<ReminderModel>())
    val schedItems = viewModel.mainList

    /*Work Manager Start*/
    val application = LocalContext.current.applicationContext as Application
    val workManager = WorkManager.getInstance(application)

    /*val workBuilder = OneTimeWorkRequestBuilder<notificationReminder>()
    workBuilder.setInputData(
        workDataOf(
            "ID" to "SAMPLE1",
            "NAME" to "SAMPLE",
            "MESSAGE" to "You have a schedule task!",
        )
    )
    workManager.enqueue(workBuilder.build())*/

    var delReminder : ReminderModel? = null

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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "My Schdulix",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color(0xFF6562DF),
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(MainScreen.AddSchedule.name)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 25.dp, end = 25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6562DF)
                    ),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            if (schedItems.isEmpty()){
                if (isLoading) {
                    Text(
                        text = "Loading... Please wait....",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black,
                    )
                }else{
                    Text(
                        text = "No record found",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black,
                    )
                }
            }else {
                LazyColumn {
                    itemsIndexed(schedItems) { index, data ->
                        var isExpired by remember { mutableStateOf(false) }

                        val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")

                        val start = LocalDate.parse(data.startDate)
                        val end = LocalDate.parse(data.endDate)
                        val dateStart = start.format(formatter)
                        val dateEnd = end.format(formatter)

                        /*Work Manager Start*/
                        val schedStart = LocalDate.parse(data.startDate)
                        val schedEnd = LocalDate.parse(data.endDate)
                        val days = ChronoUnit.DAYS.between(LocalDate.now(), schedStart)
                        val daysEnd = ChronoUnit.DAYS.between(LocalDate.now(), schedEnd)
                        //val daysEnd = -1;

                        if (daysEnd < 0) {
                            isExpired = true

                            // kill the schedule
                            workManager.cancelAllWorkByTag("${data.id}")
                        } else {
                            val dueDate = Calendar.getInstance()
                            val currentDate = Calendar.getInstance()

                            // select the details here
                            //val listItem = viewModel.getAllScheduleDtl(username, data.title).collectAsState(initial = emptyList())
                            coroutineScope.launch {
                                viewModel.fetchScheduleDtl(username, data.id)
                            }
                            val listItem =
                                viewModel.scheduleListDtl.collectAsState(initial = emptyList())
                            listItem.value.forEach {
                                val str = it.time.split(':')

                                dueDate.set(Calendar.DAY_OF_MONTH, schedStart.dayOfMonth)
                                dueDate.set(Calendar.HOUR_OF_DAY, str[0].toInt())
                                dueDate.set(Calendar.MINUTE, str[1].toInt())
                                if (dueDate.before(currentDate)) {
                                    dueDate.add(Calendar.HOUR_OF_DAY, days.toInt())
                                }
                                var timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

                                val formatter =
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                                val localDate =
                                    LocalDateTime.parse("${data.startDate} ${it.time}", formatter);
                                val timeInMilliseconds =
                                    localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();

                                if (timeDiff > 0) {
                                    timeDiff -= (60000)
                                    val workBuilder =
                                        PeriodicWorkRequestBuilder<notificationReminder>(
                                            1,
                                            TimeUnit.DAYS
                                        )
                                            .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                                            .addTag("${data.id}")
                                            .setConstraints(
                                                Constraints.Builder()
                                                    .setRequiredNetworkType(NetworkType.UNMETERED)
                                                    .build()
                                            )
                                    workBuilder.setInputData(
                                        workDataOf(
                                            "ID" to "${it.id}1",
                                            "NAME" to data.title,
                                            "MESSAGE" to "You have a schedule task!",
                                        )
                                    )
                                    workManager.enqueueUniquePeriodicWork(
                                        "${it.id}1",
                                        //ExistingPeriodicWorkPolicy.KEEP,
                                        ExistingPeriodicWorkPolicy.UPDATE,
                                        workBuilder.build()
                                    )

                                    /*val workBuilder = OneTimeWorkRequestBuilder<notificationReminder>()
                                    workBuilder.setInputData(
                                        workDataOf(
                                            "ID" to "${it.id}1",
                                            "NAME" to data.title,
                                            "MESSAGE" to "You have a schedule task!",
                                        )
                                    )
                                    workManager.enqueue(workBuilder.build())
                                     */
                                }
                            }
                        }
                        /*Work Manager End*/

                        ElevatedCard(
                            onClick = {
                                navController.navigate("EditSchedule/${data.id}")
                            },
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 6.dp
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isExpired) Color.Red else Color.White,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp, top = 1.dp, bottom = 5.dp),
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 25.dp,
                                        end = 25.dp,
                                        top = 10.dp,
                                        bottom = 10.dp
                                    ),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = data.title,
                                        color = if (isExpired) Color.White else Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                    IconButton(
                                        onClick = {
                                            //delReminder = data
                                            //isConfirm = true

                                            // kill the schedule
                                            workManager.cancelAllWorkByTag("${data.id}")
                                            coroutineScope.launch {
                                                val schedUiState = viewModel.reminderUiState
                                                schedUiState.reminderDetails = ReminderDetails(
                                                    data.id,
                                                    data.title,
                                                    data.startDate,
                                                    data.endDate,
                                                )
                                                viewModel.deleteSchedule(username, data.id)
                                                navController.navigate(MainScreen.HomePage.name)
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "Delete",
                                            tint = if (isExpired) Color.White else Color.Black,
                                        )
                                    }
                                }
                                Text(
                                    text = dateStart.toString(),
                                    color = if (isExpired) Color.White else Color.Black,
                                )
                                Text(
                                    text = dateEnd.toString(),
                                    color = if (isExpired) Color.White else Color.Black,
                                )
                            }
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

