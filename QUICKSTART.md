# 🚀 Quick Start Guide - Newfeed App

## Bắt đầu trong 5 phút!

### Bước 1: Cập nhật SDK Path ✅

Mở file `local.properties` và sửa đường dẫn Android SDK:

**Windows:**
```properties
sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
```

**Mac:**
```properties
sdk.dir=/Users/YourUsername/Library/Android/sdk
```

**Linux:**
```properties
sdk.dir=/home/YourUsername/Android/Sdk
```

### Bước 2: Mở Project trong Android Studio 📂

1. Mở **Android Studio**
2. Chọn **File > Open**
3. Chọn thư mục `Newfeed`
4. Đợi Gradle sync (2-5 phút)

### Bước 3: Chạy App ▶️

**Option 1: Emulator**
1. Click **AVD Manager** (Device Manager icon)
2. Create/Start một virtual device
3. Click nút **Run** (▶) hoặc nhấn `Shift+F10`

**Option 2: Device thật**
1. Bật USB Debugging trên điện thoại
2. Kết nối điện thoại với USB
3. Click nút **Run** (▶)

### Bước 4: Enjoy! 🎉

App sẽ tự động tạo database với sample data:
- 3 users
- 6 posts mẫu
- Sẵn sàng để test!

---

## 🎯 Features để thử

### News Feed
- ✅ Xem danh sách posts
- ✅ Like/Unlike posts (click biểu tượng 👍)
- ✅ Click "Comment" để xem và thêm comments
- ✅ Click "What's on your mind?" để tạo post mới

### Create Post
- ✅ Nhập nội dung
- ✅ Click "Post" để đăng
- ✅ Quay lại News Feed để xem post mới

### Profile
- ✅ Click icon Profile ở bottom bar
- ✅ Xem thông tin user
- ✅ Xem số Friends, Photos, Posts
- ✅ Scroll để xem posts của user

### Comments
- ✅ Click "Comment" trên bất kỳ post nào
- ✅ Nhập comment ở dưới
- ✅ Click send icon để gửi

---

## 🔧 Troubleshooting

### Lỗi "SDK location not found"
➡️ **Fix:** Kiểm tra file `local.properties` có đúng đường dẫn SDK

### Lỗi Gradle sync
➡️ **Fix:** Chọn **File > Invalidate Caches / Restart**

### App crash
➡️ **Fix:** Uninstall app khỏi device và reinstall

### Không có data
➡️ **Fix:** Reinstall app để trigger database initialization

---

## 📱 Requirements

- ✅ Android Studio Electric Eel trở lên
- ✅ JDK 17+
- ✅ Android SDK API 24+
- ✅ 4GB RAM minimum

---

## 📚 Đọc thêm

- **[README.md](README.md)** - Tổng quan project
- **[SETUP.md](SETUP.md)** - Hướng dẫn chi tiết
- **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** - Chi tiết kỹ thuật

---

## 💡 Tips

1. **Database location:** 
   - Emulator: `/data/data/com.newfeed.app/databases/newfeed_database`
   
2. **View database:** 
   - Android Studio > App Inspection > Database Inspector

3. **Check logs:**
   - Android Studio > Logcat > Filter: "Newfeed"

4. **Hot reload:**
   - Compose support hot reload trong Android Studio

---

## 🎨 UI Highlights

- 🎨 Material 3 Design
- 🔵 Facebook Blue theme
- 📱 Bottom Navigation
- 🃏 Card-based layout
- 🖼️ Placeholder cho images
- ⚡ Smooth animations

---

## 🚀 Ready to Code!

Project structure:
```
app/src/main/java/com/newfeed/app/
├── data/           # Database, DAOs, Repositories
├── domain/         # Use Cases, Models
├── presentation/   # UI, ViewModels
└── di/            # Dependency Injection
```

**Start coding from:**
- Add new features in `presentation/`
- Add business logic in `domain/usecase/`
- Modify database in `data/local/`

---

**Have fun building! 🎊**

