package com.nbscollege_jenjosh.schdulix.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.nbscollege_jenjosh.schdulix.viewmodel.ScreenViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege_jenjosh.schdulix.AddSchedule
import com.nbscollege_jenjosh.schdulix.HomePage
import com.nbscollege_jenjosh.schdulix.LoginScreen
import com.nbscollege_jenjosh.schdulix.Profile
import com.nbscollege_jenjosh.schdulix.RegistrationScreen
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchdulixApp (
    viewModel: ScreenViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
        }
        NavHost(
            navController = navController,
            //startDestination = MainScreen.Login.name,
            //startDestination = MainScreen.AddSchedule.name,
            startDestination = MainScreen.Home.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = MainScreen.Login.name) {
                LoginScreen( navController )
            }
            composable(route = MainScreen.RegistrationScreen.name) {
                RegistrationScreen( navController )
            }
            composable(route = MainScreen.AddSchedule.name) {
                AddSchedule( navController )
            }
            composable(route = MainScreen.Home.name) {
                HomePage( navController )
            }
            composable(route = MainScreen.Profile.name) {
                Profile( navController )
            }
        }
    }
}