package com.vidovicsystems.timetracker.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vidovicsystems.timetracker.ui.components.CircleButton
import com.vidovicsystems.timetracker.ui.components.MainIconButton
import com.vidovicsystems.timetracker.ui.components.MainTitle
import com.vidovicsystems.timetracker.ui.components.timeFormat
import com.vidovicsystems.timetracker.ui.viewModels.ChronometerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, chronometerViewModel: ChronometerViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "TimeTracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        },
    ) {
        ContentAddView(it, navController, chronometerViewModel)
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    chronometerViewModel: ChronometerViewModel
) {

    val state = chronometerViewModel.state

    LaunchedEffect(state.activeChrono) {
        chronometerViewModel.chronos()
    }

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = timeFormat(time = chronometerViewModel.time),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            //Start
            CircleButton(
                icon = Icons.Default.PlayArrow,
                enabled = !state.activeChrono
            ) {
                chronometerViewModel.startChrono()
            }

            //Pause
            CircleButton(
                icon = Icons.Default.Person,
                enabled = state.activeChrono
            ) {
                chronometerViewModel.pauseChrono()
            }

            //Stop
            CircleButton(
                icon = Icons.Default.Person,
                enabled = !state.activeChrono
            ) {
                chronometerViewModel.stopChrono()
            }

            //Show Save
            CircleButton(
                icon = Icons.Default.PlayArrow,
                enabled = state.showSaveButton
            ) {
                chronometerViewModel.showTextField()
            }
        }
    }
}