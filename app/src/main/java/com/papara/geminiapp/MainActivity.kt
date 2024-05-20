package com.papara.geminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.papara.geminiapp.presentation.chat_screen.ChatScreen
import com.papara.geminiapp.presentation.chat_screen.ChatScreenViewModel
import com.papara.geminiapp.presentation.conversation_list_screen.ConversationListScreen
import com.papara.geminiapp.presentation.conversation_list_screen.ConversationListScreenViewModel
import com.papara.geminiapp.presentation.navigation.NavigationGraph
import com.papara.geminiapp.ui.theme.GeminiAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val chatViewModel: ChatScreenViewModel by viewModels()
    private val databaseViewModel: ConversationListScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiAppTheme {
                NavigationGraph()

                // A surface container using the 'background' color from the theme

                  //  ConversationListScreen(viewModel = databaseViewModel)
                    //ChatScreen(viewModel = chatViewModel)

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GeminiAppTheme {
        Greeting("Android")
    }
}