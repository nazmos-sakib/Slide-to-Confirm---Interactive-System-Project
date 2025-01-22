package com.example.interactive_system.model

import com.example.interactive_system.extention.getFormattedDate

data class AddCardState(
    val formFields: AddCardFormFields = AddCardFormFields(),
    val isLoading: Boolean = false,
    val showDatePicker: Boolean = false,
) {
    companion object {
        fun mock(
            isLoading: Boolean = false,
            showDatePicker: Boolean = false
        ): AddCardState {
            // + 365 days
            val randomMockCard = MockCardConstants.randomCard()
            val mockExpiration = System.currentTimeMillis() + 31556926000L

            return  AddCardState(
                formFields = AddCardFormFields(
                    cardNumber = UiField(randomMockCard.first),
                    cardHolder = UiField("Alexander Michael"),
                    addressFirstLine = UiField("2890 Pangandaran Street"),
                    addressSecondLine = UiField(""),
                    cvvCode = UiField("123"),
                    expirationDateTimestamp =  mockExpiration,
                    expirationDate = UiField(mockExpiration.getFormattedDate("dd MMM yyyy"))
                ),
                isLoading = isLoading,
                showDatePicker = showDatePicker
            )
        }
    }
}
