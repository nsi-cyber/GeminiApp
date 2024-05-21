package com.papara.geminiapp.domain.useCase.database

import android.util.Log
import com.papara.geminiapp.data.local.entity.ChatMessage
import com.papara.geminiapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetMessagesUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    operator fun invoke(conversationId:Long?): Flow<List<ChatMessage>> =
        flow {
            try {
              emit(repo.getMessagesByConversationId(conversationId))
            }
            catch (e: Exception){
                Log.e("GetMessagesUseCase Error: ",e.toString())
            }
        }
}