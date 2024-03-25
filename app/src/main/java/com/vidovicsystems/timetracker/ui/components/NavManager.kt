package com.vidovicsystems.timetracker.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vidovicsystems.timetracker.ui.viewModels.ChronometerViewModel
import com.vidovicsystems.timetracker.ui.views.AddView
import com.vidovicsystems.timetracker.ui.views.EditView
import com.vidovicsystems.timetracker.ui.views.HomeView

@Composable
fun NavManager(chronometerViewModel: ChronometerViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController)
        }
        composable("AddView"){
            AddView(navController, chronometerViewModel)
        }
        composable("EditView"){
            EditView(navController)
        }
    }
}