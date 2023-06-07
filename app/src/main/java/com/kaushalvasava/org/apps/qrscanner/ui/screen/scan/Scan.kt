package com.kaushalvasava.org.apps.qrscanner.ui.screen.scan

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.camera.CameraSettings
import com.kaushalvasava.org.apps.qrscanner.R
import com.kaushalvasava.org.apps.qrscanner.databinding.BarcodeLayoutBinding
import com.kaushalvasava.org.apps.qrscanner.findActivity
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun ScanScreen(navController: NavController?) {
//    val navController: NavHostController = rememberNavController()
//    MyAppNavHost(navController = navController)
    Column {
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
                        Log.d("TAG", "barcodeResult: $encodedUrl")
                        try {
                            navController?.navigate("resultscreen/$encodedUrl")
                            binding.barcodeView.pause()
                        } catch (e: Exception) {
                            Log.e("TAG", "barcodeResult: ${e.message}")
                        }
//                    val action =
//                        HomeFragmentDirections.actionHomeFragmentToResultFragment(resultText)
//                    findNavController().navigate(action)
                    }

                    override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
                        super.possibleResultPoints(resultPoints)
                        Log.d("TAG", "possibleResultPoints: $")
                    }
                })
            }
        )
        Row {
            Icon(painter = painterResource(id = R.drawable.ic_phone), contentDescription = null)
            Icon(painter = painterResource(id = R.drawable.ic_location), contentDescription = null)
        }
    }
}