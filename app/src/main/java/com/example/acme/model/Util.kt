package com.example.acme.model

object Util {

    //Constants user table fields
    const val USERS_TABLE = "Users"
    const val USER_ID = "id"
    const val USER_NAME = "user"
    const val USER_PASS = "password"
    const val CREATE_TABLE_USERS = "CREATE TABLE " +
            "" + USERS_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             USER_NAME + " TEXT," + USER_PASS + " TEXT)"
}