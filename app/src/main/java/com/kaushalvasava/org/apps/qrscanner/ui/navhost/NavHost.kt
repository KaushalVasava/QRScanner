package com.kaushalvasava.org.apps.qrscanner.ui.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kaushalvasava.org.apps.qrscanner.ui.screen.ScanningResultScreen
import com.kaushalvasava.org.apps.qrscanner.ui.screen.scan.ScanScreen

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "scan"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("resultscreen/{result}",
            arguments = listOf(navArgument("result") { type = NavType.StringType })
            ) {
            ScanningResultScreen (
                navController, it.arguments?.getString("result").toString()
            )
        }
        composable("home"){
//            TabLayoutScreen()
        }
        composable("scan") {
            ScanScreen(navController)
        }
    }
}