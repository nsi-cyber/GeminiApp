package com.papara.geminiapp

import dagger.hilt.android.lifecycle.HiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papara.geminiapp.common.Resource
import com.papara.geminiapp.data.remote.model.request.Content
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.request.Part
import com.papara.geminiapp.domain.useCase.chat.SendMessageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {


    fun sendMessage() {
        viewModelScope.launch {
            sendMessageUseCase(
                apiKey = "AIzaSyCDOf1x4xf_0-_kT4np8Oyj-o1m0cGDUmA", body = MessageRequestBody(
                    contents = listOf(
                        Content(
                            parts = listOf(
                                Part("Merhaba Nasılsın")
                            )
                        )
                    )
                )
            ).onEach {
                when (it) {


                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> println(it.message)
                }
            }.launchIn(this)
        }
    }
}