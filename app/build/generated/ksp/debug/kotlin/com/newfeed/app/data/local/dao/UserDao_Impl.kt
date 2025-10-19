package com.newfeed.app.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.newfeed.app.`data`.local.entity.UserEntity
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
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUserEntity: EntityInsertAdapter<UserEntity>

  private val __deleteAdapterOfUserEntity: EntityDeleteOrUpdateAdapter<UserEntity>

  private val __updateAdapterOfUserEntity: EntityDeleteOrUpdateAdapter<UserEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfUserEntity = object : EntityInsertAdapter<UserEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `users` (`id`,`name`,`profileImageUrl`,`coverImageUrl`,`bio`,`email`,`friendsCount`,`photosCount`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpProfileImageUrl: String? = entity.profileImageUrl
        if (_tmpProfileImageUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpProfileImageUrl)
        }
        val _tmpCoverImageUrl: String? = entity.coverImageUrl
        if (_tmpCoverImageUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpCoverImageUrl)
        }
        val _tmpBio: String? = entity.bio
        if (_tmpBio == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpBio)
        }
        statement.bindText(6, entity.email)
        statement.bindLong(7, entity.friendsCount.toLong())
        statement.bindLong(8, entity.photosCount.toLong())
        statement.bindLong(9, entity.createdAt)
      }
    }
    this.__deleteAdapterOfUserEntity = object : EntityDeleteOrUpdateAdapter<UserEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `users` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfUserEntity = object : EntityDeleteOrUpdateAdapter<UserEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `users` SET `id` = ?,`name` = ?,`profileImageUrl` = ?,`coverImageUrl` = ?,`bio` = ?,`email` = ?,`friendsCount` = ?,`photosCount` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: UserEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpProfileImageUrl: String? = entity.profileImageUrl
        if (_tmpProfileImageUrl == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpProfileImageUrl)
        }
        val _tmpCoverImageUrl: String? = entity.coverImageUrl
        if (_tmpCoverImageUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpCoverImageUrl)
        }
        val _tmpBio: String? = entity.bio
        if (_tmpBio == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpBio)
        }
        statement.bindText(6, entity.email)
        statement.bindLong(7, entity.friendsCount.toLong())
        statement.bindLong(8, entity.photosCount.toLong())
        statement.bindLong(9, entity.createdAt)
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insertUser(user: UserEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfUserEntity.insertAndReturnId(_connection, user)
    _result
  }

  public override suspend fun deleteUser(user: UserEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfUserEntity.handle(_connection, user)
  }

  public override suspend fun updateUser(user: UserEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfUserEntity.handle(_connection, user)
  }

  public override fun getAllUsers(): Flow<List<UserEntity>> {
    val _sql: String = "SELECT * FROM users ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfProfileImageUrl: Int = getColumnIndexOrThrow(_stmt, "profileImageUrl")
        val _columnIndexOfCoverImageUrl: Int = getColumnIndexOrThrow(_stmt, "coverImageUrl")
        val _columnIndexOfBio: Int = getColumnIndexOrThrow(_stmt, "bio")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFriendsCount: Int = getColumnIndexOrThrow(_stmt, "friendsCount")
        val _columnIndexOfPhotosCount: Int = getColumnIndexOrThrow(_stmt, "photosCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<UserEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: UserEntity
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
          _item = UserEntity(_tmpId,_tmpName,_tmpProfileImageUrl,_tmpCoverImageUrl,_tmpBio,_tmpEmail,_tmpFriendsCount,_tmpPhotosCount,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUserById(userId: Long): Flow<UserEntity?> {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return createFlow(__db, false, arrayOf("users")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfProfileImageUrl: Int = getColumnIndexOrThrow(_stmt, "profileImageUrl")
        val _columnIndexOfCoverImageUrl: Int = getColumnIndexOrThrow(_stmt, "coverImageUrl")
        val _columnIndexOfBio: Int = getColumnIndexOrThrow(_stmt, "bio")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFriendsCount: Int = getColumnIndexOrThrow(_stmt, "friendsCount")
        val _columnIndexOfPhotosCount: Int = getColumnIndexOrThrow(_stmt, "photosCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: UserEntity?
        if (_stmt.step()) {
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
          _result = UserEntity(_tmpId,_tmpName,_tmpProfileImageUrl,_tmpCoverImageUrl,_tmpBio,_tmpEmail,_tmpFriendsCount,_tmpPhotosCount,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserByIdSync(userId: Long): UserEntity? {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfProfileImageUrl: Int = getColumnIndexOrThrow(_stmt, "profileImageUrl")
        val _columnIndexOfCoverImageUrl: Int = getColumnIndexOrThrow(_stmt, "coverImageUrl")
        val _columnIndexOfBio: Int = getColumnIndexOrThrow(_stmt, "bio")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfFriendsCount: Int = getColumnIndexOrThrow(_stmt, "friendsCount")
        val _columnIndexOfPhotosCount: Int = getColumnIndexOrThrow(_stmt, "photosCount")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: UserEntity?
        if (_stmt.step()) {
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
          _result = UserEntity(_tmpId,_tmpName,_tmpProfileImageUrl,_tmpCoverImageUrl,_tmpBio,_tmpEmail,_tmpFriendsCount,_tmpPhotosCount,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getUserCount(): Int {
    val _sql: String = "SELECT COUNT(*) FROM users"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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

  public override suspend fun deleteAllUsers() {
    val _sql: String = "DELETE FROM users"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
