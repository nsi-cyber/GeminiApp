package com.papara.geminiapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.papara.geminiapp.data.local.entity.ChatMessage

@Composable
fun MessageItem(
    message: ChatMessage, isTyping: Boolean, onTypeFinished: () -> Unit,scroll:()->Unit
) {
    var lineCount by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = if (!message.isFromUser) Arrangement.Start else Arrangement.End
    ) {
        Box(
            //backgroundColor = if (message.sender == "Bot") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            //contentColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = if (!message.isFromUser) 20.dp else 0.dp,
                        topStart = if (message.isFromUser) 20.dp else 0.dp,
                        bottomEnd = 20.dp,
                        bottomStart = 20.dp
                    )
                )
                .background(if (!message.isFromUser) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.tertiary)
                .padding(8.dp)
        ) {
            if (message.isFromUser)
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            else if (isTyping )
                TypewriterTextEffect(
                    text = message.message,
                    onEffectCompleted = onTypeFinished
                ) { displayedText ->
                    Text(onTextLayout ={ textLayoutResult ->
                        val newLineCount = textLayoutResult.lineCount
                        if (newLineCount != lineCount) {
                            lineCount = newLineCount
                            scroll()
                        }
                    } ,
                        text = displayedText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            else
                Text(
                    text = message.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
        }
    }
}

