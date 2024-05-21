package com.papara.geminiapp.domain.useCase.database

import android.util.Log
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class DeleteConversationUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    operator fun invoke(conversation: Conversation): Flow<Unit> =
        flow {
            try {
                emit(repo.deleteConversation(conversation))
            } catch (e: Exception) {
                Log.e("CreateConversationUseCase Error: ", e.toString())
            }
        }
}