package com.example.dogsitter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    //Update existing one or insert a new one
    @Upsert
    suspend fun upsertUser(user: User)

    //Delete user
    @Delete
    suspend fun deleteUser(user: User)

    //Get user by first name ascending
    @Query("SELECT * FROM user ORDER BY firstName ASC")
    fun getUsersByFirstName(): Flow<List<User>>

    //Get user by last name ascending
    @Query("SELECT * FROM user ORDER BY lastName ASC")
    fun getUsersByLastName(): Flow<List<User>>

    //Get user by phone number ascending
    @Query("SELECT * FROM user ORDER BY phoneNumber ASC")
    fun getUsersByPhoneNumber(): Flow<List<User>>

}