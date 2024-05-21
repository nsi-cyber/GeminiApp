package com.papara.geminiapp.presentation.chat_screen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papara.geminiapp.common.Resource
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.remote.model.request.Content
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.request.Part
import com.papara.geminiapp.domain.useCase.chat.SendMessageUseCase
import com.papara.geminiapp.domain.useCase.database.CreateConversationUseCase
import com.papara.geminiapp.domain.useCase.database.GetMessagesUseCase
import com.papara.geminiapp.domain.useCase.database.SavePromptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val savePromptUseCase: SavePromptUseCase,
    private val createConversationUseCase: CreateConversationUseCase,
    private val getMessagesUseCase: GetMessagesUseCase
) : ViewModel() {


    private val _chatScreenState = MutableStateFlow(ChatScreenState())
    val chatScreenState = _chatScreenState.asStateFlow()

    private var _conversationId = mutableLongStateOf(-1L)
    val conversationId = _conversationId.longValue


    fun onEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.SendPrompt -> {
                if (event.prompt.isNotEmpty()) {
                    if (_conversationId.longValue != -1L) {
                        addPrompt(event.prompt, isUser = true)
                    } else {
                        createConversation(event.prompt)
                    }
                    getResponse(event.prompt)
                }
            }

            is ChatScreenEvent.UpdatePrompt -> {
                _chatScreenState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }


            is ChatScreenEvent.LoadConversation -> {
                getConversationMessages(event.conversationId)
            }

            ChatScreenEvent.TypingFinished ->    _chatScreenState.update {
                it.copy(isTyping = false)
            }
            ChatScreenEvent.TypingStarted -> _chatScreenState.update {
                it.copy(isTyping = true)
            }
        }
    }

    private fun getConversationMessages(conversationId: Long) {
        viewModelScope.launch {
            getMessagesUseCase(conversationId = conversationId).onEach { messageList ->
                _chatScreenState.update {
                    it.copy(chatList = messageList.toMutableList())
                }
            }.launchIn(this)
        }
    }

    private fun addPrompt(prompt: String?, isUser: Boolean) {

        saveToDatabase(
            ChatMessage(
                conversationId = _conversationId.longValue,
                message = prompt!!,
                isFromUser = isUser
            )
        )

        _chatScreenState.update {
            it.copy(isLoading = false, isTyping = true,
                chatList = it.chatList.toMutableList().apply {
                    add(
                        ChatMessage(
                            conversationId = _conversationId.longValue,
                            message = prompt,
                            isFromUser = isUser
                        )
                    )
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
            ).onEach { message ->
                when (message) {


                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        _chatScreenState.update {
                            it.copy(
                              isLoading = true
                            )
                        }
                    }
                    is Resource.Empty -> {}
                    is Resource.Success -> {
                        addPrompt(
                            prompt = message.data?.candidates?.first()?.content?.parts?.first()?.text,
                            isUser = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    private fun saveToDatabase(prompt: ChatMessage) {
        viewModelScope.launch {
            savePromptUseCase(prompt).launchIn(this)
        }
    }

    private fun createConversation(prompt: String) {
        viewModelScope.launch {
            // Yeni konuşmayı oluştur ve ID'sini al
            val conversationId = createConversationUseCase(prompt).first()
            _conversationId.longValue = conversationId

            // Mesajı veritabanına kaydet
            savePromptUseCase(
                ChatMessage(
                    conversationId = _conversationId.longValue,
                    message = prompt,
                    isFromUser = true
                )
            ).collect() // collect ile akışı tamamla

            // Ekran durumunu güncelle
            _chatScreenState.update {
                it.copy(
                    isLoading = false,
                    chatList = it.chatList.toMutableList().apply {
                        add(
                            ChatMessage(
                                conversationId = _conversationId.longValue,
                                message = prompt,
                                isFromUser = true
                            )
                        )
                    },
                    prompt = ""
                )
            }
        }
    }



}