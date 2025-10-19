package com.newfeed.app.presentation.newsfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.model.User
import com.newfeed.app.presentation.components.CreatePostBar
import com.newfeed.app.presentation.components.PostCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToCreatePost: () -> Unit,
    onNavigateToComments: (Long) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToMenu: () -> Unit,
    viewModel: NewsFeedViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentUser by viewModel.currentUser.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Newsfeed",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = { /* Messenger */ }) {
                        Icon(
                            imageVector = Icons.Default.Message,
                            contentDescription = "Messenger"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                    
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = onNavigateToProfile
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
                    label = { Text("Notifications") },
                    selected = false,
                    onClick = onNavigateToNotifications
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                    label = { Text("Menu") },
                    selected = false,
                    onClick = onNavigateToMenu
                )
            }
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is NewsFeedUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is NewsFeedUiState.Error -> {
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
            is NewsFeedUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(paddingValues)
                ) {
                    item {
                        CreatePostBar(
                            userProfileImageUrl = currentUser?.profileImageUrl,
                            onCreatePostClick = onNavigateToCreatePost
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    
                    items(state.posts, key = { it.id }) { post ->
                        PostCard(
                            post = post,
                            onLikeClick = { viewModel.onLikeClick(post) },
                            onCommentClick = { onNavigateToComments(post.id) },
                            onShareClick = { /* Share */ }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewsFeedScreenContent(
    uiState: NewsFeedUiState,
    currentUser: User?,
    onNavigateToProfile: () -> Unit,
    onNavigateToCreatePost: () -> Unit,
    onNavigateToComments: (Long) -> Unit,
    onNavigateToNotifications: () -> Unit,
    onNavigateToMenu: () -> Unit,
    onLikeClick: (Post) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Newsfeed",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { /* Search */ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = { /* Messenger */ }) {
                        Icon(
                            imageVector = Icons.Default.Message,
                            contentDescription = "Messenger"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = onNavigateToProfile
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
                    label = { Text("Notifications") },
                    selected = false,
                    onClick = onNavigateToNotifications
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                    label = { Text("Menu") },
                    selected = false,
                    onClick = onNavigateToMenu
                )
            }
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is NewsFeedUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is NewsFeedUiState.Error -> {
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
            is NewsFeedUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(paddingValues)
                ) {
                    item {
                        CreatePostBar(
                            userProfileImageUrl = currentUser?.profileImageUrl,
                            onCreatePostClick = onNavigateToCreatePost
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    
                    items(state.posts, key = { it.id }) { post ->
                        PostCard(
                            post = post,
                            onLikeClick = { onLikeClick(post) },
                            onCommentClick = { onNavigateToComments(post.id) },
                            onShareClick = { /* Share */ }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsFeedScreenPreview() {
    MaterialTheme {
        NewsFeedScreenContent(
            uiState = NewsFeedUiState.Success(
                posts = listOf(
                    Post(
                        id = 1,
                        user = User(
                            id = 1,
                            name = "John Doe",
                            email = "john@example.com",
                            profileImageUrl = null
                        ),
                        content = "Just finished an amazing hike! The view from the top was breathtaking. 🏔️",
                        imageUrl = null,
                        likesCount = 42,
                        commentsCount = 5,
                        sharesCount = 2,
                        isLiked = false,
                        createdAt = System.currentTimeMillis() - 3600000
                    ),
                    Post(
                        id = 2,
                        user = User(
                            id = 2,
                            name = "Jane Smith",
                            email = "jane@example.com",
                            profileImageUrl = null
                        ),
                        content = "New blog post is live! Check out my latest thoughts on mobile development.",
                        imageUrl = null,
                        likesCount = 128,
                        commentsCount = 15,
                        sharesCount = 8,
                        isLiked = true,
                        createdAt = System.currentTimeMillis() - 7200000
                    )
                )
            ),
            currentUser = User(
                id = 1,
                name = "Current User",
                email = "user@example.com",
                profileImageUrl = null
            ),
            onNavigateToProfile = {},
            onNavigateToCreatePost = {},
            onNavigateToComments = {},
            onNavigateToNotifications = {},
            onNavigateToMenu = {},
            onLikeClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsFeedScreenLoadingPreview() {
    MaterialTheme {
        NewsFeedScreenContent(
            uiState = NewsFeedUiState.Loading,
            currentUser = null,
            onNavigateToProfile = {},
            onNavigateToCreatePost = {},
            onNavigateToComments = {},
            onNavigateToNotifications = {},
            onNavigateToMenu = {},
            onLikeClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsFeedScreenErrorPreview() {
    MaterialTheme {
        NewsFeedScreenContent(
            uiState = NewsFeedUiState.Error("Failed to load posts"),
            currentUser = null,
            onNavigateToProfile = {},
            onNavigateToCreatePost = {},
            onNavigateToComments = {},
            onNavigateToNotifications = {},
            onNavigateToMenu = {},
            onLikeClick = {}
        )
    }
}
