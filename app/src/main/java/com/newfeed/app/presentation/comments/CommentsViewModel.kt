package com.newfeed.app.presentation.comments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newfeed.app.domain.model.Comment
import com.newfeed.app.domain.model.Post
import com.newfeed.app.domain.usecase.CreateCommentUseCase
import com.newfeed.app.domain.usecase.GetCommentsUseCase
import com.newfeed.app.domain.usecase.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val createCommentUseCase: CreateCommentUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    
    private val postId: Long = savedStateHandle.get<Long>("postId") ?: 0L
    
    private val _uiState = MutableStateFlow<CommentsUiState>(CommentsUiState.Loading)
    val uiState: StateFlow<CommentsUiState> = _uiState.asStateFlow()
    
    private val _commentText = MutableStateFlow("")
    val commentText: StateFlow<String> = _commentText.asStateFlow()
    
    private val _isPosting = MutableStateFlow(false)
    val isPosting: StateFlow<Boolean> = _isPosting.asStateFlow()
    
    init {
        loadComments()
    }
    
    private fun loadComments() {
        viewModelScope.launch {
            getCommentsUseCase(postId)
                .catch { e ->
                    _uiState.value = CommentsUiState.Error(e.message ?: "Unknown error")
                }
                .collect { comments ->
                    _uiState.value = CommentsUiState.Success(comments)
                }
        }
    }
    
    fun onCommentTextChange(text: String) {
        _commentText.value = text
    }
    
    fun onPostComment() {
        if (_commentText.value.isBlank() || _isPosting.value) return
        
        viewModelScope.launch {
            _isPosting.value = true
            try {
                val userId = 1L // Current user ID
                createCommentUseCase(postId, userId, _commentText.value)
                _commentText.value = ""
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isPosting.value = false
            }
        }
    }
}

sealed interface CommentsUiState {
    object Loading : CommentsUiState
    data class Success(val comments: List<Comment>) : CommentsUiState
    data class Error(val message: String) : CommentsUiState
}



