package com.example.myapplication.scans.model

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScansUseCase @Inject constructor(
    private val repository: ScansRepository
) {
    operator fun invoke(): Flow<Resource<List<ScanItem>>> = flow {
        try {
            val scans = repository.getScans()
            emit(Resource.Success(scans))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured", emptyList()))
        }
    }
}