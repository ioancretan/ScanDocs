package com.example.myapplication.scans.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ScanItem(
    var id: Int,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)