package com.papara.geminiapp.presentation.chat_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papara.geminiapp.common.Resource
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.remote.model.request.Content
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.request.Part
import com.papara.geminiapp.domain.useCase.chat.SendMessageUseCase
import com.papara.geminiapp.domain.useCase.database.SavePromptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val savePromptUseCase: SavePromptUseCase
) : ViewModel() {

    private val _chatScreenState = MutableStateFlow(ChatScreenState())
    val chatScreenState = _chatScreenState.asStateFlow()
init{

}
    fun onEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    addPrompt(event.prompt, isUser = true)
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
        private fun addPrompt(prompt: String?,isUser:Boolean) {
        saveToDatabase( ChatMessage(conversationId = 1,message = prompt!!, isFromUser = isUser))

        _chatScreenState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(Chat(prompt.orEmpty(), isUser))
                },
                prompt = ""
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            sendMessageUseCase(
                apiKey = "AIzaSyCDOf1x4xf_0-_kT4np8Oyj-o1m0cGDUmA", body = MessageRequestBody(
                    contents = listOf(
                        Content(
                            parts = listOf(
                                Part(prompt)
                            )
                        )
                    )
                )
            ).onEach {message ->
                when (message) {


                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Empty -> {}
                    is Resource.Success ->{
                            addPrompt(prompt=message.data?.candidates?.first()?.content?.parts?.first()?.text, isUser = false)
                    }
                }
            }.launchIn(this)
        }
    }


    private fun saveToDatabase(prompt: ChatMessage) {
        viewModelScope.launch {
            savePromptUseCase(prompt).onEach {message ->

            }.launchIn(this)
        }
    }


}