package com.papara.geminiapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.papara.geminiapp.presentation.chat_screen.ChatScreen
import com.papara.geminiapp.presentation.conversation_list_screen.ConversationListScreen
import com.papara.geminiapp.presentation.splash_screen.SplashScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier.background(MaterialTheme.colorScheme.primary),
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.SPLASH,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Destination.SPLASH
        ) {
            SplashScreen(
                onSplashFinished = {
                navActions.navigateToConversationList()
                }
            )
        }

        composable(
            route = Destination.CONVERSATION_LIST
        ) {
            ConversationListScreen() {
                navActions.navigateToConversationDetail(it)
            }
        }

        composable(
            route = "${Destination.CONVERSATION_DETAIL}?id={id}", arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })
        ) { navBackStackEntry ->
            if (navBackStackEntry.arguments?.getLong("id")!=-1L)
            ChatScreen(conversationId = navBackStackEntry.arguments?.getLong("id"))
            else
                ChatScreen(conversationId = null)

        }


    }

}