package com.syedabdullah.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.syedabdullah.roomdb.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY userId ASC")
    fun getUsers():LiveData<List<User>>

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()
}