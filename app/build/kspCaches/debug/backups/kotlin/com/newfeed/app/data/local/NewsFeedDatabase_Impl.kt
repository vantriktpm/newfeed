package com.newfeed.app.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.newfeed.app.`data`.local.dao.CommentDao
import com.newfeed.app.`data`.local.dao.CommentDao_Impl
import com.newfeed.app.`data`.local.dao.LikeDao
import com.newfeed.app.`data`.local.dao.LikeDao_Impl
import com.newfeed.app.`data`.local.dao.PostDao
import com.newfeed.app.`data`.local.dao.PostDao_Impl
import com.newfeed.app.`data`.local.dao.UserDao
import com.newfeed.app.`data`.local.dao.UserDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class NewsFeedDatabase_Impl : NewsFeedDatabase() {
  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }

  private val _postDao: Lazy<PostDao> = lazy {
    PostDao_Impl(this)
  }

  private val _commentDao: Lazy<CommentDao> = lazy {
    CommentDao_Impl(this)
  }

  private val _likeDao: Lazy<LikeDao> = lazy {
    LikeDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "a6015df267fb866ecd27622f25b950a9", "f88fdd6f665aa878fc17adfda269fd1c") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `profileImageUrl` TEXT, `coverImageUrl` TEXT, `bio` TEXT, `email` TEXT NOT NULL, `friendsCount` INTEGER NOT NULL, `photosCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `posts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `content` TEXT NOT NULL, `imageUrl` TEXT, `likesCount` INTEGER NOT NULL, `commentsCount` INTEGER NOT NULL, `sharesCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_posts_userId` ON `posts` (`userId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `comments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `postId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `content` TEXT NOT NULL, `likesCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`postId`) REFERENCES `posts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_comments_postId` ON `comments` (`postId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_comments_userId` ON `comments` (`userId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `likes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `postId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`postId`) REFERENCES `posts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`userId`) REFERENCES `users`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_likes_postId` ON `likes` (`postId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_likes_userId` ON `likes` (`userId`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a6015df267fb866ecd27622f25b950a9')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `users`")
        connection.execSQL("DROP TABLE IF EXISTS `posts`")
        connection.execSQL("DROP TABLE IF EXISTS `comments`")
        connection.execSQL("DROP TABLE IF EXISTS `likes`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("profileImageUrl", TableInfo.Column("profileImageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("coverImageUrl", TableInfo.Column("coverImageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("bio", TableInfo.Column("bio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("friendsCount", TableInfo.Column("friendsCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("photosCount", TableInfo.Column("photosCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(com.newfeed.app.data.local.entity.UserEntity).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        val _columnsPosts: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPosts.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("userId", TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("content", TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("imageUrl", TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("likesCount", TableInfo.Column("likesCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("commentsCount", TableInfo.Column("commentsCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("sharesCount", TableInfo.Column("sharesCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPosts.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPosts: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysPosts.add(TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", listOf("userId"), listOf("id")))
        val _indicesPosts: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPosts.add(TableInfo.Index("index_posts_userId", false, listOf("userId"), listOf("ASC")))
        val _infoPosts: TableInfo = TableInfo("posts", _columnsPosts, _foreignKeysPosts, _indicesPosts)
        val _existingPosts: TableInfo = read(connection, "posts")
        if (!_infoPosts.equals(_existingPosts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |posts(com.newfeed.app.data.local.entity.PostEntity).
              | Expected:
              |""".trimMargin() + _infoPosts + """
              |
              | Found:
              |""".trimMargin() + _existingPosts)
        }
        val _columnsComments: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsComments.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsComments.put("postId", TableInfo.Column("postId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsComments.put("userId", TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsComments.put("content", TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsComments.put("likesCount", TableInfo.Column("likesCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsComments.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysComments: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysComments.add(TableInfo.ForeignKey("posts", "CASCADE", "NO ACTION", listOf("postId"), listOf("id")))
        _foreignKeysComments.add(TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", listOf("userId"), listOf("id")))
        val _indicesComments: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesComments.add(TableInfo.Index("index_comments_postId", false, listOf("postId"), listOf("ASC")))
        _indicesComments.add(TableInfo.Index("index_comments_userId", false, listOf("userId"), listOf("ASC")))
        val _infoComments: TableInfo = TableInfo("comments", _columnsComments, _foreignKeysComments, _indicesComments)
        val _existingComments: TableInfo = read(connection, "comments")
        if (!_infoComments.equals(_existingComments)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |comments(com.newfeed.app.data.local.entity.CommentEntity).
              | Expected:
              |""".trimMargin() + _infoComments + """
              |
              | Found:
              |""".trimMargin() + _existingComments)
        }
        val _columnsLikes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLikes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLikes.put("postId", TableInfo.Column("postId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLikes.put("userId", TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLikes.put("createdAt", TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLikes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysLikes.add(TableInfo.ForeignKey("posts", "CASCADE", "NO ACTION", listOf("postId"), listOf("id")))
        _foreignKeysLikes.add(TableInfo.ForeignKey("users", "CASCADE", "NO ACTION", listOf("userId"), listOf("id")))
        val _indicesLikes: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesLikes.add(TableInfo.Index("index_likes_postId", false, listOf("postId"), listOf("ASC")))
        _indicesLikes.add(TableInfo.Index("index_likes_userId", false, listOf("userId"), listOf("ASC")))
        val _infoLikes: TableInfo = TableInfo("likes", _columnsLikes, _foreignKeysLikes, _indicesLikes)
        val _existingLikes: TableInfo = read(connection, "likes")
        if (!_infoLikes.equals(_existingLikes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |likes(com.newfeed.app.data.local.entity.LikeEntity).
              | Expected:
              |""".trimMargin() + _infoLikes + """
              |
              | Found:
              |""".trimMargin() + _existingLikes)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "users", "posts", "comments", "likes")
  }

  public override fun clearAllTables() {
    super.performClear(true, "users", "posts", "comments", "likes")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PostDao::class, PostDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CommentDao::class, CommentDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(LikeDao::class, LikeDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun userDao(): UserDao = _userDao.value

  public override fun postDao(): PostDao = _postDao.value

  public override fun commentDao(): CommentDao = _commentDao.value

  public override fun likeDao(): LikeDao = _likeDao.value
}
