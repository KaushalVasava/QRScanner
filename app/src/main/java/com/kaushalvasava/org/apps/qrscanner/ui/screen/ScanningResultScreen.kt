package com.kaushalvasava.org.apps.qrscanner.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ScanningResultScreen(navController: NavController, result: String?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = result?:"")
        Button(onClick = {
            // copy
            Log.d("TAG", "ScanningResultScreen: ")
        }) {
            Text(text = "View")
        }
    }
}