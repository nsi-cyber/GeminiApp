package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.papara.geminiapp.R
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.presentation.components.ConversationListItem
import com.papara.geminiapp.presentation.components.EmptyListView
import com.papara.geminiapp.presentation.components.GeminiTopBar
import com.papara.geminiapp.presentation.components.SwipeToDeleteContainer
import com.papara.geminiapp.utils.observeLifecycleEvents


@Composable
fun ConversationListScreen(
    viewModel: ConversationListScreenViewModel = hiltViewModel<ConversationListScreenViewModel>(),
    redirectToChat: (id: Long) -> Unit
) {
    viewModel.observeLifecycleEvents(LocalLifecycleOwner.current.lifecycle)


    val conversationState by viewModel.conversationListScreenState.collectAsState(
        initial = ConversationListScreenState(
            conversationList = mutableListOf<Conversation>(),
            isLoading = remember { mutableStateOf(true) }
        )
    )


    Scaffold(topBar = {
        GeminiTopBar()
    }, floatingActionButton = {

        FloatingButton {
            redirectToChat(-1)
            viewModel.onEvent(ConversationListScreenEvent.OnLoading)

        }

    }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (conversationState.isLoading.value)
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(modifier = Modifier.size(74.dp))
                }
            else if (conversationState.conversationList.isEmpty()) {
                EmptyListView()
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                ) {

                    item {
                        Column (modifier = Modifier.padding(16.dp)){
                            Text(
                                text = stringResource(id = R.string.last),
                                color = MaterialTheme.colorScheme.outline,
                                fontSize = 22.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = stringResource(id = R.string.conversations),
                                color = MaterialTheme.colorScheme.outline,
                                fontSize = 28.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }


                    items(conversationState.conversationList.size, key = {conversationState.conversationList.get(it).id}) { conversation ->
                        SwipeToDeleteContainer(
                            item = conversationState.conversationList[conversation],
                            onDelete = {
                                viewModel.onEvent(ConversationListScreenEvent.OnLoading)
                                viewModel.onEvent(ConversationListScreenEvent.DeleteConversation(it))
                            }
                        ) {
                            ConversationListItem(conversationState.conversationList[conversation]) {
                                redirectToChat(
                                    it
                                )
                                viewModel.onEvent(ConversationListScreenEvent.OnLoading)

                            }
                        }
                        if (conversation < conversationState.conversationList.size-1)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 42.dp)
                                    .height(1.dp)
                                    .background(MaterialTheme.colorScheme.primary)

                            )

                    }


                }
            }


        }
    }

}

@Composable
fun FloatingButton(onClick: () -> Unit) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(99.dp))
        .background(MaterialTheme.colorScheme.scrim)
        .clickable { onClick() }) {
        Image(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "New Chat Logo",
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}






