package com.vidovicsystems.timetracker.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vidovicsystems.timetracker.R
import com.vidovicsystems.timetracker.data.Chronos
import com.vidovicsystems.timetracker.ui.components.CircleButton
import com.vidovicsystems.timetracker.ui.components.MainIconButton
import com.vidovicsystems.timetracker.ui.components.MainTextField
import com.vidovicsystems.timetracker.ui.components.MainTitle
import com.vidovicsystems.timetracker.ui.components.timeFormat
import com.vidovicsystems.timetracker.ui.viewModels.ChronometerViewModel
import com.vidovicsystems.timetracker.ui.viewModels.ChronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(
    navController: NavController,
    chronometerViewModel: ChronometerViewModel,
    chronosViewModel: ChronosViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "Añadir cronómetro") },
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
        ContentAddView(it, navController, chronometerViewModel, chronosViewModel)
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    chronometerViewModel: ChronometerViewModel,
    chronosViewModel: ChronosViewModel
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
                icon = painterResource(id = R.drawable.play_24),
                enabled = !state.activeChrono
            ) {
                chronometerViewModel.startChrono()
            }

            //Pause
            CircleButton(
                icon = painterResource(id = R.drawable.pause_24),
                enabled = state.activeChrono
            ) {
                chronometerViewModel.pauseChrono()
            }

            //Stop
            CircleButton(
                icon = painterResource(id = R.drawable.stop_24),
                enabled = !state.activeChrono
            ) {
                chronometerViewModel.stopChrono()
            }

            //Show Save
            CircleButton(
                icon = painterResource(id = R.drawable.save_24),
                enabled = state.showSaveButton
            ) {
                chronometerViewModel.showTextField()
            }
        }

        if (state.showTextField) {
            MainTextField(
                value = state.title,
                onValueChange = { chronometerViewModel.onValue(it) },
                label = "Title"
            )

            Button(onClick = {
                chronosViewModel.insertChrono(
                    Chronos(
                        title = state.title,
                        chrono = chronometerViewModel.time
                    )
                )
                chronometerViewModel.stopChrono()
                navController.popBackStack()
            }) {
                Text(text = "Guardar")
            }
        }
    }
}