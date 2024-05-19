package com.papara.geminiapp.ui.components

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
import com.papara.geminiapp.data.model.ChatMessage

@Composable
fun MessageItem(
    message: ChatMessage
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (message.sender == "Bot") Arrangement.Start else Arrangement.End
    ) {
        Card(
            //backgroundColor = if (message.sender == "Bot") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            //contentColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = message.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun MessageItemPreview() {
    MessageItem(
        ChatMessage(
            sender = "User",
            content = "Hello!",
            timestamp = System.currentTimeMillis()
        )
    )
}