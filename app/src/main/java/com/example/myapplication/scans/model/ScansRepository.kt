package com.example.myapplication.scans.model


interface  ScansRepository {
    suspend fun getScans(): MutableList<ScanItem>
}