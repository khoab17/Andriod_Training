package com.syedabdullah.roomdb.repository

import androidx.lifecycle.LiveData
import com.syedabdullah.roomdb.dao.UserDao
import com.syedabdullah.roomdb.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllUser:LiveData<List<User>> = userDao.getUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}