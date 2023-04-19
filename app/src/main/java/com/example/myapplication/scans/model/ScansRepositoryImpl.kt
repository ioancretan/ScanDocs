package com.example.myapplication.scans.model

class ScansRepositoryImpl : ScansRepository {

    @Throws(Exception::class)
    override suspend fun getScans() : MutableList<ScanItem>{
        val items = (1..10).map {
            ScanItem(
                it
            )
        }

        if (items.size == 0) {
            throw Exception("not valid")
        }

        return items.toMutableList()
    }
}