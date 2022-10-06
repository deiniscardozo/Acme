package com.example.acme.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase (context:Context?) : SQLiteOpenHelper(context, "Acme", null, 1) {

    override fun onCreate(db:SQLiteDatabase) {
        db.execSQL(Util.CREATE_TABLE_USERS)
    }

    override fun onUpgrade(db:SQLiteDatabase, versionAntigua:Int, versionNueva:Int) {
          onCreate(db)
    }
}