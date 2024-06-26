package com.nbscollege_jenjosh.schdulix

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import com.nbscollege_jenjosh.schdulix.preferences.PreferencesManager
import com.nbscollege_jenjosh.schdulix.screens.loadingScreen
import com.nbscollege_jenjosh.schdulix.screens.registrationAlert
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ReminderDetails
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ReminderTimeDetails
import com.nbscollege_jenjosh.schdulix.ui.theme.reminder.ScheduleScreenViewModel
import com.nbscollege_jenjosh.schdulix.ui.theme.user.AppViewModelProvider
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date


@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition", "SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditSchedule(
    navController: NavController,
    index: Int,
    viewModel: ScheduleScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    var title by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var stringLabel by remember { mutableStateOf("") }

    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    var padDay: String = ""
    var padMOnth: String = ""
    var intMonth = 0

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val currentDate = Calendar.getInstance()

    val mDateStartDate = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            padDay = mDayOfMonth.toString()
            if (mDayOfMonth.toString().length == 1){
                padDay = "0${mDayOfMonth.toString()}"
            }
            intMonth = mMonth + 1
            padMOnth = intMonth.toString()
            if (intMonth.toString().length == 1){
                padMOnth = "0${intMonth.toString()}"
            }
            startDate = "$mYear-$padMOnth-${padDay}"
        }, mYear, mMonth, mDay
    )
    val mDateEndDate = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            padDay = mDayOfMonth.toString()
            if (mDayOfMonth.toString().length == 1){
                padDay = "0${mDayOfMonth.toString()}"
            }
            intMonth = mMonth + 1
            padMOnth = intMonth.toString()
            if (intMonth.toString().length == 1){
                padMOnth = "0${intMonth.toString()}"
            }

            endDate = "$mYear-$padMOnth-${padDay}"
        }, mYear, mMonth, mDay
    )
    mDateStartDate.datePicker.minDate = currentDate.timeInMillis
    mDateEndDate.datePicker.minDate = currentDate.timeInMillis

    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    var hourStr = ""
    var minuteStr = ""
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            hourStr = mHour.toString()
            minuteStr = mMinute.toString()

            stringLabel = "${hourStr.padStart(2,'0')}:${minuteStr.padStart(2,'0')}"
        }, mHour, mMinute, false
    )
    val isLoading = remember { mutableStateOf(false) }
    if (isLoading.value){
        loadingScreen(isLoading.value) { isLoading.value = false }
    }

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val username = preferencesManager.getData("username", "")

    LaunchedEffect(Unit){
        isLoading.value = true
        viewModel.fetchScheduleDtl(username,index)
        viewModel.getSchedule(username, index).collect {
            title = it.title
            startDate = it.startDate
            endDate = it.endDate
        }
        isLoading.value = false
    }
    val listItem = viewModel.scheduleListDtl.collectAsState(initial = emptyList())

    var message = remember { mutableStateOf( "" ) }
    var isSuccess = remember { mutableStateOf( false ) }
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


    val format = SimpleDateFormat("HH:mm")
    val formatDisp = SimpleDateFormat("hh:mm a")

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Update Schedule",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF6562DF),
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(MainScreen.HomePage.name)
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
                actions = {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                isLoading.value = true
                                val addSchedUiState = viewModel.reminderUiState
                                addSchedUiState.reminderDetails = ReminderDetails(index,username,title,startDate,endDate,username)

                                val response = viewModel.updateSchedule()
                                isLoading.value = false
                                if (response != null) {
                                    if (response.flag == 1) {
                                        message.value = response.message
                                        showDialog.value = true
                                    } else {
                                        navController.navigate(MainScreen.HomePage.name)
                                    }
                                }else{
                                    message.value = "An error occured."
                                    showDialog.value = true
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors( containerColor = Color.White ),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Save",
                            tint = Color(0xFF6562DF),
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Save",
                            color = Color(0xFF6562DF),
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White),
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
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "Title") },
                label = { Text(text = "Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = startDate,
                onValueChange = { startDate = it },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "Start Date") },
                label = { Text(text = "Start Date") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp)
                    .clickable { mDateStartDate.show() },
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,

                    disabledTextColor = Color.Black,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Start Date")
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "End Date") },
                label = { Text(text = "End Date") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp)
                    .clickable { mDateEndDate.show() },
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,

                    disabledTextColor = Color.Black,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ),
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Start Date")
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = stringLabel,
                    onValueChange = { stringLabel = it },
                    shape = RoundedCornerShape(10.dp),
                    placeholder = { Text(text = "Time") },
                    label = { Text(text = "Time") },
                    modifier = Modifier
                        .clickable { mTimePickerDialog.show() },
                    enabled = false,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,
                        focusedLabelColor = Color.Black,

                        disabledTextColor = Color.Black,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    trailingIcon = {
                        Icon(imageVector = Icons.Filled.AccessTime, contentDescription = "Time")
                    }
                )
                Button(
                    onClick = {
                        if(stringLabel != "") {
                            coroutineScope.launch {
                                isLoading.value = true
                                val addSchedUiState = viewModel.reminderUiState

                                addSchedUiState.addSchedLine = ReminderTimeDetails(index, title,1,stringLabel)
                                viewModel.insertScheduleDetail(username)
                                stringLabel = "";

                                isLoading.value = false
                                navController.navigate("EditSchedule/${index}")
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6562DF),
                    ),
                    shape = RoundedCornerShape(50),
                ) {
                    Text(
                        text = "Add",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.White,
                    )
                }
            }
            LazyColumn{
                itemsIndexed(listItem.value){indexx, timeList ->
                    val dispDate = "${timeList.time}"
                    val dteDisp = format.parse(dispDate)
                    val dateDisplay = formatDisp.format(dteDisp)

                    ElevatedCard(
                        onClick = {  },
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
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                //text = timeList.time,
                                text = dateDisplay,
                                color = Color.Black
                            )
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        val addSchedUiState = viewModel.reminderUiState

                                        addSchedUiState.addSchedLine = ReminderTimeDetails(index,"",timeList.line)
                                        isLoading.value = true
                                        viewModel.deleteScheduleDtl(username)
                                        isLoading.value = false
                                        navController.navigate("EditSchedule/${index}")
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
