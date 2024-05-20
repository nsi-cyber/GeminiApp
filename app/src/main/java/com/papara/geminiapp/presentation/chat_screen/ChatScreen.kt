package com.papara.geminiapp.presentation.chat_screen

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.presentation.components.ChatInput
import com.papara.geminiapp.presentation.components.DotsAnimation
import com.papara.geminiapp.presentation.components.MessageItem
import com.papara.geminiapp.presentation.conversation_list_screen.ConversationListScreenViewModel


@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel = hiltViewModel<ChatScreenViewModel>(),
    conversationId: Long?
) {
    LaunchedEffect(Unit) {
        conversationId?.let {
            viewModel.onEvent(ChatScreenEvent.LoadConversation(conversationId))
        }
    }
    val messages by viewModel.chatScreenState.collectAsState(
        initial = ChatScreenState(
            chatList = mutableListOf<ChatMessage>(),
            prompt = ""
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(messages.chatList.size) { message ->
                MessageItem(messages.chatList.get(message))
            }

            item {
                if (messages.isLoading) {
                    DotsAnimation(dotSize = 24.dp)
                }
            }
        }

        ChatInput(
            modifier = Modifier.padding(16.dp),
            onSendMessage = { prompt -> viewModel.onEvent(ChatScreenEvent.SendPrompt(prompt = prompt)) }
        )
    }
}