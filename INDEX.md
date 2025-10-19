# 📑 Newfeed Project - File Index

## 📖 Documentation Files

| File | Mô tả | Dành cho |
|------|-------|----------|
| **[README.md](README.md)** | Tổng quan project, features, tech stack | Tất cả mọi người |
| **[QUICKSTART.md](QUICKSTART.md)** | Hướng dẫn bắt đầu nhanh trong 5 phút | Người mới bắt đầu |
| **[SETUP.md](SETUP.md)** | Hướng dẫn setup chi tiết, troubleshooting | Developers |
| **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** | Chi tiết kỹ thuật, architecture, features | Technical audience |
| **[INDEX.md](INDEX.md)** | File này - Navigation guide | Tất cả |

---

## 🏗️ Project Structure

### Root Files
```
Newfeed/
├── README.md                    # Project overview
├── QUICKSTART.md               # Quick start guide
├── SETUP.md                    # Detailed setup instructions
├── PROJECT_SUMMARY.md          # Technical summary
├── INDEX.md                    # This file
├── .gitignore                  # Git ignore rules
├── settings.gradle.kts         # Gradle settings
├── build.gradle.kts            # Root build configuration
├── gradle.properties           # Gradle properties
├── local.properties            # Local SDK path (update this!)
├── gradlew                     # Gradle wrapper (Unix/Linux)
├── gradlew.bat                 # Gradle wrapper (Windows)
└── gradle/
    └── wrapper/
        ├── gradle-wrapper.jar
        └── gradle-wrapper.properties
```

### App Module
```
app/
├── build.gradle.kts            # App dependencies & config
├── proguard-rules.pro          # ProGuard rules
└── src/main/
    ├── AndroidManifest.xml     # App manifest
    ├── java/com/newfeed/app/
    │   ├── NewfeedApplication.kt          # Application class
    │   ├── data/                          # DATA LAYER
    │   │   ├── local/
    │   │   │   ├── NewfeedDatabase.kt
    │   │   │   ├── DatabaseInitializer.kt
    │   │   │   ├── entity/                # Database entities
    │   │   │   ├── dao/                   # Data access objects
    │   │   │   └── relation/              # Database relations
    │   │   ├── mapper/                    # Entity-Model mappers
    │   │   └── repository/                # Repository implementations
    │   ├── domain/                        # DOMAIN LAYER
    │   │   ├── model/                     # Business models
    │   │   ├── repository/                # Repository interfaces
    │   │   └── usecase/                   # Use cases
    │   ├── presentation/                  # PRESENTATION LAYER
    │   │   ├── MainActivity.kt
    │   │   ├── newsfeed/                  # News Feed feature
    │   │   ├── createpost/                # Create Post feature
    │   │   ├── profile/                   # Profile feature
    │   │   ├── comments/                  # Comments feature
    │   │   ├── components/                # Reusable components
    │   │   ├── navigation/                # Navigation
    │   │   └── theme/                     # UI theme
    │   └── di/                            # DEPENDENCY INJECTION
    │       ├── DatabaseModule.kt
    │       ├── RepositoryModule.kt
    │       └── AppModule.kt
    └── res/                               # Resources
        ├── drawable/
        ├── mipmap-*/
        └── values/
```

---

## 📱 Key Features Location

### 🏠 News Feed
- **Screen:** `presentation/newsfeed/NewsFeedScreen.kt`
- **ViewModel:** `presentation/newsfeed/NewsFeedViewModel.kt`
- **Use Case:** `domain/usecase/GetAllPostsUseCase.kt`
- **Repository:** `domain/repository/PostRepository.kt`

### ✏️ Create Post
- **Screen:** `presentation/createpost/CreatePostScreen.kt`
- **ViewModel:** `presentation/createpost/CreatePostViewModel.kt`
- **Use Case:** `domain/usecase/CreatePostUseCase.kt`

### 👤 Profile
- **Screen:** `presentation/profile/ProfileScreen.kt`
- **ViewModel:** `presentation/profile/ProfileViewModel.kt`
- **Use Case:** `domain/usecase/GetCurrentUserUseCase.kt`

### 💬 Comments
- **Screen:** `presentation/comments/CommentsScreen.kt`
- **ViewModel:** `presentation/comments/CommentsViewModel.kt`
- **Use Cases:** `domain/usecase/GetCommentsUseCase.kt`, `CreateCommentUseCase.kt`

---

## 🗄️ Database Files

### Entities (Tables)
- `data/local/entity/UserEntity.kt` - Users table
- `data/local/entity/PostEntity.kt` - Posts table
- `data/local/entity/CommentEntity.kt` - Comments table
- `data/local/entity/LikeEntity.kt` - Likes table

