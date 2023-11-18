package com.nbscollege_jenjosh.schdulix.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.nbscollege_jenjosh.schdulix.viewmodel.ScreenViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbscollege_jenjosh.schdulix.AddSchedule
import com.nbscollege_jenjosh.schdulix.EditSchedule
import com.nbscollege_jenjosh.schdulix.ForgotPassword
import com.nbscollege_jenjosh.schdulix.HomePage
import com.nbscollege_jenjosh.schdulix.LoginScreen
import com.nbscollege_jenjosh.schdulix.Profile
import com.nbscollege_jenjosh.schdulix.RegistrationScreen
import com.nbscollege_jenjosh.schdulix.SplashScreen
import com.nbscollege_jenjosh.schdulix.navigation.routes.MainScreen
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchdulixApp (
    screenViewModel: ScreenViewModel
){
    val navController: NavHostController = rememberNavController()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = MainScreen.Splash.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = MainScreen.Splash.name) {
                SplashScreen( navController, screenViewModel )
            }
            composable(route = MainScreen.CheckLogin.name) {
                CheckLogin( screenViewModel )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckLogin( screenViewModel: ScreenViewModel ){
    val navController: NavHostController = rememberNavController()
    var isLogin = screenViewModel.checkLogin()

    if (!isLogin){
        MainLogin(navController, screenViewModel)
    }else{
        MainHomeScreen(navController, screenViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLogin(
    navController: NavHostController,
    screenViewModel: ScreenViewModel
){
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
        }
        NavHost(
            navController = navController,
            startDestination = MainScreen.Login.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = MainScreen.Login.name) {
                LoginScreen( navController, screenViewModel )
            }
            composable(route = MainScreen.RegistrationScreen.name) {
                RegistrationScreen( navController )
            }
            composable(route = MainScreen.HomePage.name) {
                HomePage( navController, screenViewModel )
            }
            composable(route = MainScreen.CheckLogin.name) {
                CheckLogin( screenViewModel )
            }
            composable(route = MainScreen.Splash.name) {
                SplashScreen( navController, screenViewModel )
            }
            composable(route = MainScreen.Forgot.name) {
                ForgotPassword( navController )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(
    navController: NavHostController,
    screenViewModel: ScreenViewModel
){
    Scaffold (
        bottomBar = { }
    ){
        Column(modifier = Modifier.padding(it)) {
        }
        NavHost(
            navController = navController,
            //startDestination = MainScreen.Login.name,
            startDestination = MainScreen.HomePage.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = MainScreen.Login.name) {
                LoginScreen( navController, screenViewModel )
            }
            composable(route = MainScreen.RegistrationScreen.name) {
                RegistrationScreen( navController )
            }
            composable(route = MainScreen.AddSchedule.name) {
                AddSchedule( navController )
            }
            composable(route = MainScreen.HomePage.name) {
                HomePage( navController, screenViewModel )
            }
            composable(route = MainScreen.Profile.name) {
                Profile( navController, screenViewModel )
            }
            composable(route = MainScreen.CheckLogin.name) {
                CheckLogin( screenViewModel )
            }
            composable(route = MainScreen.Splash.name) {
                SplashScreen( navController, screenViewModel )
            }
            composable("EditSchedule/{bid}") { navBackStackEntry ->
                val bid = navBackStackEntry.arguments?.getString("bid")

                bid?.let {
                    EditSchedule(navController = navController, index = bid.toInt())
                }
            }
        }
    }
}