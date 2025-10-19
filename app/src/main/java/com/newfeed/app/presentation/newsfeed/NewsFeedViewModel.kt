package com.newfeed.app.presentation.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.model.User
import com.newfeed.app.domain.usecase.GetAllPostsUseCase
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase
import com.newfeed.app.domain.usecase.ToggleLikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<NewsFeedUiState>(NewsFeedUiState.Loading)
    val uiState: StateFlow<NewsFeedUiState> = _uiState.asStateFlow()
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()
    
    init {
        loadCurrentUser()
        loadPosts()
    }
    
    private fun loadCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }
    
    private fun loadPosts() {
        viewModelScope.launch {
            getAllPostsUseCase()
                .catch { e ->
                    _uiState.value = NewsFeedUiState.Error(e.message ?: "Unknown error")
                }
                .collect { posts ->
                    _uiState.value = NewsFeedUiState.Success(posts)
                }
        }
    }
    
    fun onLikeClick(post: Post) {
        viewModelScope.launch {
            val userId = _currentUser.value?.id ?: 1L
            toggleLikeUseCase(post.id, userId, post.isLiked)
        }
    }
}

sealed interface NewsFeedUiState {
    object Loading : NewsFeedUiState
    data class Success(val posts: List<Post>) : NewsFeedUiState
    data class Error(val message: String) : NewsFeedUiState
}



