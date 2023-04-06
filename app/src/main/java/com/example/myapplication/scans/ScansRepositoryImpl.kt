package com.example.myapplication.scans

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class ScansRepositoryImpl : ScansRepository {

    override suspend fun getScans(): MutableList<ScanItem> {
        val items = (1..10).map {
            ScanItem(
                it
            )
        }
        return items.toMutableList()
    }
}

data class ScanItem(
    var id: Int,
    var isSelected: MutableState<Boolean> = mutableStateOf(false)
)