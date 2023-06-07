package com.kaushalvasava.org.apps.qrscanner.ui.screen.generatecode

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.kaushalvasava.org.apps.qrscanner.R
import com.kaushalvasava.org.apps.qrscanner.model.InfoType
import com.kaushalvasava.org.apps.qrscanner.ui.screen.generatecode.compoents.RowItem

@Composable
fun GenerateCode() {
    LazyColumn {
        items(list) {
            RowItem( it.title,null)
        }
    }
}

val list = listOf<InfoType>(
    InfoType("Phone number", null),
    InfoType("Website", null),
)