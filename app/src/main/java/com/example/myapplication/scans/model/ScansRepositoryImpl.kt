package com.example.myapplication.scans.model

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