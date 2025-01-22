package com.example.interactive_system.variant_a

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.interactive_system.common.DataForm
import com.example.interactive_system.slide_to_comfirm.SlideToConfirm
import com.example.interactive_system.variant_a.ui.theme.InteractivesystemTheme

class VariantAActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InteractivesystemTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val cardNumber =  remember {
                        mutableStateOf("")
                    }

                    DataForm(
                        modifier = Modifier.padding(innerPadding),
                        cardNumber = cardNumber
                    ){
                        SlideToConfirm()
                    }
                }
            }
        }
    }
}