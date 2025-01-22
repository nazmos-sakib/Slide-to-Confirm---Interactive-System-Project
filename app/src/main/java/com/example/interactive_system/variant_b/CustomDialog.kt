package com.example.interactive_system.variant_b

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.interactive_system.common.CardUiHelpers
import com.example.interactive_system.ui.theme.Blue100
import com.example.interactive_system.ui.theme.orange
import com.example.interactive_system.ui.theme.white

@Composable
fun CustomDialog(
    cardNumber: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp), // Set elevation here
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(2.dp, color = Blue100, shape = RoundedCornerShape(15.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Text(
                    text = "Are you sure you want to Add this card",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Card Number")
                        Text(
                            CardUiHelpers.formatCardNumber(
                                buildAnnotatedString {
                                    append(cardNumber)
                                    // then style the last added word as red, exclamation mark will be red
                                    addStyle(SpanStyle(color = Color.White), 0, this.length)
                                    toAnnotatedString()
                                }
                            ).text)
                    }
                    HorizontalDivider()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = orange,
                            contentColor = white
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
                            onConfirm()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Confirm",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun CustomDialogPreview() {
    CustomDialog(
        cardNumber = "",
        onConfirm = {},
        onDismiss = {}
    )
}