package com.newfeed.app.`data`.local.dao

import androidx.collection.LongSparseArray
import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchLongSparseArray
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.SQLiteStatement
import com.newfeed.app.`data`.local.entity.PostEntity
import com.newfeed.app.`data`.local.entity.UserEntity
import com.newfeed.app.`data`.local.relation.PostWithUser
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PostDao_Impl(
  __db: RoomDatabase,
) : PostDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPostEntity: EntityInsertAdapter<PostEntity>

  private val __deleteAdapterOfPostEntity: EntityDeleteOrUpdateAdapter<PostEntity>

  private val __updateAdapterOfPostEntity: EntityDeleteOrUpdateAdapter<PostEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPostEntity = object : EntityInsertAdapter<PostEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `posts` (`id`,`userId`,`content`,`imageUrl`,`likesCount`,`commentsCount`,`sharesCount`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PostEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.userId)
        statement.bindText(3, entity.content)
        val _tmpImageUrl: String? = entity.imageUrl
        if (_tmpImageUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpImageUrl)
        }
        statement.bindLong(5, entity.likesCount.toLong())
        statement.bindLong(6, entity.commentsCount.toLong())
        statement.bindLong(7, entity.sharesCount.toLong())
        statement.bindLong(8, entity.createdAt)
      }
    }
    this.__deleteAdapterOfPostEntity = object : EntityDeleteOrUpdateAdapter<PostEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `posts` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PostEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfPostEntity = object : EntityDeleteOrUpdateAdapter<PostEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `posts` SET `id` = ?,`userId` = ?,`content` = ?,`imageUrl` = ?,`likesCount` = ?,`commentsCount` = ?,`sharesCount` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PostEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.userId)
        statement.bindText(3, entity.content)
        val _tmpImageUrl: String? = entity.imageUrl
        if (_tmpImageUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpImageUrl)
        }
        statement.bindLong(5, entity.likesCount.toLong())
        statement.bindLong(6, entity.commentsCount.toLong())
        statement.bindLong(7, entity.sharesCount.toLong())
        statement.bindLong(8, entity.createdAt)
        statement.bindLong(9, entity.id)
      }
    }
  }

  public override suspend fun insertPost(post: PostEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPostEntity.insertAndReturnId(_connection, post)
    _result
  }

  public override suspend fun deletePost(post: PostEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfPostEntity.handle(_connection, post)
  }

  public override suspend fun updatePost(post: PostEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPostEntity.handle(_connection, post)
  }

  public override fun getAllPostsWithUser(): Flow<List<PostWithUser>> {
    val _sql: String = "SELECT * FROM posts ORDER BY createdAt DESC"
    return createFlow(__db, true, arrayOf("users", "posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfLikesCount: Int = getColumnIndexOrThrow(_stmt, "likesCount")
        val _columnIndexOfCommentsCount: Int = getColumnIndexOrThrow(_stmt, "commentsCount")
        val _columnIndexOfSharesCount: Int = getColumnIndexOrThrow(_stmt, "sharesCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _collectionUser: LongSparseArray<UserEntity?> = LongSparseArray<UserEntity?>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfUserId)
          _collectionUser.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipusersAscomNewfeedAppDataLocalEntityUserEntity(_connection, _collectionUser)
        val _result: MutableList<PostWithUser> = mutableListOf()
        while (_stmt.step()) {
          val _item: PostWithUser
          val _tmpPost: PostEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpLikesCount: Int
          _tmpLikesCount = _stmt.getLong(_columnIndexOfLikesCount).toInt()
          val _tmpCommentsCount: Int
          _tmpCommentsCount = _stmt.getLong(_columnIndexOfCommentsCount).toInt()
          val _tmpSharesCount: Int
          _tmpSharesCount = _stmt.getLong(_columnIndexOfSharesCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _tmpPost = PostEntity(_tmpId,_tmpUserId,_tmpContent,_tmpImageUrl,_tmpLikesCount,_tmpCommentsCount,_tmpSharesCount,_tmpCreatedAt)
          val _tmpUser: UserEntity?
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfUserId)
          _tmpUser = _collectionUser.get(_tmpKey_1)
          if (_tmpUser == null) {
            error("Relationship item 'user' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'userId' and entityColumn named 'id'.")
          }
          _item = PostWithUser(_tmpPost,_tmpUser)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPostById(postId: Long): Flow<PostWithUser?> {
    val _sql: String = "SELECT * FROM posts WHERE id = ?"
    return createFlow(__db, true, arrayOf("users", "posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, postId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfLikesCount: Int = getColumnIndexOrThrow(_stmt, "likesCount")
        val _columnIndexOfCommentsCount: Int = getColumnIndexOrThrow(_stmt, "commentsCount")
        val _columnIndexOfSharesCount: Int = getColumnIndexOrThrow(_stmt, "sharesCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _collectionUser: LongSparseArray<UserEntity?> = LongSparseArray<UserEntity?>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfUserId)
          _collectionUser.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipusersAscomNewfeedAppDataLocalEntityUserEntity(_connection, _collectionUser)
        val _result: PostWithUser?
        if (_stmt.step()) {
          val _tmpPost: PostEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpLikesCount: Int
          _tmpLikesCount = _stmt.getLong(_columnIndexOfLikesCount).toInt()
          val _tmpCommentsCount: Int
          _tmpCommentsCount = _stmt.getLong(_columnIndexOfCommentsCount).toInt()
          val _tmpSharesCount: Int
          _tmpSharesCount = _stmt.getLong(_columnIndexOfSharesCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _tmpPost = PostEntity(_tmpId,_tmpUserId,_tmpContent,_tmpImageUrl,_tmpLikesCount,_tmpCommentsCount,_tmpSharesCount,_tmpCreatedAt)
          val _tmpUser: UserEntity?
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfUserId)
          _tmpUser = _collectionUser.get(_tmpKey_1)
          if (_tmpUser == null) {
            error("Relationship item 'user' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'userId' and entityColumn named 'id'.")
          }
          _result = PostWithUser(_tmpPost,_tmpUser)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getPostsByUser(userId: Long): Flow<List<PostWithUser>> {
    val _sql: String = "SELECT * FROM posts WHERE userId = ? ORDER BY createdAt DESC"
    return createFlow(__db, true, arrayOf("users", "posts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfLikesCount: Int = getColumnIndexOrThrow(_stmt, "likesCount")
        val _columnIndexOfCommentsCount: Int = getColumnIndexOrThrow(_stmt, "commentsCount")
        val _columnIndexOfSharesCount: Int = getColumnIndexOrThrow(_stmt, "sharesCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _collectionUser: LongSparseArray<UserEntity?> = LongSparseArray<UserEntity?>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfUserId)
          _collectionUser.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipusersAscomNewfeedAppDataLocalEntityUserEntity(_connection, _collectionUser)
        val _result: MutableList<PostWithUser> = mutableListOf()
        while (_stmt.step()) {
          val _item: PostWithUser
          val _tmpPost: PostEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpLikesCount: Int
          _tmpLikesCount = _stmt.getLong(_columnIndexOfLikesCount).toInt()
          val _tmpCommentsCount: Int
          _tmpCommentsCount = _stmt.getLong(_columnIndexOfCommentsCount).toInt()
          val _tmpSharesCount: Int
          _tmpSharesCount = _stmt.getLong(_columnIndexOfSharesCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _tmpPost = PostEntity(_tmpId,_tmpUserId,_tmpContent,_tmpImageUrl,_tmpLikesCount,_tmpCommentsCount,_tmpSharesCount,_tmpCreatedAt)
          val _tmpUser: UserEntity?
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfUserId)
          _tmpUser = _collectionUser.get(_tmpKey_1)
          if (_tmpUser == null) {
            error("Relationship item 'user' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'userId' and entityColumn named 'id'.")
          }
          _item = PostWithUser(_tmpPost,_tmpUser)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateLikesCount(postId: Long, count: Int) {
    val _sql: String = "UPDATE posts SET likesCount = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, count.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, postId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateCommentsCount(postId: Long, count: Int) {
    val _sql: String = "UPDATE posts SET commentsCount = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, count.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, postId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __fetchRelationshipusersAscomNewfeedAppDataLocalEntityUserEntity(_connection: SQLiteConnection, _map: LongSparseArray<UserEntity?>) {
    if (_map.isEmpty()) {
      return
    }
    if (_map.size() > 999) {
      recursiveFetchLongSparseArray(_map, false) { _tmpMap ->
        __fetchRelationshipusersAscomNewfeedAppDataLocalEntityUserEntity(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `id`,`name`,`profileImageUrl`,`coverImageUrl`,`bio`,`email`,`friendsCount`,`photosCount`,`createdAt` FROM `users` WHERE `id` IN (")
    val _inputSize: Int = _map.size()
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (i in 0 until _map.size()) {
      val _item: Long = _map.keyAt(i)
      _stmt.bindLong(_argIndex, _item)
      _argIndex++
    }
    try {
      val _itemKeyIndex: Int = getColumnIndex(_stmt, "id")
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfId: Int = 0
      val _columnIndexOfName: Int = 1
      val _columnIndexOfProfileImageUrl: Int = 2
      val _columnIndexOfCoverImageUrl: Int = 3
      val _columnIndexOfBio: Int = 4
      val _columnIndexOfEmail: Int = 5
      val _columnIndexOfFriendsCount: Int = 6
      val _columnIndexOfPhotosCount: Int = 7
      val _columnIndexOfCreatedAt: Int = 8
      while (_stmt.step()) {
        val _tmpKey: Long
        _tmpKey = _stmt.getLong(_itemKeyIndex)
        if (_map.containsKey(_tmpKey)) {
          val _item_1: UserEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpProfileImageUrl: String?
          if (_stmt.isNull(_columnIndexOfProfileImageUrl)) {
            _tmpProfileImageUrl = null
          } else {
            _tmpProfileImageUrl = _stmt.getText(_columnIndexOfProfileImageUrl)
          }
          val _tmpCoverImageUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverImageUrl)) {
            _tmpCoverImageUrl = null
          } else {
            _tmpCoverImageUrl = _stmt.getText(_columnIndexOfCoverImageUrl)
          }
          val _tmpBio: String?
          if (_stmt.isNull(_columnIndexOfBio)) {
            _tmpBio = null
          } else {
            _tmpBio = _stmt.getText(_columnIndexOfBio)
          }
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpFriendsCount: Int
          _tmpFriendsCount = _stmt.getLong(_columnIndexOfFriendsCount).toInt()
          val _tmpPhotosCount: Int
          _tmpPhotosCount = _stmt.getLong(_columnIndexOfPhotosCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item_1 = UserEntity(_tmpId,_tmpName,_tmpProfileImageUrl,_tmpCoverImageUrl,_tmpBio,_tmpEmail,_tmpFriendsCount,_tmpPhotosCount,_tmpCreatedAt)
          _map.put(_tmpKey, _item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
