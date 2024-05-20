package com.papara.geminiapp.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TypeWriterText(
    text: String,
) {

    var textToDisplay by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        key1 = text,
    ) {

            text.forEachIndexed { charIndex, _ ->
                textToDisplay = text
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(50)
            }
    }

    Text(
        text = textToDisplay,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    )
}