package com.newfeed.app.presentation.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.newfeed.app.presentation.components.UserAvatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onNavigateBack: () -> Unit
) {
    val notifications = remember {
        getSampleNotifications()
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Mark all as read */ }) {
                        Icon(
                            imageVector = Icons.Default.DoneAll,
                            contentDescription = "Mark all as read"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        if (notifications.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No notifications yet",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                items(notifications, key = { it.id }) { notification ->
                    NotificationItem(
                        notification = notification,
                        onClick = { /* Handle notification click */ }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun NotificationItem(
    notification: NotificationData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                if (!notification.isRead) {
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon background
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(notification.getIconBackgroundColor()),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = notification.getIcon(),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = notification.getIconColor()
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notification.userName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = if (!notification.isRead) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.weight(1f)
                )
                if (!notification.isRead) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = notification.message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = notification.timeAgo,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// Data models
data class NotificationData(
    val id: Long,
    val type: NotificationType,
    val userName: String,
    val message: String,
    val timeAgo: String,
    val isRead: Boolean = false
)

enum class NotificationType {
    LIKE, COMMENT, FRIEND_REQUEST, MENTION, SHARE
}

private fun NotificationData.getIcon(): ImageVector {
    return when (type) {
        NotificationType.LIKE -> Icons.Default.ThumbUp
        NotificationType.COMMENT -> Icons.Default.ChatBubble
        NotificationType.FRIEND_REQUEST -> Icons.Default.PersonAdd
        NotificationType.MENTION -> Icons.Default.AlternateEmail
        NotificationType.SHARE -> Icons.Default.Share
    }
}

private fun NotificationData.getIconColor(): androidx.compose.ui.graphics.Color {
    return when (type) {
        NotificationType.LIKE -> androidx.compose.ui.graphics.Color(0xFF1877F2)
        NotificationType.COMMENT -> androidx.compose.ui.graphics.Color(0xFF42B72A)
        NotificationType.FRIEND_REQUEST -> androidx.compose.ui.graphics.Color(0xFFE4405F)
        NotificationType.MENTION -> androidx.compose.ui.graphics.Color(0xFFFD8D14)
        NotificationType.SHARE -> androidx.compose.ui.graphics.Color(0xFF9C27B0)
    }
}

private fun NotificationData.getIconBackgroundColor(): androidx.compose.ui.graphics.Color {
    return getIconColor().copy(alpha = 0.1f)
}

private fun getSampleNotifications(): List<NotificationData> {
    return listOf(
        NotificationData(
            id = 1,
            type = NotificationType.LIKE,
            userName = "Jane Smith",
            message = "liked your post",
            timeAgo = "5 minutes ago",
            isRead = false
        ),
        NotificationData(
            id = 2,
            type = NotificationType.COMMENT,
            userName = "Mike Johnson",
            message = "commented on your post: \"Great work!\"",
            timeAgo = "15 minutes ago",
            isRead = false
        ),
        NotificationData(
            id = 3,
            type = NotificationType.FRIEND_REQUEST,
            userName = "Sarah Wilson",
            message = "sent you a friend request",
            timeAgo = "1 hour ago",
            isRead = false
        ),
        NotificationData(
            id = 4,
            type = NotificationType.MENTION,
            userName = "Tom Brown",
            message = "mentioned you in a comment",
            timeAgo = "2 hours ago",
            isRead = true
        ),
        NotificationData(
            id = 5,
            type = NotificationType.SHARE,
            userName = "Emily Davis",
            message = "shared your post",
            timeAgo = "3 hours ago",
            isRead = true
        ),
        NotificationData(
            id = 6,
            type = NotificationType.LIKE,
            userName = "David Lee",
            message = "and 15 others liked your post",
            timeAgo = "5 hours ago",
            isRead = true
        ),
        NotificationData(
            id = 7,
            type = NotificationType.COMMENT,
            userName = "Lisa Anderson",
            message = "replied to your comment",
            timeAgo = "1 day ago",
            isRead = true
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun NotificationsScreenPreview() {
    MaterialTheme {
        NotificationsScreen(
            onNavigateBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationItemPreview() {
    MaterialTheme {
        Column {
            NotificationItem(
                notification = NotificationData(
                    id = 1,
                    type = NotificationType.LIKE,
                    userName = "Jane Smith",
                    message = "liked your post",
                    timeAgo = "5 minutes ago",
                    isRead = false
                ),
                onClick = {}
            )
            NotificationItem(
                notification = NotificationData(
                    id = 2,
                    type = NotificationType.COMMENT,
                    userName = "Mike Johnson",
                    message = "commented on your post",
                    timeAgo = "15 minutes ago",
                    isRead = true
                ),
                onClick = {}
            )
        }
    }
}

