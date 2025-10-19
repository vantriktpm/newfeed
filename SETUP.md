# Hướng dẫn Setup và Chạy Ứng Dụng Newfeed

## Yêu cầu hệ thống

- **Android Studio**: Electric Eel (2022.1.1) hoặc mới hơn
- **JDK**: Java 17 hoặc mới hơn
- **Android SDK**: API Level 24 (Android 7.0) trở lên
- **Gradle**: 8.2 (tự động download khi build)

## Các bước setup

### 1. Cài đặt Android Studio

1. Download Android Studio từ [developer.android.com](https://developer.android.com/studio)
2. Cài đặt và mở Android Studio
3. Cài đặt Android SDK thông qua SDK Manager:
   - Android SDK Platform 34
   - Android SDK Build-Tools
   - Android Emulator

### 2. Setup local.properties

1. Mở file `local.properties` trong thư mục root của project
2. Cập nhật đường dẫn đến Android SDK của bạn:

```properties
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

**Windows:**
- Thường ở: `C:\Users\YourUsername\AppData\Local\Android\Sdk`

**Mac:**
- Thường ở: `/Users/YourUsername/Library/Android/sdk`

**Linux:**
- Thường ở: `/home/YourUsername/Android/Sdk`

### 3. Mở Project trong Android Studio

1. Mở Android Studio
2. Chọn **File > Open**
3. Chọn thư mục project `Newfeed`
4. Chờ Android Studio sync Gradle (có thể mất vài phút lần đầu)

### 4. Sync Gradle

Sau khi mở project:
1. Đợi Gradle tự động sync
2. Nếu có lỗi, chọn **File > Sync Project with Gradle Files**
3. Nếu có dependencies chưa download, Gradle sẽ tự động download

### 5. Chạy Ứng Dụng

#### Sử dụng Emulator:

1. Mở **AVD Manager** (Android Virtual Device Manager)
2. Tạo một device mới nếu chưa có:
   - Click **Create Virtual Device**
   - Chọn device (ví dụ: Pixel 5)
   - Chọn system image (API 34 - Android 14)
   - Finish
3. Start emulator
4. Click nút **Run** (▶) trong Android Studio
5. Chọn emulator đang chạy

#### Sử dụng Device thật:

1. Bật **Developer Options** trên điện thoại:
   - Vào Settings > About Phone
   - Tap 7 lần vào Build Number
2. Bật **USB Debugging** trong Developer Options
3. Kết nối điện thoại với máy tính qua USB
4. Click nút **Run** (▶) trong Android Studio
5. Chọn device của bạn

## Build APK

### Debug APK:

```bash
# Windows
gradlew.bat assembleDebug

# Mac/Linux
./gradlew assembleDebug
```

APK sẽ nằm ở: `app/build/outputs/apk/debug/app-debug.apk`

### Release APK:

```bash
# Windows
gradlew.bat assembleRelease

# Mac/Linux
./gradlew assembleRelease
```

## Troubleshooting

### Lỗi Gradle Sync

**Lỗi:** "SDK location not found"
- **Giải pháp:** Kiểm tra file `local.properties` và đảm bảo đường dẫn SDK đúng

**Lỗi:** "Kotlin version mismatch"
- **Giải pháp:** Clean project và rebuild:
  ```bash
  gradlew.bat clean build
  ```

### Lỗi Build

**Lỗi:** "Execution failed for task ':app:compileDebugKotlin'"
- **Giải pháp:** 
  1. Invalidate Caches: **File > Invalidate Caches / Restart**
  2. Restart Android Studio
  3. Sync lại Gradle

**Lỗi:** "Java heap space"
- **Giải pháp:** Tăng heap size trong `gradle.properties`:
  ```properties
  org.gradle.jvmargs=-Xmx4096m -Dfile.encoding=UTF-8
  ```

### Lỗi Runtime

**Lỗi:** App crash khi khởi động
- **Giải pháp:** 
  1. Check Logcat trong Android Studio
  2. Xác minh database initialization đang chạy
  3. Clean và rebuild project

**Lỗi:** Database không có data
- **Giải pháp:** 
  1. Uninstall app khỏi device/emulator
  2. Reinstall để trigger database initialization

## Cấu trúc Database

Ứng dụng sử dụng Room SQLite với các tables:
- **users**: Thông tin người dùng
- **posts**: Bài viết
- **comments**: Bình luận
- **likes**: Lượt thích

Database tự động được tạo và populate với sample data khi app khởi động lần đầu.

## Features có sẵn

✅ News Feed với posts
✅ Like/Unlike posts
✅ Comment trên posts
✅ Create new post
✅ Profile page
✅ Navigation giữa các screens
✅ Material 3 UI giống Facebook
✅ SQLite local database
✅ Clean Architecture

## Tính năng có thể mở rộng

- 📸 Upload ảnh cho posts
- 🔐 Authentication system
- 👥 Friends management
- 📱 Notifications
- 🔍 Search functionality
- 💬 Real-time chat
- 📊 Analytics

## Support

Nếu gặp vấn đề:
1. Check file README.md
2. Xem logs trong Logcat
3. Clean và rebuild project
4. Update Android Studio lên phiên bản mới nhất

## Resources

- [Android Developers](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)

