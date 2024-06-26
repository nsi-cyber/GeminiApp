package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.papara.geminiapp.data.local.entity.Conversation


data class ConversationListScreenState(
    val conversationList: MutableList<Conversation> = mutableListOf(),
    val isLoading: MutableState<Boolean> = mutableStateOf(true),
)

