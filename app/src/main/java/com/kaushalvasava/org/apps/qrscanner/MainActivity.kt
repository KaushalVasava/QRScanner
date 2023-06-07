package com.kaushalvasava.org.apps.qrscanner

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.google.zxing.client.android.BeepManager
import com.kaushalvasava.org.apps.qrscanner.ui.navhost.MyAppNavHost
import com.kaushalvasava.org.apps.qrscanner.ui.screen.scan.ScanScreen
import com.kaushalvasava.org.apps.qrscanner.ui.theme.QRScannerTheme

class MainActivity : ComponentActivity() {

//    companion object {
//        @SuppressLint("StaticFieldLeak")
//        lateinit var beepManager: BeepManager
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        beepManager = BeepManager(this)
        setContent {
            RequestPermission()
//            val context = LocalContext.current
//            var capturedImageUri by remember {
//                mutableStateOf<Uri>(Uri.EMPTY)
//            }
//            val cameraLauncher =
//                rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
//
//                }
//            val permissionLauncher = rememberLauncherForActivityResult(
//                ActivityResultContracts.RequestPermission()
//            ) {
//
//                if (it) {
//                    //Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
//                    cameraLauncher.launch(android.Manifest.permission.CAMERA)
//                } else {
//                    //Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
//                }
//
//            }
            QRScannerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    ScanScreen()
                    MyAppNavHost(navController = rememberNavController())
                    // TabLayoutScreen()
                }
            }
        }
    }
}

@Composable
fun RequestPermission() {
    val launcher: ManagedActivityResultLauncher<String, Boolean> =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission Accepted: Do something
                Log.d("TAG", "PERMISSION GRANTED")
//                startService(intent)
//                bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
        }

    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            LocalContext.current,
            android.Manifest.permission.CAMERA
        ) -> {
            // Some works that require permission
            Log.d("TAG", "Code requires permission")
        }

        else -> {
            // Asking for permission
            SideEffect {
                launcher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QRScannerTheme {
        Greeting("Android")
    }
}