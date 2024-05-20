package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papara.geminiapp.common.Resource
import com.papara.geminiapp.data.remote.model.request.Content
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.request.Part
import com.papara.geminiapp.domain.useCase.chat.SendMessageUseCase
import com.papara.geminiapp.domain.useCase.database.GetConversationsUseCase
import com.papara.geminiapp.presentation.chat_screen.Chat
import com.papara.geminiapp.presentation.chat_screen.ChatScreenEvent
import com.papara.geminiapp.presentation.chat_screen.ChatScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ConversationListScreenViewModel @Inject constructor(
    private val getConversationsUseCase: GetConversationsUseCase,
) : ViewModel() {

    private val _conversationListScreenState = MutableStateFlow(ConversationListScreenState())
    val conversationListScreenState = _conversationListScreenState.asStateFlow()

    init {
        getConversations()
    }



    private fun getConversations() {
        viewModelScope.launch {
            getConversationsUseCase().onEach {conversations ->
                _conversationListScreenState.update {
                  it.copy(conversationList = conversations.toMutableList())
                }

            }.launchIn(this)
        }
    }




}