# Newfeed - Social Media App

Newfeed là một ứng dụng mạng xã hội giống Facebook được xây dựng bằng Android Native với Kotlin.

## 🏗️ Kiến trúc

Ứng dụng được xây dựng theo mô hình **Clean Architecture** với 3 layer chính:

### 1. Data Layer
- **Entities**: UserEntity, PostEntity, CommentEntity, LikeEntity
- **DAOs**: Truy cập dữ liệu với Room Database
- **Repository Implementations**: Implement các interface từ Domain layer
- **Mappers**: Chuyển đổi giữa Entity và Domain Model

### 2. Domain Layer
- **Models**: User, Post, Comment
- **Repositories**: Interface định nghĩa các operations
- **Use Cases**: Business logic (GetAllPostsUseCase, CreatePostUseCase, ToggleLikeUseCase, etc.)

### 3. Presentation Layer
- **UI**: Jetpack Compose
- **ViewModels**: Quản lý UI state và business logic
- **Screens**: NewsFeedScreen, CreatePostScreen, ProfileScreen, CommentsScreen
- **Components**: Reusable UI components

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: Clean Architecture + MVVM
- **Database**: Room SQLite
- **Dependency Injection**: Hilt
- **Navigation**: Compose Navigation
- **Image Loading**: Coil
- **Asynchronous**: Coroutines + Flow

## 📱 Tính năng

### News Feed
- Hiển thị danh sách bài viết
- Like/Unlike bài viết
- Comment vào bài viết
- Share bài viết
- Tạo bài viết mới

### Profile
- Xem thông tin cá nhân
- Hiển thị số lượng bạn bè, ảnh
- Xem tất cả bài viết của user
- Edit profile

### Create Post
- Tạo bài viết mới với text
- Thêm ảnh/video (UI ready)

### Comments
- Xem tất cả comment của bài viết
- Thêm comment mới
- Real-time update với Flow

## 🚀 Cách chạy

1. Clone repository
2. Mở project bằng Android Studio
3. Sync Gradle
4. Chạy app trên emulator hoặc thiết bị thật

## 📦 Dependencies

```kotlin
// Core
- androidx.core:core-ktx:1.12.0
- androidx.lifecycle:lifecycle-runtime-ktx:2.6.2

// Compose
- androidx.compose:compose-bom:2023.10.01
- androidx.compose.material3:material3
- androidx.navigation:navigation-compose:2.7.5

// Room Database
- androidx.room:room-runtime:2.6.1
- androidx.room:room-ktx:2.6.1

// Hilt DI
- com.google.dagger:hilt-android:2.48
- androidx.hilt:hilt-navigation-compose:1.1.0

// Coil (Image Loading)
- io.coil-kt:coil-compose:2.5.0
```

## 🎨 UI Design

Ứng dụng được thiết kế theo phong cách Facebook với:
- **Màu chính**: Facebook Blue (#1877F2)
- **Material 3 Design**
- **Bottom Navigation Bar**
- **Card-based layout**
- **Responsive UI**

## 📝 Database Schema

### Users
- id, name, email, profileImageUrl, coverImageUrl, bio, friendsCount, photosCount, createdAt

### Posts
- id, userId, content, imageUrl, likesCount, commentsCount, sharesCount, createdAt

### Comments
- id, postId, userId, content, likesCount, createdAt

### Likes
- id, postId, userId, createdAt

## 👥 Sample Data

Ứng dụng đi kèm với dữ liệu mẫu bao gồm:
- 3 users
- 6 bài viết mẫu với likes và comments

## 📄 License

This project is for educational purposes.

