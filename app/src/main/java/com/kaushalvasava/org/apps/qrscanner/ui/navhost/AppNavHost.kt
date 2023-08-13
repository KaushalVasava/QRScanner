package com.kaushalvasava.org.apps.qrscanner.ui.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kaushalvasava.org.apps.qrscanner.ui.screen.imagepicker.ImagePicker
import com.kaushalvasava.org.apps.qrscanner.ui.screen.result.ResultScreen
import com.kaushalvasava.org.apps.qrscanner.ui.screen.generatecode.GenerateCodeScreen
import com.kaushalvasava.org.apps.qrscanner.ui.screen.home.HomeScreen
import com.kaushalvasava.org.apps.qrscanner.ui.screen.scan.ScanScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            "${NavigationItem.Result.route}/{result}",
            arguments = listOf(navArgument("result") { type = NavType.StringType })
        ) {
            ResultScreen(
                navController, it.arguments?.getString("result").toString()
            )
        }
        composable(NavigationItem.Generate.route) {
            GenerateCodeScreen(navController)
        }
        composable(NavigationItem.ImagePicker.route) {
            ImagePicker(navController)
        }
        composable(NavigationItem.Scan.route) {
            ScanScreen(navController)
        }
    }
}