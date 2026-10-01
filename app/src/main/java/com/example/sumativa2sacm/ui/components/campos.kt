package com.example.sumativa2sacm.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun CampoTexto(
    valor: String,
    etiqueta: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValueChange,
        label = {
            Text(etiqueta)
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}


@Composable
fun CampoNumerico(
    valor: String,
    etiqueta: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { nuevoValor ->
            if (nuevoValor.all { it.isDigit() || it == '.' }) {
                onValueChange(nuevoValor)
            }
        },
        label = {
            Text(etiqueta)
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        )
    )
}