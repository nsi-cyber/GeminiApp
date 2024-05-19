package com.papara.geminiapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papara.geminiapp.presentation.chat_screen.Chat

@Composable
fun MessageItem(
    message: Chat
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.isFromUser == false) Arrangement.Start else Arrangement.End
    ) {
        Card(
            //backgroundColor = if (message.sender == "Bot") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            //contentColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message.prompt,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

