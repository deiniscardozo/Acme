package com.example.acme.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.acme.model.Util.CREATE_TABLE_CUSTOMERS
import com.example.acme.model.Util.CREATE_TABLE_TICKETS
import com.example.acme.model.Util.CREATE_TABLE_USERS
import com.example.acme.model.Util.CUSTOMERS_TABLE
import com.example.acme.model.Util.TICKETS_TABLE
import com.example.acme.model.Util.USERS_TABLE

class DataBase (context:Context?) : SQLiteOpenHelper(context, "Acme", null, 3) {

    override fun onCreate(db:SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_USERS)
        db.execSQL(CREATE_TABLE_TICKETS)
        db.execSQL(CREATE_TABLE_CUSTOMERS)
    }

    override fun onUpgrade(db:SQLiteDatabase, versionAntigua:Int, versionNueva:Int) {
        db.execSQL("DROP TABLE IF EXISTS $USERS_TABLE")
        db.execSQL("DROP TABLE IF EXISTS $TICKETS_TABLE")
        db.execSQL("DROP TABLE IF EXISTS $CUSTOMERS_TABLE")
        onCreate(db)
    }
}