package com.jhapps.pacecalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TabNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val startDestination = Destination.PACE
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal)}

    Scaffold(modifier = Modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets){
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                               destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = {Text(destination.label)}
                    )
                }
            }
        }
    ){ contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
){
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.PACE -> PaceScreen()
                    Destination.SPLITS -> SplitsScreen()
                }
            }
        }
    }
}