### DAOs (Database Access)
- `data/local/dao/UserDao.kt` - User CRUD operations
- `data/local/dao/PostDao.kt` - Post CRUD operations
- `data/local/dao/CommentDao.kt` - Comment CRUD operations
- `data/local/dao/LikeDao.kt` - Like CRUD operations

### Relations
- `data/local/relation/PostWithUser.kt` - Post joined with User
- `data/local/relation/CommentWithUser.kt` - Comment joined with User

### Database Core
- `data/local/NewfeedDatabase.kt` - Room database definition
- `data/local/DatabaseInitializer.kt` - Sample data initialization

---

## 🎨 UI Components

### Reusable Components
- `presentation/components/UserAvatar.kt` - User avatar component
- `presentation/components/PostCard.kt` - Post card component
- `presentation/components/CreatePostBar.kt` - Quick post bar

### Theme Files
- `presentation/theme/Color.kt` - Color definitions
- `presentation/theme/Type.kt` - Typography
- `presentation/theme/Theme.kt` - Material 3 theme

---

## 🔧 Configuration Files

### Gradle
- **Root:** `build.gradle.kts` - Plugin versions
- **App:** `app/build.gradle.kts` - Dependencies, SDK versions
- **Settings:** `settings.gradle.kts` - Module configuration
- **Properties:** `gradle.properties` - Gradle settings

### Android
- **Manifest:** `app/src/main/AndroidManifest.xml` - App configuration
- **ProGuard:** `app/proguard-rules.pro` - Code obfuscation rules

### Local
- **SDK Path:** `local.properties` - ⚠️ **UPDATE THIS FIRST!**

---

## 🎯 Where to Start

### For Beginners:
1. Read **[QUICKSTART.md](QUICKSTART.md)**
2. Update `local.properties`
3. Open in Android Studio
4. Run the app
5. Explore the code

### For Developers:
1. Read **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)**
2. Understand Clean Architecture structure
3. Check `data/`, `domain/`, `presentation/` layers
4. Review Dependency Injection in `di/`
5. Start coding!

### For Architects:
1. Review **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)**
2. Analyze Clean Architecture implementation
3. Check separation of concerns
4. Review dependency flow
5. Evaluate scalability

---

## 🔍 Find Specific Code

### Want to modify UI?
➡️ Go to `presentation/` folder

### Want to add business logic?
➡️ Go to `domain/usecase/`

### Want to change database?
➡️ Go to `data/local/`

### Want to add new screen?
1. Create new package in `presentation/`
2. Add Screen + ViewModel
3. Register in `navigation/NewfeedNavHost.kt`

### Want to add new feature?
1. Create Entity in `data/local/entity/`
2. Create DAO in `data/local/dao/`
3. Create Model in `domain/model/`
4. Create Repository interface in `domain/repository/`
5. Implement Repository in `data/repository/`
6. Create Use Cases in `domain/usecase/`
7. Create UI in `presentation/`

---

## 📚 Learning Path

### Level 1: Beginner
- [ ] Read QUICKSTART.md
- [ ] Run the app
- [ ] Explore UI
- [ ] Modify a string in `res/values/strings.xml`
- [ ] Change a color in `presentation/theme/Color.kt`

### Level 2: Intermediate
- [ ] Understand Clean Architecture
- [ ] Read through ViewModels
- [ ] Understand Flow usage
- [ ] Modify a Use Case
- [ ] Add a new UI component

### Level 3: Advanced
- [ ] Add a new feature end-to-end
- [ ] Modify database schema
- [ ] Add new navigation route
- [ ] Implement new business logic
- [ ] Optimize performance

---

## 🆘 Need Help?

### Common Issues:
- **Build error** → Check [SETUP.md](SETUP.md) Troubleshooting section
- **No data** → Reinstall app
- **Crash** → Check Logcat in Android Studio
- **SDK error** → Update `local.properties`

### Resources:
- [Android Developers](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Hilt DI](https://developer.android.com/training/dependency-injection/hilt-android)

---

## 📊 Project Statistics

- **Total Files:** 60+ files
- **Documentation:** 5 MD files
- **Screens:** 4 main screens
- **Use Cases:** 7 use cases
- **Database Tables:** 4 tables
- **Components:** 3+ reusable components
- **Lines of Code:** 3000+ lines

---

## ✅ Checklist Before Starting

- [ ] Android Studio installed
- [ ] JDK 17+ installed
- [ ] Android SDK downloaded
- [ ] `local.properties` updated
- [ ] Read QUICKSTART.md
- [ ] Gradle sync successful
- [ ] App runs without errors

---

## 🎉 You're Ready!

Choose your path:
- 🚀 **Quick Start** → [QUICKSTART.md](QUICKSTART.md)
- 🔧 **Detailed Setup** → [SETUP.md](SETUP.md)
- 📚 **Learn Architecture** → [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)
- 📖 **Overview** → [README.md](README.md)

**Happy Coding! 💻**

