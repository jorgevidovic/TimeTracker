package com.vidovicsystems.timetracker.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vidovicsystems.timetracker.ui.components.ChronoCard
import com.vidovicsystems.timetracker.ui.components.MainTitle
import com.vidovicsystems.timetracker.ui.components.FloatButton
import com.vidovicsystems.timetracker.ui.components.timeFormat
import com.vidovicsystems.timetracker.ui.viewModels.ChronosViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, chronosViewModel: ChronosViewModel) {
    Scaffold(
        topBar =
        {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "TimeTracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },

        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }

    ) {
        ContentHomeView(it, navController, chronosViewModel)
    }
}

@Composable
fun ContentHomeView(
    it: PaddingValues,
    navController: NavController,
    chronosViewModel: ChronosViewModel
) {
    Column(
        modifier = Modifier.padding(it)
    ) {
        val chronosList by chronosViewModel.chronosList.collectAsState()
        LazyColumn {
            items(chronosList) { item ->
                val delete = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = { chronosViewModel.deleteChrono(item) }
                )

                SwipeableActionsBox(endActions = listOf(delete), swipeThreshold = 100.dp) {
                    ChronoCard(item.title, timeFormat(item.chrono)) {
                        navController.navigate("EditView/${item.id}")
                    }
                }
            }
        }
    }

}
