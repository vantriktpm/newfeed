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
import com.newfeed.app.`data`.local.entity.CommentEntity
import com.newfeed.app.`data`.local.entity.UserEntity
import com.newfeed.app.`data`.local.relation.CommentWithUser
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
public class CommentDao_Impl(
  __db: RoomDatabase,
) : CommentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCommentEntity: EntityInsertAdapter<CommentEntity>

  private val __deleteAdapterOfCommentEntity: EntityDeleteOrUpdateAdapter<CommentEntity>

  private val __updateAdapterOfCommentEntity: EntityDeleteOrUpdateAdapter<CommentEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCommentEntity = object : EntityInsertAdapter<CommentEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `comments` (`id`,`postId`,`userId`,`content`,`likesCount`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CommentEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.postId)
        statement.bindLong(3, entity.userId)
        statement.bindText(4, entity.content)
        statement.bindLong(5, entity.likesCount.toLong())
        statement.bindLong(6, entity.createdAt)
      }
    }
    this.__deleteAdapterOfCommentEntity = object : EntityDeleteOrUpdateAdapter<CommentEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `comments` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CommentEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfCommentEntity = object : EntityDeleteOrUpdateAdapter<CommentEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `comments` SET `id` = ?,`postId` = ?,`userId` = ?,`content` = ?,`likesCount` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CommentEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.postId)
        statement.bindLong(3, entity.userId)
        statement.bindText(4, entity.content)
        statement.bindLong(5, entity.likesCount.toLong())
        statement.bindLong(6, entity.createdAt)
        statement.bindLong(7, entity.id)
      }
    }
  }

  public override suspend fun insertComment(comment: CommentEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfCommentEntity.insertAndReturnId(_connection, comment)
    _result
  }

  public override suspend fun deleteComment(comment: CommentEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfCommentEntity.handle(_connection, comment)
  }

  public override suspend fun updateComment(comment: CommentEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfCommentEntity.handle(_connection, comment)
  }

  public override fun getCommentsByPost(postId: Long): Flow<List<CommentWithUser>> {
    val _sql: String = "SELECT * FROM comments WHERE postId = ? ORDER BY createdAt ASC"
    return createFlow(__db, true, arrayOf("users", "comments")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, postId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPostId: Int = getColumnIndexOrThrow(_stmt, "postId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfLikesCount: Int = getColumnIndexOrThrow(_stmt, "likesCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _collectionUser: LongSparseArray<UserEntity?> = LongSparseArray<UserEntity?>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfUserId)
          _collectionUser.put(_tmpKey, null)
        }
        _stmt.reset()
        __fetchRelationshipusersAscomNewfeedAppDataLocalEntityUserEntity(_connection, _collectionUser)
        val _result: MutableList<CommentWithUser> = mutableListOf()
        while (_stmt.step()) {
          val _item: CommentWithUser
          val _tmpComment: CommentEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpPostId: Long
          _tmpPostId = _stmt.getLong(_columnIndexOfPostId)
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpLikesCount: Int
          _tmpLikesCount = _stmt.getLong(_columnIndexOfLikesCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _tmpComment = CommentEntity(_tmpId,_tmpPostId,_tmpUserId,_tmpContent,_tmpLikesCount,_tmpCreatedAt)
          val _tmpUser: UserEntity?
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfUserId)
          _tmpUser = _collectionUser.get(_tmpKey_1)
          if (_tmpUser == null) {
            error("Relationship item 'user' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'userId' and entityColumn named 'id'.")
          }
          _item = CommentWithUser(_tmpComment,_tmpUser)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCommentsCount(postId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM comments WHERE postId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, postId)
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
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
