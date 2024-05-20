package com.papara.geminiapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
    navigateToChat: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Menü",
            //style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = navigateToChat,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Geçmiş Sohbetler")
        }
        // Diğer menü öğelerini buraya ekleyebilirsiniz
    }
}