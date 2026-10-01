package com.example.sumativa2sacm.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sumativa2sacm.ui.components.CampoNumerico
import com.example.sumativa2sacm.ui.components.CampoTexto

@Composable
fun SumativaScreen() {

    var propietario by remember {
        mutableStateOf("")
    }

    var cantidad by remember {
        mutableStateOf("")
    }

    var precioUnitario by remember {
        mutableStateOf("")
    }

    var porcentajePropina by remember {
        mutableStateOf("")
    }

    var propina by remember {
        mutableStateOf(0.0)
    }

    var total by remember {
        mutableStateOf(0.0)
    }

    var error by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(55.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Propina",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$%.2f".format(propina),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Total pagar",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$%.2f".format(total),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(35.dp)
        )

        CampoTexto(
            valor = propietario,
            etiqueta = "Nombre del propietario",
            onValueChange = {
                propietario = it
                error = ""
            }
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        CampoNumerico(
            valor = cantidad,
            etiqueta = "Cantidad",
            onValueChange = {
                cantidad = it
                error = ""
            }
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        CampoNumerico(
            valor = precioUnitario,
            etiqueta = "Precio unitario",
            onValueChange = {
                precioUnitario = it
                error = ""
            }
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        CampoNumerico(
            valor = porcentajePropina,
            etiqueta = "Porcentaje de propina (máximo 10%)",
            onValueChange = {
                porcentajePropina = it
                error = ""
            }
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        if (error.isNotEmpty()) {

            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        Button(
            onClick = {

                if (
                    propietario.isBlank() ||
                    cantidad.isBlank() ||
                    precioUnitario.isBlank() ||
                    porcentajePropina.isBlank()
                ) {
                    error = "Todos los campos son obligatorios."
                    return@Button
                }

                val cantidadNumero =
                    cantidad.toDoubleOrNull()

                val precioNumero =
                    precioUnitario.toDoubleOrNull()

                val porcentajeNumero =
                    porcentajePropina.toDoubleOrNull()

                if (
                    cantidadNumero == null ||
                    precioNumero == null ||
                    porcentajeNumero == null
                ) {
                    error = "Ingrese valores numéricos válidos."
                    return@Button
                }

                if (cantidadNumero <= 0) {
                    error = "La cantidad debe ser mayor que 0."
                    return@Button
                }

                if (precioNumero <= 0) {
                    error = "El precio unitario debe ser mayor que 0."
                    return@Button
                }

                if (
                    porcentajeNumero < 0 ||
                    porcentajeNumero > 10
                ) {
                    error = "La propina no puede superar el 10%."
                    return@Button
                }

                val subtotal =
                    cantidadNumero * precioNumero

                propina =
                    subtotal * (porcentajeNumero / 100)

                total =
                    subtotal + propina

                error = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular total")
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {

                propietario = ""
                cantidad = ""
                precioUnitario = ""
                porcentajePropina = ""

                propina = 0.0
                total = 0.0
                error = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar")
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}