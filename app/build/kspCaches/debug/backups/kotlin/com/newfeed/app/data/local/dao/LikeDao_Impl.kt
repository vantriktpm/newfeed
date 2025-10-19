package com.newfeed.app.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.newfeed.app.`data`.local.entity.LikeEntity
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
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class LikeDao_Impl(
  __db: RoomDatabase,
) : LikeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfLikeEntity: EntityInsertAdapter<LikeEntity>

  private val __deleteAdapterOfLikeEntity: EntityDeleteOrUpdateAdapter<LikeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfLikeEntity = object : EntityInsertAdapter<LikeEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `likes` (`id`,`postId`,`userId`,`createdAt`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LikeEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.postId)
        statement.bindLong(3, entity.userId)
        statement.bindLong(4, entity.createdAt)
      }
    }
    this.__deleteAdapterOfLikeEntity = object : EntityDeleteOrUpdateAdapter<LikeEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `likes` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: LikeEntity) {
        statement.bindLong(1, entity.id)
      }
    }
  }

  public override suspend fun insertLike(like: LikeEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfLikeEntity.insertAndReturnId(_connection, like)
    _result
  }

  public override suspend fun deleteLike(like: LikeEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfLikeEntity.handle(_connection, like)
  }

  public override fun getLikesByPost(postId: Long): Flow<List<LikeEntity>> {
    val _sql: String = "SELECT * FROM likes WHERE postId = ?"
    return createFlow(__db, false, arrayOf("likes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, postId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPostId: Int = getColumnIndexOrThrow(_stmt, "postId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<LikeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: LikeEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpPostId: Long
          _tmpPostId = _stmt.getLong(_columnIndexOfPostId)
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = LikeEntity(_tmpId,_tmpPostId,_tmpUserId,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLikesCount(postId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM likes WHERE postId = ?"
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

  public override suspend fun getLikeByUserAndPost(postId: Long, userId: Long): LikeEntity? {
    val _sql: String = "SELECT * FROM likes WHERE postId = ? AND userId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, postId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfPostId: Int = getColumnIndexOrThrow(_stmt, "postId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: LikeEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpPostId: Long
          _tmpPostId = _stmt.getLong(_columnIndexOfPostId)
          val _tmpUserId: Long
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = LikeEntity(_tmpId,_tmpPostId,_tmpUserId,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteLikeByUserAndPost(postId: Long, userId: Long) {
    val _sql: String = "DELETE FROM likes WHERE postId = ? AND userId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, postId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, userId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
