package com.newfeed.app.presentation.createpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newfeed.app.domain.usecase.CreatePostUseCase
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CreatePostUiState())
    val uiState: StateFlow<CreatePostUiState> = _uiState.asStateFlow()
    
    private var currentUserId: Long? = null
    
    init {
        loadCurrentUser()
    }
    
    private fun loadCurrentUser() {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            if (user != null) {
                currentUserId = user.id
                _uiState.value = _uiState.value.copy(
                    userName = user.name,
                    userProfileImageUrl = user.profileImageUrl
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    userName = "User",
                    error = "No user found. Please create a user first."
                )
            }
        }
    }
    
    fun onContentChange(content: String) {
        _uiState.value = _uiState.value.copy(content = content, error = null)
    }
    
    fun onPost(onSuccess: () -> Unit) {
        if (_uiState.value.content.isBlank()) return
        
        val userId = currentUserId
        if (userId == null) {
            _uiState.value = _uiState.value.copy(
                error = "No user found. Cannot create post."
            )
            return
        }
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isPosting = true, error = null)
            try {
                createPostUseCase(userId, _uiState.value.content)
                _uiState.value = _uiState.value.copy(
                    isPosting = false,
                    content = "" // Clear content after successful post
                )
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isPosting = false,
                    error = e.message ?: "Failed to create post"
                )
            }
        }
    }
}

data class CreatePostUiState(
    val userName: String = "",
    val userProfileImageUrl: String? = null,
    val content: String = "",
    val isPosting: Boolean = false,
    val error: String? = null
)



