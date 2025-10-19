# Newfeed - Facebook Clone Android App
## Project Summary

### 📱 Thông tin Project

**Tên ứng dụng:** Newfeed  
**Nền tảng:** Android (Native Kotlin)  
**Kiến trúc:** Clean Architecture  
**Database:** SQLite (Room)  
**UI Framework:** Jetpack Compose  
**Dependency Injection:** Hilt  

---

## 🏗️ Kiến trúc Clean Architecture

### 1️⃣ **Data Layer** (Tầng dữ liệu)
📁 `app/src/main/java/com/newfeed/app/data/`

#### Database (Room SQLite)
- **Entities:**
  - `UserEntity` - Thông tin người dùng
  - `PostEntity` - Bài viết
  - `CommentEntity` - Bình luận
  - `LikeEntity` - Lượt thích

- **DAOs (Data Access Objects):**
  - `UserDao` - CRUD operations cho User
  - `PostDao` - CRUD operations cho Post
  - `CommentDao` - CRUD operations cho Comment
  - `LikeDao` - CRUD operations cho Like

- **Relations:**
  - `PostWithUser` - Join Post với User
  - `CommentWithUser` - Join Comment với User

- **Database:**
  - `NewfeedDatabase` - Room database chính
  - `DatabaseInitializer` - Khởi tạo sample data

#### Mappers
- `UserMapper` - Entity ↔ Domain Model
- `PostMapper` - Entity ↔ Domain Model
- `CommentMapper` - Entity ↔ Domain Model

#### Repository Implementations
- `UserRepositoryImpl`
- `PostRepositoryImpl`
- `CommentRepositoryImpl`

---

### 2️⃣ **Domain Layer** (Tầng nghiệp vụ)
📁 `app/src/main/java/com/newfeed/app/domain/`

#### Models (Domain Models)
- `User` - Business model cho User
- `Post` - Business model cho Post
- `Comment` - Business model cho Comment

#### Repositories (Interfaces)
- `UserRepository`
- `PostRepository`
- `CommentRepository`

#### Use Cases (Business Logic)
- `GetAllPostsUseCase` - Lấy tất cả posts
- `CreatePostUseCase` - Tạo post mới
- `ToggleLikeUseCase` - Like/Unlike post
- `GetCommentsUseCase` - Lấy comments của post
- `CreateCommentUseCase` - Tạo comment mới
- `GetUserUseCase` - Lấy thông tin user
- `GetCurrentUserUseCase` - Lấy user hiện tại

---

### 3️⃣ **Presentation Layer** (Tầng giao diện)
📁 `app/src/main/java/com/newfeed/app/presentation/`

#### Screens & ViewModels

**🏠 News Feed:**
- `NewsFeedScreen` - Màn hình feed chính
- `NewsFeedViewModel` - ViewModel cho news feed
- Features:
  - Hiển thị danh sách posts
  - Like/Unlike posts
  - Navigate to comments
  - Create new post

**✏️ Create Post:**
- `CreatePostScreen` - Màn hình tạo post
- `CreatePostViewModel` - ViewModel cho create post
- Features:
  - Text input
  - Photo/Video (UI ready)
  - Post button

**👤 Profile:**
- `ProfileScreen` - Màn hình profile
- `ProfileViewModel` - ViewModel cho profile
- Features:
  - User info display
  - Cover photo
  - Profile picture
  - Stats (Friends, Photos, Posts)
  - User's posts list
  - Edit profile button

**💬 Comments:**
- `CommentsScreen` - Màn hình comments
- `CommentsViewModel` - ViewModel cho comments
- Features:
  - Comment list
  - Add new comment
  - Real-time updates với Flow

#### Components (Reusable UI)
- `UserAvatar` - Avatar component
- `PostCard` - Card hiển thị post
- `CreatePostBar` - Bar tạo post nhanh

#### Theme
- `Color.kt` - Màu sắc (Facebook Blue, etc.)
- `Type.kt` - Typography
- `Theme.kt` - Material 3 theme

