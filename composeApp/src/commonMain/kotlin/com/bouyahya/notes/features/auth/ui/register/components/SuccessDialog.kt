package com.bouyahya.notes.features.auth.ui.register.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SuccessDialog(
    onDismissRequest: () -> Unit,
    onNavigate: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = "Congratulations!") },
        text = { Text(text = "You have successfully registered") },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryVariant
                ),
                onClick = onNavigate
            ) {
                Text(
                    text = "Login",
                    color = Color.White
                )
            }
        }
    )
}