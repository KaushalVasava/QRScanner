package com.kaushalvasava.org.apps.qrscanner.ui.navhost

enum class Screen {
    HOME,
    SCAN,
    GENERATE,
    RESULT,
    IMAGE_PICKER
}

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Scan : NavigationItem(Screen.SCAN.name)
    object Generate : NavigationItem(Screen.GENERATE.name)
    object Result : NavigationItem(Screen.RESULT.name)
    object ImagePicker : NavigationItem(Screen.IMAGE_PICKER.name)
}