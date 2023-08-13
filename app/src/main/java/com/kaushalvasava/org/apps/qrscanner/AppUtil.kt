package com.kaushalvasava.org.apps.qrscanner

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun copyTextToClipboard(context: Context, message: String, label: String = "copy") {
    val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText(
        label,
        message
    )
    Toast.makeText(context, "$message is copied", Toast.LENGTH_SHORT).show()
    clipboardManager.setPrimaryClip(clipData)
}

fun shareImageUri(context: Context, uri: Uri) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        type = "image/png"
    }
    context.startActivity(intent)
}

fun saveImage(context: Context, image: Bitmap): Uri? {
    val imagesFolder = File(context.cacheDir, "images")
    var uri: Uri? = null
    try {
        imagesFolder.mkdirs()
        val file = File(imagesFolder, "shared_qr_code.png")
        val stream = FileOutputStream(file)
        image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.flush()
        stream.close()
        uri = FileProvider.getUriForFile(
            context,
            "com.kaushalvasava.org.apps.qrscanner.fileprovider",
            file
        )
    } catch (e: IOException) {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
    }
    return uri
}
