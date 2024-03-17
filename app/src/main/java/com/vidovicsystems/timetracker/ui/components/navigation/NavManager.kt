package com.vidovicsystems.timetracker.ui.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vidovicsystems.timetracker.ui.views.AddView
import com.vidovicsystems.timetracker.ui.views.EditView
import com.vidovicsystems.timetracker.ui.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController)
        }
        composable("AddView"){
            AddView(navController)
        }
        composable("EditView"){
            EditView(navController)
        }
    }
}