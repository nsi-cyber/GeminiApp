package com.papara.geminiapp.presentation.conversation_list_screen

import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.data.models.ConversationModel



data class ConversationListScreenState(
    val conversationList: MutableList<Conversation> = mutableListOf(),
)

