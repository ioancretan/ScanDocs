package com.example.myapplication.scans.view

sealed class Screen(val route: String) {
    object ScansListScreen: Screen("scans_list_screen")
}