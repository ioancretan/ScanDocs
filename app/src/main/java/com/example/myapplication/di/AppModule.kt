package com.example.myapplication.di

import com.example.myapplication.scans.ScansRepository
import com.example.myapplication.scans.ScansRepositoryImpl
import com.example.myapplication.scans.ScansUseCase
import com.example.myapplication.scans.ScansViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideScansViewModel(getScansUseCase: ScansUseCase): ScansViewModel = ScansViewModel(getScansUseCase)

    @Provides
    fun provideScansUseCase(repository: ScansRepository): ScansUseCase = ScansUseCase(repository)

    @Provides
    fun provideScansRepository(): ScansRepository = ScansRepositoryImpl()
}