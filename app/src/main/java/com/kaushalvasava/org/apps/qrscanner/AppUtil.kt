package com.kaushalvasava.org.apps.qrscanner

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

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