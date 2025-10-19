package com.newfeed.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.newfeed.app.presentation.comments.CommentsScreen
import com.newfeed.app.presentation.createpost.CreatePostScreen
import com.newfeed.app.presentation.menu.MenuScreen
import com.newfeed.app.presentation.newsfeed.NewsFeedScreen
import com.newfeed.app.presentation.notifications.NotificationsScreen
import com.newfeed.app.presentation.profile.ProfileScreen

@Composable
fun NewsfeedNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NewsFeed.route
    ) {
        composable(Screen.NewsFeed.route) {
            NewsFeedScreen(
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToCreatePost = {
                    navController.navigate(Screen.CreatePost.route)
                },
                onNavigateToComments = { postId ->
                    navController.navigate(Screen.Comments.createRoute(postId))
                },
                onNavigateToNotifications = {
                    navController.navigate(Screen.Notifications.route)
                },
                onNavigateToMenu = {
                    navController.navigate(Screen.Menu.route)
                }
            )
        }
        
        composable(Screen.CreatePost.route) {
            CreatePostScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToComments = { postId ->
                    navController.navigate(Screen.Comments.createRoute(postId))
                }
            )
        }
        
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.LongType
                }
            )
        ) {
            CommentsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Menu.route) {
            MenuScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}



