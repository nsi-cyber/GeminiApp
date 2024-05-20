package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.presentation.chat_screen.ChatScreenState
import com.papara.geminiapp.presentation.components.DotsAnimation
import com.papara.geminiapp.presentation.components.MessageItem
import com.papara.geminiapp.utils.observeLifecycleEvents


@Composable
fun ConversationListScreen(
    viewModel: ConversationListScreenViewModel = hiltViewModel<ConversationListScreenViewModel>(),
    redirectToChat: (id: Long) -> Unit
) {
    viewModel.observeLifecycleEvents(LocalLifecycleOwner.current.lifecycle)


    val conversationState by viewModel.conversationListScreenState.collectAsState(
        initial = ConversationListScreenState(
            conversationList = mutableListOf<Conversation>(),

            )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if(conversationState.isLoading.value)
Text(text ="Loading" )


        Button(onClick = {redirectToChat(-1) }) {
            Box {
                Text(text = "New Chat",modifier=Modifier.padding(32.dp))
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(conversationState.conversationList.size) { conversation ->
                Text(
                    text = conversationState.conversationList.get(conversation).description,
                    modifier = Modifier.clickable {
                        redirectToChat(
                            conversationState.conversationList.get(conversation).id
                        )
                    })
            }


        }


    }
}