package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.data.models.ConversationModel



data class ConversationListScreenState(
    val conversationList: MutableList<Conversation> = mutableListOf(),
    val isLoading: MutableState<Boolean> = mutableStateOf(false),
)

