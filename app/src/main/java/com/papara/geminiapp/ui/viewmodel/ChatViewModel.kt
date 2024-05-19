package com.papara.geminiapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papara.geminiapp.data.model.ChatMessage
import com.papara.geminiapp.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                val response = chatRepository.sendMessage(message)
                _messages.value += ChatMessage(
                    sender = "Bot",
                    content = response.response,
                    timestamp = System.currentTimeMillis()
                )
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}