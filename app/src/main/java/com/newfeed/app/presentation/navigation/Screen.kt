package com.newfeed.app.presentation.navigation

sealed class Screen(val route: String) {
    object NewsFeed : Screen("news_feed")
    object CreatePost : Screen("create_post")
    object Profile : Screen("profile")
    object Notifications : Screen("notifications")
    object Menu : Screen("menu")
    object Comments : Screen("comments/{postId}") {
        fun createRoute(postId: Long) = "comments/$postId"
    }
}



