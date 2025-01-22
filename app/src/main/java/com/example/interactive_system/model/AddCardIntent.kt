package com.example.interactive_system.model

sealed class AddCardIntent {
    object EnterScreen : AddCardIntent()
    data class StringFieldChanged(
        val fieldType: AddCardFieldType,
        val fieldValue: String
    ) : AddCardIntent()
    data class ExpirationPickerSet(val date: Long?) : AddCardIntent()
    object SaveCard : AddCardIntent()
    object ConsumeResultEvent : AddCardIntent()
    data class ToggleDatePicker(val isShown: Boolean) : AddCardIntent()
}
