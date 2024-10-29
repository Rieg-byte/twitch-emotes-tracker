package com.rieg

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun Application() {
    Div(
        attrs = {
            // specify attributes here
            style {
                // specify inline style here
            }
        }
    ) {
        Text("Hello World")
    }
}