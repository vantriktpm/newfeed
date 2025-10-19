package com.newfeed.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.model.User
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase
import com.newfeed.app.domain.usecase.GetAllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAllPostsUseCase: GetAllPostsUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()
    
    init {
        loadProfile()
    }
    
    private fun loadProfile() {
        viewModelScope.launch {
            try {
                val user = getCurrentUserUseCase()
                if (user != null) {
                    getAllPostsUseCase()
                        .map { posts -> posts.filter { it.user.id == user.id } }
                        .collect { userPosts ->
                            _uiState.value = ProfileUiState.Success(user, userPosts)
                        }
                } else {
                    _uiState.value = ProfileUiState.Error("User not found")
                }
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed interface ProfileUiState {
    object Loading : ProfileUiState
    data class Success(val user: User, val posts: List<Post>) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}



