package com.nbscollege_jenjosh.schdulix

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import java.util.Calendar


data class TimeSchedule (var indexTime: Int = 0, var time: String = "" );

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchedule( navController: NavController ) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    val mContext = LocalContext.current
    val mDate = remember { mutableStateOf("") }

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val mDateStartDate = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            startDate = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )
    val mDateEndDate = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            endDate = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    startDate = "Start Date"
    endDate = "End Date"
    time = "Time"

    val state = rememberScrollState()
    LaunchedEffect(Unit) { state.animateScrollTo(100) }

    var timeCount by remember { mutableStateOf (0) }

    var idfyState = 0
    val schedList = remember { mutableStateListOf<String>() }
    val inputvalue = remember { mutableStateOf( TextFieldValue() ) }

    var hourStr = ""
    var minuteStr = ""
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            hourStr = mHour.toString()
            minuteStr = mMinute.toString()

            schedList[idfyState] = "${hourStr.padStart(2,'0')}:${minuteStr.padStart(2,'0')}"
        }, mHour, mMinute, false
    )

    Scaffold (
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(state),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Add Schedule",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF6562DF),
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text(text = "Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = "Start",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                )
                Button(
                    onClick = { mDateStartDate.show() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(1.dp, Color(0xFF858585)),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Text(
                        text = startDate,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "End Date")
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = "End  ",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color(0xFF6562DF),
                )
                Button(
                    onClick = { mDateEndDate.show() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(1.dp, Color(0xFF858585)),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Text(
                        text = endDate,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "End Date")
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            schedList.forEachIndexed { index , setting ->
                var stringLabel = "";
                stringLabel = setting
                if (setting == ""){
                    stringLabel = "Time"
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = {
                            timeCount--
                            schedList.removeAt(index)
                        },
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                        ),
                        border = BorderStroke(1.dp, Color.Red),
                        shape = RoundedCornerShape(200.dp),
                    ) {
                        Text(
                            text = "X",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color.Red,
                        )
                    }
                    Button(
                        onClick = {
                            mTimePickerDialog.show()
                            idfyState = index
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                        ),
                        border = BorderStroke(1.dp, Color(0xFF858585)),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(
                            text = stringLabel,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(top = 10.dp, bottom = 10.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "End Date")
                    }
                }
            }

            Button(
                onClick = {
                    timeCount++

                    schedList.add(inputvalue.value.text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 0.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E1D6D)
                ),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "Add Time",
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White,
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Button(
                    onClick = {
                              navController.navigate(MainScreen.Home.name)
                    },
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6562DF)
                    ),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Text(
                        text = "Cancel",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.White,
                    )
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp,),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6562DF)
                    ),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Text(
                        text = "Save",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.White,
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}