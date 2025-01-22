package com.example.interactive_system.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.interactive_system.R
import com.example.interactive_system.ui.theme.Gray15
import com.example.interactive_system.ui.theme.Gray5
import com.example.interactive_system.ui.theme.Gray60
import com.example.interactive_system.ui.theme.primaryFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = Gray60,
        fontFamily = primaryFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = Color(0xFFCFCFD3),
        errorContainerColor = Color.Transparent,
    ),
    shape: Shape = RoundedCornerShape(4.dp),
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        readOnly = readOnly,
        maxLines = maxLines,
        textStyle = textStyle,
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            singleLine = singleLine,
            enabled = enabled,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(
                vertical = 14.dp, horizontal = 16.dp
            ),
            placeholder = placeholder,
            colors = colors,
            isError = error != null,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            supportingText = {
                if (error != null) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = error,
                        color = MaterialTheme.colorScheme.error
                    )
                } else supportingText?.invoke()
            },
            label = label,
            container = {
                OutlinedTextFieldDefaults.ContainerBox(enabled, error != null, interactionSource, colors, shape)
            }
        )
    }
}


@Composable
fun DecoratedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = Gray60,
        fontFamily = primaryFontFamily,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(4.dp),
) {
    PrimaryTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        error = error,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        interactionSource = interactionSource,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Gray5,
            focusedIndicatorColor = Gray15,
            unfocusedIndicatorColor = Gray5,
            errorContainerColor = Color.Transparent,
        ),
        shape = shape
    )
}


@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    PrimaryTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible.value) {
                painterResource(id = R.drawable.ic_visible)
            } else {
                painterResource(id = R.drawable.ic_invisible)
            }

            val description = if (passwordVisible.value) stringResource(R.string.hide_password) else stringResource(R.string.show_password)

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(painter = icon, description)
            }
        }
    )

}


@Composable
fun DecoratedPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    DecoratedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        error = error,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible.value) {
                painterResource(id = R.drawable.ic_visible)
            } else {
                painterResource(id = R.drawable.ic_invisible)
            }

            val description = if (passwordVisible.value) stringResource(R.string.hide_password) else stringResource(R.string.show_password)

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(painter = icon, description)
            }
        }
    )

}

@Composable
fun ReadonlyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    error: String? = null
) {
    Box(modifier = modifier) {
        PrimaryTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = visualTransformation,
            error = error
        )

        // TODO ripple
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable(onClick = onClick),
        )
    }
}

@Preview
@Composable
fun TextField_Preview() {
    ScreenPreview{
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text("Primary fields")

            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Test test test",
                onValueChange = {}
            )

            PrimaryTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Test test test",
                onValueChange = {},
                error = "Test error"
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Test test test",
                onValueChange = {}
            )

            Text("Decorated fields")

            DecoratedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Test test",
                onValueChange = {}
            )

            DecoratedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Test test",
                onValueChange = {},
                error = "test error"
            )

            DecoratedPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Test test test",
                onValueChange = {}
            )

            Text("Other")

            ReadonlyTextField(
                value = "Test",
                onValueChange = {},
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}