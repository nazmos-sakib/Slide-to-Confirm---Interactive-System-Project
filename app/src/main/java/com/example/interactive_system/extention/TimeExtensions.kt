package com.example.interactive_system.extention

import java.text.SimpleDateFormat
import java.util.Locale

fun Long.getFormattedDate(format: String): String {
    val formatter = SimpleDateFormat(format, Locale.US)
    return formatter.format(this)
}