#### Navigation
- `Screen.kt` - Route definitions
- `NewfeedNavHost` - Navigation graph

---

## 🔧 Dependency Injection (Hilt)
📁 `app/src/main/java/com/newfeed/app/di/`

- **DatabaseModule** - Provide Room database & DAOs
- **RepositoryModule** - Bind repository implementations
- **AppModule** - Application-level dependencies

---

## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    profileImageUrl TEXT,
    coverImageUrl TEXT,
    bio TEXT,
    friendsCount INTEGER DEFAULT 0,
    photosCount INTEGER DEFAULT 0,
    createdAt INTEGER NOT NULL
)
```

### Posts Table
```sql
CREATE TABLE posts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    content TEXT NOT NULL,
    imageUrl TEXT,
    likesCount INTEGER DEFAULT 0,
    commentsCount INTEGER DEFAULT 0,
    sharesCount INTEGER DEFAULT 0,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
```

### Comments Table
```sql
CREATE TABLE comments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    postId INTEGER NOT NULL,
    userId INTEGER NOT NULL,
    content TEXT NOT NULL,
    likesCount INTEGER DEFAULT 0,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY (postId) REFERENCES posts(id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
```

### Likes Table
```sql
CREATE TABLE likes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    postId INTEGER NOT NULL,
    userId INTEGER NOT NULL,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY (postId) REFERENCES posts(id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
```

---

## 📦 Dependencies

### Core Android
```kotlin
androidx.core:core-ktx:1.12.0
androidx.lifecycle:lifecycle-runtime-ktx:2.6.2
androidx.activity:activity-compose:1.8.1
```

### Jetpack Compose
```kotlin
androidx.compose:compose-bom:2023.10.01
androidx.compose.ui:ui
androidx.compose.material3:material3
androidx.compose.material:material-icons-extended
androidx.navigation:navigation-compose:2.7.5
```

### Room Database
```kotlin
androidx.room:room-runtime:2.6.1
androidx.room:room-ktx:2.6.1
androidx.room:room-compiler:2.6.1 (KSP)
```

### Hilt (Dependency Injection)
```kotlin
com.google.dagger:hilt-android:2.48
com.google.dagger:hilt-android-compiler:2.48 (KSP)
androidx.hilt:hilt-navigation-compose:1.1.0
```

### Image Loading
```kotlin
io.coil-kt:coil-compose:2.5.0
```

### Coroutines
```kotlin
org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3
```

---

## ✨ Features Implemented

### ✅ Core Features
- [x] News Feed với danh sách posts
- [x] Like/Unlike posts
- [x] Comment trên posts
- [x] Create new post
- [x] Profile page
- [x] Bottom navigation
- [x] Material 3 UI giống Facebook
- [x] SQLite local database
- [x] Clean Architecture
- [x] Hilt Dependency Injection
- [x] Jetpack Compose UI
- [x] Navigation Component
- [x] Sample data initialization

### 🎨 UI Components
- [x] PostCard component
- [x] UserAvatar component
- [x] CreatePostBar component
- [x] Comments list
- [x] Profile header
- [x] Bottom navigation bar

### 🔄 Data Flow
- [x] Repository pattern
- [x] Use cases
- [x] Flow for reactive data
- [x] Room database
- [x] Entity-Model mapping

---

## 🚀 Sample Data

### 3 Users:
1. **John Doe** - Software Developer
2. **Jane Smith** - Designer
3. **Mike Johnson** - Photographer

### 6 Sample Posts:
- Various posts với likes, comments, shares
- Time-based posts (1h, 2h, 3h, 6h, 12h, 1 day ago)

---

## 📱 Screens

### 1. News Feed Screen
- Top bar với search & messenger icons
- Create post bar
- List of posts với infinite scroll
- Bottom navigation

### 2. Create Post Screen
- User info header
- Text input
- Add photo/video button
- Post button

### 3. Profile Screen
- Cover photo
- Profile picture
- User info & stats
- Edit profile button
- User's posts list

### 4. Comments Screen
- Comment list
- Comment input với send button
- Timestamp display

---

## 🎯 Architecture Benefits

### Clean Architecture:
✅ **Separation of Concerns** - Mỗi layer có trách nhiệm riêng  
✅ **Testability** - Dễ dàng unit test từng layer  
✅ **Maintainability** - Code dễ maintain và mở rộng  
✅ **Scalability** - Dễ thêm features mới  
✅ **Independence** - UI, Database có thể thay đổi độc lập  

### Room SQLite:
✅ **Type Safety** - Compile-time query verification  
✅ **Reactive** - Flow support cho real-time updates  
✅ **Migration Support** - Dễ dàng upgrade database  
✅ **Performance** - Optimized queries  

### Jetpack Compose:
✅ **Declarative UI** - Less code, more readable  
✅ **State Management** - Built-in state handling  
✅ **Material 3** - Modern design system  
✅ **Performance** - Smart recomposition  

### Hilt DI:
✅ **Automatic Injection** - Less boilerplate  
✅ **Scoped Dependencies** - Proper lifecycle management  
✅ **Testing** - Easy to mock dependencies  

---

## 🔮 Future Enhancements

### Phase 2:
- [ ] Upload ảnh từ gallery
- [ ] Camera integration
- [ ] Edit/Delete posts
- [ ] Share functionality
- [ ] Like comments

### Phase 3:
- [ ] User authentication
- [ ] Friends system
- [ ] Friend requests
- [ ] Real-time notifications
- [ ] Search functionality

### Phase 4:
- [ ] Stories feature
- [ ] Chat/Messenger
- [ ] Video posts
- [ ] Reactions (Love, Haha, Wow, etc.)
- [ ] Dark mode

### Phase 5:
- [ ] Backend integration (REST API)
- [ ] Firebase sync
- [ ] Push notifications
- [ ] Analytics
- [ ] Crash reporting

---

## 📝 Code Quality

### Architecture Patterns:
✅ Clean Architecture  
✅ MVVM Pattern  
✅ Repository Pattern  
✅ Use Case Pattern  
✅ Dependency Injection  

### Best Practices:
✅ Single Responsibility Principle  
✅ Dependency Inversion  
✅ Reactive Programming với Flow  
✅ Immutable Data Models  
✅ Proper Error Handling  

---

## 📚 Learning Resources

### Documentation:
- [README.md](README.md) - Project overview
- [SETUP.md](SETUP.md) - Setup instructions
- [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) - This file

### External Resources:
- [Android Developers](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Room Database](https://developer.android.com/training/data-storage/room)

---

## 🎓 Key Takeaways

### What You Learned:
1. **Clean Architecture** implementation in Android
2. **Room Database** setup và usage
3. **Jetpack Compose** UI development
4. **Hilt** dependency injection
5. **Navigation Component** với Compose
6. **Flow** for reactive programming
7. **Material 3** design implementation
8. **Repository pattern** và Use Cases
9. **Entity-Model mapping**
10. **Modern Android development practices**

---

## ⚡ Quick Start

```bash
# Clone project
cd Newfeed

# Mở trong Android Studio
# File > Open > Chọn thư mục Newfeed

# Sync Gradle
# Đợi Gradle sync xong

# Run app
# Click nút Run (▶) hoặc Shift+F10
```

---

## 📊 Project Statistics

- **Total Files:** ~60+ files
- **Lines of Code:** ~3000+ lines
- **Screens:** 4 main screens
- **Components:** 3 reusable components
- **Use Cases:** 7 use cases
- **Database Tables:** 4 tables
- **Dependencies:** 15+ libraries

---

## 🎉 Congratulations!

Bạn đã có một ứng dụng Facebook Clone hoàn chỉnh với:
- ✅ Clean Architecture
- ✅ Modern Android Stack
- ✅ Production-ready code structure
- ✅ Scalable architecture
- ✅ Best practices implementation

**Happy Coding! 🚀**

