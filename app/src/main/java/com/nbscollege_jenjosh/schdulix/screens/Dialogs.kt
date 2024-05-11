package com.nbscollege_jenjosh.schdulix.screens

import android.app.Dialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen

@Composable
fun Dialogs(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
           TextButton(onClick = { onConfirmation() }) {
               Text("OK")
           }
        },
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }

    )
}

@Composable
fun loginAlert(
    showDialog: Boolean,
    onDismiss: () -> Unit
){
    if (showDialog){
        AlertDialog(
            onDismissRequest = { onDismiss },
            confirmButton = {
                TextButton(onClick = onDismiss ) {
                    Text("OK")
                }
            },
            text = {
                Text(text = "Invalid Username and Password!")
            },
            icon = {
                Icon(imageVector = Icons.Filled.Info , contentDescription = "")
            }
        )
    }
}

@Composable
fun registrationAlert(
    message: MutableState<String>,
    showDialog: Boolean,
    isSuccess: Boolean,
    navController: NavController,
    onDismiss: () -> Unit
){
    if (showDialog){
        AlertDialog(
            onDismissRequest = { onDismiss },
            confirmButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("OK")
                }
            },
            title = {
                Text("")
            },
            text = {
                Text(text = message.value)
            },
            icon = {
                Icon(imageVector = Icons.Filled.Info , contentDescription = "")
            }
        )
    }
}

@Composable
fun loadingScreen(
    showDialog: Boolean,
    onDismiss: () -> Unit
){
    if (showDialog){
        AlertDialog(
            onDismissRequest = {  },
            confirmButton = { },
            text = {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ){
                    CircularProgressIndicator()
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Loading.... Please wait....."
                    )
                }
            },
            icon = {
                Icon(imageVector = Icons.Filled.Info , contentDescription = "")
            }
        )
    }
}