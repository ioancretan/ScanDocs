@file:OptIn(ExperimentalFoundationApi::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.scans.ScansViewModel
import com.example.myapplication.ui.ScansList
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var scansViewModel: ScansViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as ScansApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colors.background) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.ScansListScreen.route
                ) {
                    composable(
                        route = Screen.ScansListScreen.route
                    ) {
                        ScansList(scansViewModel)
                    }
                }
            }
        }
    }
}


