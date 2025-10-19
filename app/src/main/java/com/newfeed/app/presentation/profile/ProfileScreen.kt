package com.newfeed.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.model.User
import com.newfeed.app.presentation.components.PostCard
import com.newfeed.app.presentation.components.UserAvatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit,
    onNavigateToComments: (Long) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
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
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is ProfileUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProfileUiState.Error -> {
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
            is ProfileUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(paddingValues)
                ) {
                    // Cover Photo with Avatar Overlay
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp), // Increased to accommodate avatar
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            // Cover Photo
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .align(Alignment.TopCenter)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            if (state.user.coverImageUrl != null) {
                                AsyncImage(
                                    model = state.user.coverImageUrl,
                                    contentDescription = "Cover photo",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            }
                            
                            // Profile Avatar overlaying cover photo
                            UserAvatar(
                                imageUrl = state.user.profileImageUrl,
                                size = 120.dp,
                                showBorder = true,
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                    
                    // Profile Info
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                
                                // Name
                                Text(
                                    text = state.user.name,
                                    style = MaterialTheme.typography.displayLarge
                                )
                                
                                // Bio
                                if (state.user.bio != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = state.user.bio,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                // Stats
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    ProfileStat(
                                        count = state.posts.size,
                                        label = "Posts"
                                    )
                                    ProfileStat(
                                        count = state.user.friendsCount,
                                        label = "Friends"
                                    )
                                    ProfileStat(
                                        count = state.user.photosCount,
                                        label = "Photos"
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(16.dp))
                                
                                // Edit Profile Button
                                Button(
                                    onClick = { /* Edit profile */ },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Edit Profile")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    
                    // Posts Section Header
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text(
                                text = "Posts",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    
                    // User Posts
                    items(state.posts, key = { it.id }) { post ->
                        PostCard(
                            post = post,
                            onLikeClick = { /* Like */ },
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

@Composable
private fun ProfileStat(
    count: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenContent(
    uiState: ProfileUiState,
    onNavigateBack: () -> Unit,
    onNavigateToComments: (Long) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
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
        }
    ) { paddingValues ->
        when (val state = uiState) {
            is ProfileUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is ProfileUiState.Error -> {
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
            is ProfileUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(paddingValues)
                ) {
                    // Cover Photo with Avatar Overlay
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp), // Increased to accommodate avatar
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            // Cover Photo
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .align(Alignment.TopCenter)
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                if (state.user.coverImageUrl != null) {
                                    AsyncImage(
                                        model = state.user.coverImageUrl,
                                        contentDescription = "Cover photo",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            
                            // Profile Avatar overlaying cover photo
                            UserAvatar(
                                imageUrl = state.user.profileImageUrl,
                                size = 120.dp,
                                showBorder = true,
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = state.user.name,
                                    style = MaterialTheme.typography.displayLarge
                                )

                                if (state.user.bio != null) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = state.user.bio,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    ProfileStat(
                                        count = state.posts.size,
                                        label = "Posts"
                                    )
                                    ProfileStat(
                                        count = state.user.friendsCount,
                                        label = "Friends"
                                    )
                                    ProfileStat(
                                        count = state.user.photosCount,
                                        label = "Photos"
                                    )
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                Button(
                                    onClick = { },
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Edit Profile")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text(
                                text = "Posts",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    
                    items(state.posts, key = { it.id }) { post ->
                        PostCard(
                            post = post,
                            onLikeClick = { },
                            onCommentClick = { onNavigateToComments(post.id) },
                            onShareClick = { }
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
private fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreenContent(
            uiState = ProfileUiState.Success(
                user = User(
                    id = 1,
                    name = "John Doe",
                    email = "john@example.com",
                    profileImageUrl = null,
                    coverImageUrl = null,
                    bio = "Mobile developer | Tech enthusiast | Coffee lover ☕",
                    friendsCount = 342,
                    photosCount = 87
                ),
                posts = listOf(
                    Post(
                        id = 1,
                        user = User(
                            id = 1,
                            name = "John Doe",
                            email = "john@example.com",
                            profileImageUrl = null
                        ),
                        content = "Just deployed my new Android app! Check it out on the Play Store.",
                        imageUrl = null,
                        likesCount = 52,
                        commentsCount = 12,
                        sharesCount = 4,
                        isLiked = false,
                        createdAt = System.currentTimeMillis() - 86400000
                    ),
                    Post(
                        id = 2,
                        user = User(
                            id = 1,
                            name = "John Doe",
                            email = "john@example.com",
                            profileImageUrl = null
                        ),
                        content = "Beautiful sunset from my office window 🌅",
                        imageUrl = null,
                        likesCount = 128,
                        commentsCount = 23,
                        sharesCount = 8,
                        isLiked = true,
                        createdAt = System.currentTimeMillis() - 172800000
                    )
                )
            ),
            onNavigateBack = {},
            onNavigateToComments = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenLoadingPreview() {
    MaterialTheme {
        ProfileScreenContent(
            uiState = ProfileUiState.Loading,
            onNavigateBack = {},
            onNavigateToComments = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenErrorPreview() {
    MaterialTheme {
        ProfileScreenContent(
            uiState = ProfileUiState.Error("Failed to load profile"),
            onNavigateBack = {},
            onNavigateToComments = {}
        )
    }
}
