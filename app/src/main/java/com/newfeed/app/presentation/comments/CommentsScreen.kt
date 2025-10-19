package com.newfeed.app.presentation.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.newfeed.app.domain.model.Comment
import com.newfeed.app.domain.model.User
import com.newfeed.app.presentation.components.UserAvatar
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    onNavigateBack: () -> Unit,
    viewModel: CommentsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val commentText by viewModel.commentText.collectAsState()
    val isPosting by viewModel.isPosting.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comments") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = commentText,
                        onValueChange = { viewModel.onCommentTextChange(it) },
                        modifier = Modifier
                            .weight(1f),
                        placeholder = { Text("Write a comment...") },
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                        )
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    IconButton(
                        onClick = { viewModel.onPostComment() },
                        enabled = commentText.isNotBlank() && !isPosting
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send comment",
                            tint = if (commentText.isNotBlank()) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is CommentsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is CommentsUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            is CommentsUiState.Success -> {
                if (state.comments.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No comments yet. Be the first to comment!",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(paddingValues)
                    ) {
                        items(state.comments, key = { it.id }) { comment ->
                            CommentItem(comment = comment)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CommentItem(
    comment: Comment,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        UserAvatar(
            imageUrl = comment.user.profileImageUrl,
            size = 36.dp
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = comment.user.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = comment.content,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Row(
                modifier = Modifier.padding(start = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = formatTimestamp(comment.createdAt),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (comment.likesCount > 0) {
                    Text(
                        text = "${comment.likesCount} likes",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    
    return when {
        diff < 60_000 -> "Just now"
        diff < 3600_000 -> "${diff / 60_000}m"
        diff < 86400_000 -> "${diff / 3600_000}h"
        diff < 604800_000 -> "${diff / 86400_000}d"
        else -> {
            val sdf = SimpleDateFormat("MMM d", Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommentsScreenContent(
    uiState: CommentsUiState,
    commentText: String,
    isPosting: Boolean,
    onNavigateBack: () -> Unit,
    onCommentTextChange: (String) -> Unit,
    onPostComment: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comments") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = commentText,
                        onValueChange = onCommentTextChange,
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Write a comment...") },
                        shape = RoundedCornerShape(24.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                        )
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    IconButton(
                        onClick = onPostComment,
                        enabled = commentText.isNotBlank() && !isPosting
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send comment",
                            tint = if (commentText.isNotBlank()) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is CommentsUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is CommentsUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
            is CommentsUiState.Success -> {
                if (state.comments.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No comments yet. Be the first to comment!",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(paddingValues)
                    ) {
                        items(state.comments, key = { it.id }) { comment ->
                            CommentItem(comment = comment)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentsScreenPreview() {
    MaterialTheme {
        CommentsScreenContent(
            uiState = CommentsUiState.Success(
                comments = listOf(
                    Comment(
                        id = 1,
                        postId = 1,
                        user = User(
                            id = 1,
                            name = "John Doe",
                            email = "john@example.com",
                            profileImageUrl = null
                        ),
                        content = "This is a great post!",
                        likesCount = 5,
                        createdAt = System.currentTimeMillis() - 3600000
                    ),
                    Comment(
                        id = 2,
                        postId = 1,
                        user = User(
                            id = 2,
                            name = "Jane Smith",
                            email = "jane@example.com",
                            profileImageUrl = null
                        ),
                        content = "I totally agree with this! Thanks for sharing.",
                        likesCount = 12,
                        createdAt = System.currentTimeMillis() - 7200000
                    ),
                    Comment(
                        id = 3,
                        postId = 1,
                        user = User(
                            id = 3,
                            name = "Bob Wilson",
                            email = "bob@example.com",
                            profileImageUrl = null
                        ),
                        content = "Interesting perspective! 🤔",
                        likesCount = 3,
                        createdAt = System.currentTimeMillis() - 1800000
                    )
                )
            ),
            commentText = "",
            isPosting = false,
            onNavigateBack = {},
            onCommentTextChange = {},
            onPostComment = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentsScreenEmptyPreview() {
    MaterialTheme {
        CommentsScreenContent(
            uiState = CommentsUiState.Success(comments = emptyList()),
            commentText = "",
            isPosting = false,
            onNavigateBack = {},
            onCommentTextChange = {},
            onPostComment = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentsScreenLoadingPreview() {
    MaterialTheme {
        CommentsScreenContent(
            uiState = CommentsUiState.Loading,
            commentText = "",
            isPosting = false,
            onNavigateBack = {},
            onCommentTextChange = {},
            onPostComment = {}
        )
    }
}
