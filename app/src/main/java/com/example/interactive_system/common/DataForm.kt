package com.example.interactive_system.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interactive_system.R
import com.example.interactive_system.extention.getFormattedDate
import com.example.interactive_system.model.AddCardFieldType
import com.example.interactive_system.model.AddCardFormFields
import com.example.interactive_system.model.AddCardIntent
import com.example.interactive_system.model.AddCardState
import com.example.interactive_system.model.UiField
import com.example.interactive_system.ui.theme.primaryFontFamily
import kotlinx.coroutines.flow.MutableStateFlow


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DataForm(
    modifier: Modifier =  Modifier,
    cardNumber:MutableState<String>,
    content: @Composable () -> Unit
) {
    val state =  remember {
        mutableStateOf(AddCardState())
    }

    Surface(
        modifier  = modifier,
        color  = Color.White,
    ) {
        AddCardScreen_Ui(
            state = state,
            _cardNumber = cardNumber,
            onIntent = {   }
        ){content()}
    }
}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun DataFormPreview() {
    DataForm(cardNumber = mutableStateOf("")){}
}

@Composable
fun AddCardScreen_Ui(
    modifier: Modifier =  Modifier,
    state:  MutableState<AddCardState>,
    _cardNumber:MutableState<String>,
    onIntent: (intent: AddCardIntent) -> Unit = {},
    content: @Composable () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val cardFormField = remember { mutableStateOf(AddCardFormFields()) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = 16.dp,
                    bottom = 40.dp,
                    start = 24.dp,
                    end = 24.dp
                )
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }, verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(Modifier.weight(1f)) {
                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    CardNumberField(
                        title = "Card Number",
                        cardNumber = cardFormField.value.cardNumber.value,
                        onPostValue = { cardNumber->
                            cardFormField.value = cardFormField.value.copy(
                                cardNumber =  cardFormField.value.cardNumber.copy(
                                value = cardNumber
                            ))

                            _cardNumber.value = cardNumber
                        },
                        type = KeyboardType.Number,
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                        // FIXME field
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = "Expired Date", style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = primaryFontFamily,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF808289),
                                ), modifier = Modifier.padding(top = 8.dp)
                            )

                            Spacer(Modifier.height(16.dp))

                            ReadonlyTextField(
                                value = cardFormField.value.expirationDate.value,
                                onValueChange = {},
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                        state.value = state.value.copy(
                                             showDatePicker = true
                                         )
                                },
                                error = state.value.formFields.expirationDate.error?.asString()
                            )
                        }


                        FormField(
                            title = "CVC/CVV",
                            onValueChange = { cvv->
                                cardFormField.value = cardFormField.value.copy(
                                    cvvCode =  cardFormField.value.cvvCode.copy(
                                        value = cvv
                                    ))
                                },
                            uiField = cardFormField.value.cvvCode,
                            modifier = Modifier.weight(1f),
                            type = KeyboardType.Number
                        )
                    }

                    FormField(
                        title = "Cardholder Name",
                        onValueChange = { cardHolderName->
                            cardFormField.value = cardFormField.value.copy(
                                cardHolder =  cardFormField.value.cardHolder.copy(
                                    value = cardHolderName
                                ))
                        },
                        uiField = cardFormField.value.cardHolder,
                        capitalize = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Billing Address", style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = primaryFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF333333),
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    FormField(
                        title = "Address Line 1",
                        onValueChange = { add1->
                            cardFormField.value = cardFormField.value.copy(
                                addressFirstLine =  cardFormField.value.addressFirstLine.copy(
                                    value = add1
                                ))
                        },
                        uiField = cardFormField.value.addressFirstLine,
                    )

                    FormField(
                        title = "Address Line 2",
                        onValueChange = { add2->
                            cardFormField.value = cardFormField.value.copy(
                                addressSecondLine =  cardFormField.value.addressSecondLine.copy(
                                    value = add2
                                ))
                        },
                        uiField = cardFormField.value.addressSecondLine,
                    )
/*
                    FormField(
                        title = "Amount",
                        onValueChange = { amount->
                            cardFormField.value = cardFormField.value.copy(
                                amount =  cardFormField.value.amount.copy(
                                    value = amount
                                ))
                        },
                        uiField = cardFormField.value.amount,
                    )*/

                }
            }

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
            ) {
                content()
            }


        }






    if (state.value.showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { selectedMills ->
                state.value = state.value.copy(
                    showDatePicker = false
                )

                if (selectedMills != null) {
                    cardFormField.value = cardFormField.value.copy(
                        expirationDate = UiField(selectedMills.getFormattedDate("dd MMM yyyy")),
                        expirationDateTimestamp = selectedMills
                    )
                }
            },
            initialSelectedDate = state.value.formFields.expirationDateTimestamp
        )
    }
}

 @Composable
private fun FormField(
    title: String,
    uiField: UiField,
    onValueChange: (value: String) -> Unit,
    modifier: Modifier = Modifier,
    type: KeyboardType = KeyboardType.Text,
    capitalize: Boolean = false
) {

    Column(modifier = modifier) {
        Text(
            text = title, style = TextStyle(
                fontSize = 12.sp,
                fontFamily = primaryFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF808289),
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        PrimaryTextField(
            value = if (capitalize) {
                uiField.value.toUpperCase(Locale.current)
            } else {
                uiField.value
            },
            onValueChange = {
                onValueChange.invoke(it)
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = type,
            ),
            error = uiField.error?.asString()
        )
    }
}

/*

@Composable
@Preview(device = Devices.NEXUS_5)
fun AddCardScreen_Preview() {
    ScreenPreview {
        AddCardScreen_Ui(
            state = AddCardState.mock()
        ){}
    }
}


@Composable
@Preview(device = Devices.NEXUS_5)
fun AddCardScreen_DatePicker_Preview() {
    ScreenPreview {
        AddCardScreen_Ui(
            state = AddCardState.mock(showDatePicker = true)
        ){}
    }
}

 */