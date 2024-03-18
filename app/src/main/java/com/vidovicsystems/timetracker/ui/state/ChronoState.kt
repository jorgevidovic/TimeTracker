package com.vidovicsystems.timetracker.ui.state

data class ChronoState(
    val activeChrono : Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField : Boolean = false,
    val title: String = "",
)
