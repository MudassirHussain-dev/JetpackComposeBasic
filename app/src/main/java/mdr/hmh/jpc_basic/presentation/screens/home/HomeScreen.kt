package mdr.hmh.jpc_basic.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun HomeScreen(
    email: String,
    password: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),

        ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.h3
        )

        Text(text = "Email $email, Password $password")
    }
}