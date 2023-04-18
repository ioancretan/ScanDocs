package com.example.myapplication.scans.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.scans.model.ScanItem

data class ScansState(
    val scans: MutableState<MutableList<ScanItem>> = mutableStateOf(mutableListOf()),
    val error: String = "",
    var isMAnageButtonVisible: MutableState<Boolean> = mutableStateOf(false)
)