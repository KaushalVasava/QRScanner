package com.kaushalvasava.org.apps.qrscanner.ui.screen.generatecode

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.kaushalvasava.org.apps.qrscanner.R
import com.kaushalvasava.org.apps.qrscanner.model.InfoType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateCodeScreen(navController: NavController) {
    var txt by rememberSaveable {
        mutableStateOf("")
    }
    var bmp: Bitmap? by rememberSaveable {
        mutableStateOf(null)
    }
    Column(Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally) {
        TopAppBar(title = { Text(stringResource(R.string.result)) }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
            }
        })
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = txt,
            onValueChange = {
                txt = it
            },
            placeholder = {
                Text(
                    stringResource(R.string.enter_phone_number_email_website_link_etc),
                    color = Color.Gray
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            bmp = generateQrCode(txt)
        }) {
            Text(stringResource(R.string.generate))
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (bmp != null)
            AsyncImage(
                model = bmp,
                contentDescription = "image",
                modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally)
            )
    }
}

private fun generateQrCode(content: String): Bitmap? {
    val writer = QRCodeWriter()
    return try {
        val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.Black.hashCode() else Color.White.hashCode()
                )
            }
        }
        bmp
    } catch (e: WriterException) {
        e.printStackTrace()
        null
    } catch (e: Exception){
        e.printStackTrace()
        null
    }
}