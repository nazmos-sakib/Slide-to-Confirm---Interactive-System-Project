package com.example.interactive_system.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.interactive_system.ui.theme.BankingAppTheme

@Composable
fun ScreenPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit = {},
) {

    BankingAppTheme(
        darkTheme = darkTheme,
        dynamicColor = false
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content.invoke()
        }
    }
}