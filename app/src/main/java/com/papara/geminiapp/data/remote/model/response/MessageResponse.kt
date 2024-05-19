package com.papara.geminiapp.data.remote.model.response

data class MessageResponse(
    val candidates: List<Candidate>,
    val promptFeedback: PromptFeedback
)