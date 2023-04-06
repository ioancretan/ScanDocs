package com.example.myapplication.scans

interface  ScansRepository {
    suspend fun getScans(): MutableList<ScanItem>
}