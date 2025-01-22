package com.example.interactive_system.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interactive_system.R
import com.example.interactive_system.ui.theme.BankingAppTheme
import com.example.interactive_system.ui.theme.primaryFontFamily

// TODO refactoring of fields
@Composable
fun CardNumberField(
    title: String,
    cardNumber: String,
    onPostValue: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    type: KeyboardType = KeyboardType.Text,
) {

    Column(modifier = modifier) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = primaryFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF808289),
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        PrimaryTextField(
            value = cardNumber.toString(),
            onValueChange = {
                onPostValue.invoke(it)
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = type,
            ),
            visualTransformation = { number ->
                CardUiHelpers.formatCardNumber(number)
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.card_icon),
                    contentDescription = "Visibility Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(end = 16.dp)
                )
            },
        )
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun CardTextField_Preview() {
    BankingAppTheme() {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            CardNumberField(
                title = "Card Number",
                onPostValue = {},
                cardNumber = "10000998998",
            )
        }
    }
}