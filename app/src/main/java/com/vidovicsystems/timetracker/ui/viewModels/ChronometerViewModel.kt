package com.vidovicsystems.timetracker.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vidovicsystems.timetracker.ui.state.ChronoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChronometerViewModel : ViewModel() {
    var state by mutableStateOf(ChronoState())
        private set

    var chronoJob by mutableStateOf<Job?>(null)
        private set

    var time by mutableLongStateOf(0L)
        private set

    fun onValue(value: String) {
        state = state.copy(
            title = value
        )
    }

    fun startChrono() {
        state = state.copy(
            activeChrono = true
        )
    }

    fun pauseChrono() {
        state = state.copy(
            activeChrono = false,
            showSaveButton = true
        )
    }

    fun stopChrono() {
        chronoJob?.cancel()
        time = 0
        state = state.copy(
            activeChrono = false,
            showSaveButton = false,
            showTextField = false,
            title = ""
        )
    }

    fun showTextField() {
        state = state.copy(
            showTextField = true
        )
    }

    fun chronos() {
        if (state.activeChrono) {
            chronoJob?.cancel()
            chronoJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    time += 1000
                }
            }
        } else {
            chronoJob?.cancel()
        }
    }

}