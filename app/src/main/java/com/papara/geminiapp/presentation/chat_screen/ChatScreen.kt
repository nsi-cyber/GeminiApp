package com.papara.geminiapp.presentation.chat_screen

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.papara.geminiapp.R
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.presentation.components.ChatInput
import com.papara.geminiapp.presentation.components.DotsAnimation
import com.papara.geminiapp.presentation.components.MessageItem
import com.papara.geminiapp.presentation.conversation_list_screen.ConversationListScreenViewModel
import com.papara.geminiapp.utils.rememberKeyboardVisibility
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ChatScreen(
    viewModel: ChatScreenViewModel = hiltViewModel<ChatScreenViewModel>(),
    conversationId: Long?
) {
    val chatListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val isKeyboardVisible by rememberKeyboardVisibility()
    val messages by viewModel.chatScreenState.collectAsState(
        initial = ChatScreenState(
            isTyping = false,
            chatList = mutableListOf<ChatMessage>(),
            prompt = ""
        )
    )
    LaunchedEffect(Unit) {
        conversationId?.let {
            viewModel.onEvent(ChatScreenEvent.LoadConversation(conversationId))
        }
    }


    LaunchedEffect(messages.chatList.size) {
        chatListState.animateScrollToItem(messages.chatList.size)
    }
    LaunchedEffect(isKeyboardVisible) {
        chatListState.animateScrollToItem(messages.chatList.size)

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.outlineVariant),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp), state = chatListState
        ) {
            items(messages.chatList.size) { message ->
                MessageItem(
                    messages.chatList[message],
                    messages.isTyping && message == messages.chatList.size - 1,
                    scroll = {
                        coroutineScope.launch {
                            chatListState.animateScrollToItem(messages.chatList.size)
                        }
                    },
                    onTypeFinished = {
                         viewModel.onEvent(ChatScreenEvent.TypingFinished)
                    }
                )
            }

            item {
                if (messages.isLoading) {
                    Box(

                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 20.dp,
                                    topStart = 0.dp,
                                    bottomEnd = 20.dp,
                                    bottomStart = 20.dp
                                )
                            )
                            .background(MaterialTheme.colorScheme.onTertiary)
                            .padding(8.dp)
                    ) {
                        DotsAnimation(dotSize = 8.dp)

                    }
                }
            }
        }

        ChatInput(
            modifier = Modifier,
            onSendMessage = { prompt -> viewModel.onEvent(ChatScreenEvent.SendPrompt(prompt = prompt)) }
            , onMessageChanged = {viewModel.onEvent(ChatScreenEvent.UpdatePrompt(it))},
            textInput = messages.prompt
        )
    }


}