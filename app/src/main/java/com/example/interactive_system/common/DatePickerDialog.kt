package com.example.interactive_system.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDismissRequest: (selectedMills: Long?) -> Unit = {},
    initialSelectedDate: Long? = null
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDate,
    )
    val dialogState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest.invoke(datePickerState.selectedDateMillis)
        },
        sheetState = dialogState,
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }
}