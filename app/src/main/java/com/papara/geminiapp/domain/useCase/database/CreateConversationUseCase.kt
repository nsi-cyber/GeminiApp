package com.papara.geminiapp.domain.useCase.database

import android.util.Log
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class CreateConversationUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    operator fun invoke(description: String): Flow<Long> =
        flow {
            try {
                val conversation = Conversation(description = description)
                val conversationId = repo.createConversation(conversation)
                emit(conversationId)
            } catch (e: Exception) {
                Log.e("CreateConversationUseCase Error: ", e.toString())
            }
        }
}