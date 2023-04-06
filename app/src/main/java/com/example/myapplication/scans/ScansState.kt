package com.example.myapplication.scans

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ScansState(
    val scans: MutableState<MutableList<ScanItem>> = mutableStateOf(mutableListOf()),
    val error: String = "",
    var isMAnageButtonVisible: MutableState<Boolean> = mutableStateOf(false)
)