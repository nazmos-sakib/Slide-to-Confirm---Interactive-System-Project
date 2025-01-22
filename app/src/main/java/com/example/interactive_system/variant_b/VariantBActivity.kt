package com.example.interactive_system.variant_b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.interactive_system.common.DataForm
import com.example.interactive_system.variant_b.ui.theme.InteractivesystemTheme

class VariantBActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InteractivesystemTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->


                    val cardNumber = remember {
                        mutableStateOf("")
                    }
                    DataForm(
                        modifier = Modifier.padding(innerPadding),
                        cardNumber = cardNumber
                    ) {
                        ConfirmButtonWithDialogBox(cardNumber = cardNumber)
                    }
                }
            }
        }
    }
}


@Composable
fun ConfirmButtonWithDialogBox(
    modifier: Modifier = Modifier,
    cardNumber: MutableState<String>,
    onButtonClick: () -> Unit = {}
) {
    val isDialogShown = remember {
        mutableStateOf(false)
    }

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onButtonClick()
            isDialogShown.value = true
        }) {
        Text(text = "Save")
    }
    if (isDialogShown.value) {
        CustomDialog(
            cardNumber = cardNumber.value,
            onConfirm = {},
            onDismiss = { isDialogShown.value = false }
        )

    }
}

@Preview
@Composable
fun ConfirmButtonWithDialogBoxPreview() {
    //ConfirmButtonWithDialogBox()
}