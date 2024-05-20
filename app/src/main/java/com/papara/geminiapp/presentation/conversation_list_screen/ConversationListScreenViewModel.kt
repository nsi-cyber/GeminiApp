package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.papara.geminiapp.domain.useCase.database.GetConversationsUseCase
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
) : ViewModel(), DefaultLifecycleObserver {

    private val _conversationListScreenState = MutableStateFlow(ConversationListScreenState())
    val conversationListScreenState = _conversationListScreenState.asStateFlow()




    private fun getConversations() {
        viewModelScope.launch {
            getConversationsUseCase().onEach {conversations ->
                _conversationListScreenState.update {
                  it.copy(conversationList = conversations.toMutableList(),
                      isLoading = mutableStateOf(false)
                  )
                }

            }.launchIn(this)
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        _conversationListScreenState.update {
            it.copy(isLoading = mutableStateOf(true))
        }
        getConversations()
    }


}