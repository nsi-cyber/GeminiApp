package com.papara.geminiapp.data.repository

import com.papara.geminiapp.data.remote.ApiService
import com.papara.geminiapp.data.remote.model.request.MessageRequestBody
import com.papara.geminiapp.data.remote.model.response.MessageResponse
import com.papara.geminiapp.domain.repository.ApiRepository
import javax.inject.Inject


class ApiRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ApiRepository {
    override suspend fun sendMessage(apiKey: String?, body: MessageRequestBody): MessageResponse? {
      return  api.sendMessage(apiKey = apiKey, body = body)
    }


}