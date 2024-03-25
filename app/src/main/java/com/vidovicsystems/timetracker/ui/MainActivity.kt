package com.vidovicsystems.timetracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vidovicsystems.timetracker.ui.components.NavManager
import com.vidovicsystems.timetracker.ui.theme.TimeTrackerTheme
import com.vidovicsystems.timetracker.ui.viewModels.ChronometerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chronometerViewModel : ChronometerViewModel by viewModels()
        setContent {
            TimeTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(chronometerViewModel)
                }
            }
        }
    }
}