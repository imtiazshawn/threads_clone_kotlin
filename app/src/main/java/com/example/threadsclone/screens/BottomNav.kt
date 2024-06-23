package com.example.threadsclone.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.threadsclone.model.BottomNavItem
import com.example.threadsclone.navigation.Routes

@Composable
fun BottomNav(navController: NavHostController) {
    val navControllerRem = rememberNavController()

    Scaffold (
        bottomBar = { MyBottomBar(navControllerRem) }
    ) {
        innerPadding -> NavHost(
            navController = navControllerRem,
            startDestination = Routes.Home.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Home.routes) {
                Home()
            }

            composable(Routes.Notification.routes) {
                Notification()
            }

            composable(Routes.Profile.routes) {
                Profile()
            }

            composable(Routes.Search.routes) {
                Search()
            }

            composable(Routes.AddThread.routes) {
                AddThreads()
            }
        }
    }
}

@Composable
fun MyBottomBar(navControllerRem: NavHostController) {
    val backStackEntry = navControllerRem.currentBackStackEntryAsState()

    val list = listOf(
        BottomNavItem("Home", Routes.Home.routes, Icons.Rounded.Home),
        BottomNavItem("Search", Routes.Search.routes, Icons.Rounded.Search),
        BottomNavItem("Add Threads", Routes.AddThread.routes, Icons.Rounded.Add),
        BottomNavItem("Notification", Routes.Notification.routes, Icons.Rounded.Notifications),
        BottomNavItem("Profile", Routes.Profile.routes, Icons.Rounded.Person)
    )

    BottomAppBar {
        list.forEach {
            val selected = it.route == backStackEntry?.value?.destination?.route
            
            NavigationBarItem(
                selected = selected,
                onClick = { navControllerRem.navigate(it.route) {
                    popUpTo(navControllerRem.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                } },
                icon = { Icon(imageVector = it.icon, contentDescription = null) }
            )
        }
    }
}