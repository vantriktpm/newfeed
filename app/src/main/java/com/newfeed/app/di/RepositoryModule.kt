package com.newfeed.app.di

import com.newfeed.app.data.repository.CommentRepositoryImpl
import com.newfeed.app.data.repository.PostRepositoryImpl
import com.newfeed.app.data.repository.UserRepositoryImpl
import com.newfeed.app.domain.repository.CommentRepository
import com.newfeed.app.domain.repository.PostRepository
import com.newfeed.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository
    
    @Binds
    @Singleton
    abstract fun bindCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl
    ): CommentRepository
    
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}



