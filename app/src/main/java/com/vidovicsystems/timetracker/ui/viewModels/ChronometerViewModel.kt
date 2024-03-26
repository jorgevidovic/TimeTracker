package com.vidovicsystems.timetracker.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vidovicsystems.timetracker.data.repository.ChronosRepository
import com.vidovicsystems.timetracker.ui.state.ChronoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChronometerViewModel @Inject constructor(private val repository: ChronosRepository) :
    ViewModel() {
    var state by mutableStateOf(ChronoState())
        private set

    var chronoJob by mutableStateOf<Job?>(null)
        private set

    var time by mutableLongStateOf(0L)
        private set

    fun getChronoById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getChronoById(id).collect { item ->
                if (item != null) {
                    time = item.chrono
                    state = state.copy(
                        title = item.title
                    )} else {
                        Log.d("ERROR", "No se ha encontrado el cronómetro")
                    }
                }
            }
        }


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