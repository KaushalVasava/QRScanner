package com.kaushalvasava.org.apps.qrscanner.ui.screen.scan

import android.view.View
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.camera.CameraSettings
import com.kaushalvasava.org.apps.qrscanner.R
import com.kaushalvasava.org.apps.qrscanner.databinding.BarcodeLayoutBinding
import com.kaushalvasava.org.apps.qrscanner.findActivity
import com.kaushalvasava.org.apps.qrscanner.ui.navhost.NavigationItem
import com.kaushalvasava.org.apps.qrscanner.ui.theme.QRScannerTheme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.result)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                }
            }
        )
        val context = LocalContext.current
        AndroidView(factory = {
            View.inflate(it, R.layout.barcode_layout, null)
        },
            update = {
                val beepManager = BeepManager(context.findActivity())
                beepManager.isBeepEnabled = true
                beepManager.isVibrateEnabled = true
                val binding = BarcodeLayoutBinding.bind(it)
                binding.barcodeView.resume()
                val s = CameraSettings()
                s.requestedCameraId = 0 // front/back/etc
                binding.barcodeView.cameraSettings = s
                binding.barcodeView.decodeSingle(object : BarcodeCallback {
                    override fun barcodeResult(result: BarcodeResult?) {
                        beepManager.playBeepSound()
                        val resultText = result?.result?.text ?: ""
                        val encodedUrl =
                            URLEncoder.encode(resultText, StandardCharsets.UTF_8.toString())
                        try {
                            navController.navigate("${NavigationItem.Result.route}/$encodedUrl")
                            binding.barcodeView.pause()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Invalid code", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
                        super.possibleResultPoints(resultPoints)
                    }
                })
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("OR")
        Spacer(modifier = Modifier.height(8.dp))
        IconButton(onClick = {
            navController.navigate(NavigationItem.ImagePicker.route)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = null,
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    QRScannerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ScanScreen(navController = rememberNavController())
        }
    }
}
