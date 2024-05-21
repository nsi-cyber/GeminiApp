package com.papara.geminiapp.domain.useCase.database

import android.util.Log
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetConversationsUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    operator fun invoke(): Flow<List<Conversation>> =
        flow {
            try {
                emit(repo.getAllConversations())
            }
            catch (e: Exception){
                Log.e("GetMessagesUseCase Error: ",e.toString())
            }
        }
}