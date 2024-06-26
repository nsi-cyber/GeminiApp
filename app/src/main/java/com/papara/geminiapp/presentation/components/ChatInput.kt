package com.papara.geminiapp.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.papara.geminiapp.R

@Composable
fun ChatInput(textInput:String,
    modifier: Modifier = Modifier,
    onSendMessage: (String) -> Unit,
              onMessageChanged: (String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        TextField(colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.outline,
            unfocusedTextColor = MaterialTheme.colorScheme.outline
        ),
            value = textInput,
            onValueChange = { onMessageChanged(it) },
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(25.dp))
                ,
            placeholder = { Text(stringResource(id = R.string.text_field_placeholder)) }
        )

        Box(modifier = Modifier
            .clip(RoundedCornerShape(99.dp))
            .background(MaterialTheme.colorScheme.scrim)

            .clickable { onSendMessage(textInput) }) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp),
                painter = painterResource(id = R.drawable.ic_sent),
                contentDescription = "Send", tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}