package com.vidovicsystems.timetracker.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vidovicsystems.timetracker.ui.viewModels.ChronometerViewModel
import com.vidovicsystems.timetracker.ui.viewModels.ChronosViewModel
import com.vidovicsystems.timetracker.ui.views.AddView
import com.vidovicsystems.timetracker.ui.views.EditView
import com.vidovicsystems.timetracker.ui.views.HomeView

@Composable
fun NavManager(chronometerViewModel: ChronometerViewModel, chronosViewModel: ChronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, chronosViewModel)
        }
        composable("AddView"){
            AddView(navController, chronometerViewModel, chronosViewModel)
        }
        composable("EditView/{id}", arguments = listOf(navArgument("id") { type = NavType.LongType})) {
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, chronometerViewModel, chronosViewModel, id)
        }
    }
}