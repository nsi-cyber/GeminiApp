package com.papara.geminiapp.presentation.chat_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ChatScreenViewModel: ViewModel() {
    private val _chatScreenState = MutableStateFlow(ChatScreenState())
    val chatScreenState = _chatScreenState.asStateFlow()

    fun onEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt)
                    getResponse(event.prompt)
                }
            }
            is ChatScreenEvent.UpdatePrompt -> {
                _chatScreenState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }
        }
    }

    private fun addPrompt(prompt: String) {
        _chatScreenState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt, true))
                },
                prompt = ""
            )
        }
    }

    private fun getResponse(prompt: String) {
        TODO("Not yet implemented")
    }
}