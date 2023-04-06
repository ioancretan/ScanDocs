package com.example.myapplication

sealed class Screen(val route: String) {
    object ScansListScreen: Screen("scans_list_screen")
}