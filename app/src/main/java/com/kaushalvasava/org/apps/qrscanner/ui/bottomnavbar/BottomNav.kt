package com.kaushalvasava.org.apps.qrscanner.ui.bottomnavbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainEntryPoint() {

    val navController = rememberNavController()
    Scaffold(bottomBar = { MainBottomNavigation(navController = navController) }) {
        MainNavigation(navHostController = navController)
    }
}

@Composable
fun MainNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomNavItem.Home.route,
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Search.route) {
            SearchScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
        composable(BottomNavItem.Mail.route) {
            MailScreen()
        }
        composable(BottomNavItem.Warning.route) {
            WarningScreen()
        }
    }
}

@Composable
fun WarningScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Warning Screen", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun MailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Mail Screen", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Profile Screen", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Search Screen", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Home Screen", modifier = Modifier.align(Alignment.Center))
    }
}

// Main Bottom Navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Warning,
        BottomNavItem.Mail,
        BottomNavItem.Search
    )
    BottomAppBar(containerColor = Color.White, contentColor = Color.White) {
        val navStack by navController.currentBackStackEntryAsState()
        val currentRoute = navStack?.destination?.route

        items.forEach { item ->
//            BottomNavigationItem(
//                label = { Text(item.label) },
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let {
//                            popUpTo(item.route)
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                }, icon = {
//                    BadgedBox(badge = {
//                        if (currentRoute == item.route) {
//                            Surface(
//                                modifier = Modifier.padding(4.dp),
//                                shape = CircleShape,
//                                color = Color.Red
//                            ) {
//                                Text(
//                                    text = "12",
//                                    style = TextStyle(fontSize = 8.sp),
//                                    modifier = Modifier.padding(4.dp),
//                                    color = Color.White
//                                )
//                            }
//                        }
//                    }) {
//                        Icon(
//                            imageVector = item.icon,
//                            contentDescription = null,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                }, alwaysShowLabel = true,
//                selectedContentColor = Color.Red,
//                unselectedContentColor = Color.Black
//            )
        }
    }
}






















