package com.nbscollege_jenjosh.schdulix.screens

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.nbscollege_jenjosh.schdulix.preferences.PreferencesManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val data = preferencesManager.getData("login", "")

    if (data == ""){
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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
                HomePage( navController, drawerState )
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val openDrawer: () -> Unit = { scope.launch { drawerState.open() } }
    val closeDrawer: () -> Unit = { scope.launch { drawerState.close() } }

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }

    BackHandler(
        enabled = drawerState.isOpen,
    ) {
        closeDrawer()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "SCHDULIX", //logo instead of text or
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
                NavigationDrawerItem(
                    label = {
                        Text(text = "My Profile") //put "profile" icon
                            },
                    selected = false,
                    onClick = {
                        closeDrawer()
                        navController.navigate(MainScreen.Profile.name)
                    }
                )
                NavigationDrawerItem(
                    label = {
                        Text(text = "My Schedules")
                    },
                    selected = false,
                    onClick = {
                        closeDrawer()
                        navController.navigate(MainScreen.HomePage.name)
                    }
                )
                NavigationDrawerItem(
                    label = {
                        Text(text = "New Schedule")
                    },
                    selected = false,
                    onClick = {
                        closeDrawer()
                        navController.navigate(MainScreen.AddSchedule.name)
                    }
                )
                NavigationDrawerItem(
                    label = {
                        Text(text = "Sign Out")
                    },
                    selected = false,
                    onClick = {
                        screenViewModel.unsetLogin()

                        preferencesManager.saveData("login", "")
                        preferencesManager.saveData("username", "")
                        preferencesManager.saveData("firstName", "")
                        preferencesManager.saveData("lastName", "")

                        closeDrawer()

                        navController.navigate(MainScreen.Splash.name)
                    }
                )
            }
        }
    ) {
        Scaffold(
            bottomBar = { }
        ) {
            Column(modifier = Modifier.padding(it)) {
            }
            NavHost(
                navController = navController,
                startDestination = MainScreen.HomePage.name,
                modifier = Modifier.padding(it)
            ) {
                composable(route = MainScreen.Login.name) {
                    LoginScreen(navController, screenViewModel)
                }
                composable(route = MainScreen.RegistrationScreen.name) {
                    RegistrationScreen(navController)
                }
                composable(route = MainScreen.AddSchedule.name) {
                    AddSchedule(navController)
                }
                composable(route = MainScreen.HomePage.name) {
                    HomePage(navController, drawerState)
                }
                composable(route = MainScreen.Profile.name) {
                    Profile(navController, screenViewModel)
                }
                composable(route = MainScreen.CheckLogin.name) {
                    CheckLogin(screenViewModel)
                }
                composable(route = MainScreen.Splash.name) {
                    SplashScreen(navController, screenViewModel)
                }
                composable("EditSchedule/{bid}") { navBackStackEntry ->
                    val bid = navBackStackEntry.arguments?.getString("bid")

                    bid?.let {
                        EditSchedule(navController = navController, index = bid.toString())
                    }
                }
            }
        }
    }
}