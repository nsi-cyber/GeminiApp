package com.papara.geminiapp.presentation.navigation

import androidx.navigation.NavHostController


object Destination {
    const val SPLASH = "splash"
    const val CONVERSATION_LIST = "conversation_list"
    const val CONVERSATION_DETAIL = "conversation_detail"

}

class NavigationActions(private val navController : NavHostController) {
    fun navigateToConversationList() {
        navController.navigate(Destination.CONVERSATION_LIST) {
            popUpTo(Destination.SPLASH) {
                inclusive = true
                saveState = true
            }
        }
    }


    fun navigateToConversationDetail(id:Long?=-1L) {
        navController.navigate("${Destination.CONVERSATION_DETAIL}?id=$id")
    }


}