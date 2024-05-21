package com.papara.geminiapp.presentation.conversation_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text

import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.papara.geminiapp.R
import com.papara.geminiapp.data.local.entity.Conversation
import com.papara.geminiapp.presentation.components.GeminiTopBar
import com.papara.geminiapp.utils.observeLifecycleEvents
import kotlinx.coroutines.delay


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
                                text = "Last",
                                color = MaterialTheme.colorScheme.outline,
                                fontSize = 22.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "Conversations",
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
                                modifier = Modifier.fillMaxWidth().padding(start = 42.dp)
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
            modifier = Modifier.padding(16.dp)
                .size(24.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
fun EmptyListView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty),
            contentDescription = "Empty Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 12.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(text = "You dont have any conversation")
    }
}

@Composable
fun ConversationListItem(data: Conversation, onClick: (id: Long) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .clickable { onClick(data.id) }
            .padding(14.dp),


        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = "Chat Logo",
            modifier = Modifier.size(28.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Text(text = data.description)
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    val state = rememberDismissState(
        confirmValueChange = { value ->
            if (value == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart)
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}