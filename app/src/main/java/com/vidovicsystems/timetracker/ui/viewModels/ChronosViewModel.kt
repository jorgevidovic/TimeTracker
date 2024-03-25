package com.vidovicsystems.timetracker.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vidovicsystems.timetracker.data.Chronos
import com.vidovicsystems.timetracker.data.repository.ChronosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChronosViewModel @Inject constructor(private val chronosRepository: ChronosRepository) :
    ViewModel() {
    private val _chronosList = MutableStateFlow<List<Chronos>>(emptyList())
    val chronosList = _chronosList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            chronosRepository.getAllChronos().collect { item ->
                if (item.isNullOrEmpty()) {
                    _chronosList.value = emptyList()
                } else {
                    _chronosList.value = item
                }
            }
        }
    }

    fun insertChrono(chrono: Chronos) =
        viewModelScope.launch { chronosRepository.insertChronos(chrono) }

    fun updateChrono(chrono: Chronos) =
        viewModelScope.launch { chronosRepository.updateChronos(chrono) }

    fun deleteChrono(chrono: Chronos) =
        viewModelScope.launch { chronosRepository.deleteChronos(chrono) }
}