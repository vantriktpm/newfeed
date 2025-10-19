package com.newfeed.app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.model.User
import com.newfeed.app.presentation.theme.FacebookBlue
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PostCard(
    post: Post,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Header with user info
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UserAvatar(
                    imageUrl = post.user.profileImageUrl,
                    size = 40.dp
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = post.user.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = formatTimestamp(post.createdAt),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                IconButton(onClick = { /* More options */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "More options",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            // Post content
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            // Post image
            if (post.imageUrl != null) {
                Spacer(modifier = Modifier.height(12.dp))
                AsyncImage(
                    model = post.imageUrl,
                    contentDescription = "Post image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    contentScale = ContentScale.Crop
                )
            }
            
            // Like, comment, share counts
            if (post.likesCount > 0 || post.commentsCount > 0 || post.sharesCount > 0) {
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (post.likesCount > 0) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                contentDescription = null,
                                tint = FacebookBlue,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${post.likesCount}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    
                    Row {
                        if (post.commentsCount > 0) {
                            Text(
                                text = "${post.commentsCount} comments",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (post.commentsCount > 0 && post.sharesCount > 0) {
                            Text(
                                text = " • ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (post.sharesCount > 0) {
                            Text(
                                text = "${post.sharesCount} shares",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(4.dp))
            
            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    icon = if (post.isLiked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp,
                    text = "Like",
                    onClick = onLikeClick,
                    tint = if (post.isLiked) FacebookBlue else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                
                ActionButton(
                    icon = Icons.Default.ChatBubbleOutline,
                    text = "Comment",
                    onClick = onCommentClick,
                    modifier = Modifier.weight(1f)
                )
                
                ActionButton(
                    icon = Icons.Default.Share,
                    text = "Share",
                    onClick = onShareClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = tint
        )
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

@Preview(showBackground = true)
@Composable
private fun PostCardPreview() {
    MaterialTheme {
        PostCard(
            post = Post(
                id = 1,
                user = User(
                    id = 1,
                    name = "John Doe",
                    email = "john@example.com",
                    profileImageUrl = null
                ),
                content = "This is a sample post content. I'm testing the preview functionality!",
                imageUrl = null,
                likesCount = 42,
                commentsCount = 5,
                sharesCount = 2,
                isLiked = false,
                createdAt = System.currentTimeMillis() - 3600000 // 1 hour ago
            ),
            onLikeClick = {},
            onCommentClick = {},
            onShareClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PostCardLikedPreview() {
    MaterialTheme {
        PostCard(
            post = Post(
                id = 2,
                user = User(
                    id = 2,
                    name = "Jane Smith",
                    email = "jane@example.com",
                    profileImageUrl = null
                ),
                content = "Beautiful sunset today! 🌅",
                imageUrl = null,
                likesCount = 128,
                commentsCount = 15,
                sharesCount = 8,
                isLiked = true,
                createdAt = System.currentTimeMillis() - 7200000 // 2 hours ago
            ),
            onLikeClick = {},
            onCommentClick = {},
            onShareClick = {}
        )
    }
}
