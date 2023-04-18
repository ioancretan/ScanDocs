package com.example.myapplication.di

import com.example.myapplication.scans.model.ScansRepository
import com.example.myapplication.scans.model.ScansRepositoryImpl
import com.example.myapplication.scans.model.ScansUseCase
import com.example.myapplication.scans.viewModel.ScansViewModel
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