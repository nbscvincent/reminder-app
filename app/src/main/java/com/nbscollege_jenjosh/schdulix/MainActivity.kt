package com.nbscollege_jenjosh.schdulix

import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nbscollege_jenjosh.schdulix.screens.SchdulixApp
import com.nbscollege_jenjosh.schdulix.ui.theme.SchdulixTheme
import com.nbscollege_jenjosh.schdulix.viewmodel.ScreenViewModel
import kotlinx.coroutines.delay
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        val screenViewModel: ScreenViewModel by viewModels();

        setContent {
            SchdulixTheme {
                SchdulixApp(screenViewModel)
            }
        }
    }
}
