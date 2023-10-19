package com.nbscollege_jenjosh.schdulix

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSchedule(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Scaffold (
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
            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                shape = RoundedCornerShape(10.dp),
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Time")
                },
                placeholder = { Text(text = "Time") },
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
            Text(
                text = "Duration",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(0xFF6562DF),
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
                OutlinedTextField(
                    value = startDate,
                    onValueChange = { startDate = it },
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Start Date")
                    },
                    placeholder = { Text(text = "Start Date") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    )
                )
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
                OutlinedTextField(
                    value = endDate,
                    onValueChange = { endDate = it },
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "End Date")
                    },
                    placeholder = { Text(text = "End Date") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black
                    )
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
                    onClick = { },
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