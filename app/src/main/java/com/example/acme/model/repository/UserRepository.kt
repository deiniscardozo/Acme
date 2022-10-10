package com.example.acme.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.acme.model.DataBase
import com.example.acme.model.Util
import com.example.acme.model.entity.Users

class UserRepository {

    private lateinit var conn: DataBase
    private lateinit var cursor: Cursor
    private var user: String = ""
    private var password: String = ""

    fun UsersInsert(context:Context, user:String, pass:String) {
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        //insert into users (id,user,password)
        values.put(Util.USER_NAME, user)
        values.put(Util.USER_PASS, pass)

        val newRowId = db.insert(Util.USERS_TABLE, Util.USER_NAME, values)

        Toast.makeText(context, "Id Registro: $newRowId",Toast.LENGTH_SHORT).show()
        db.close()
    }

    private fun queryUser(contex: Context, userName: String): Users {
        conn = DataBase(contex)
        val db:SQLiteDatabase = conn.readableDatabase
        val param = arrayOf(userName)
        val columns = arrayOf(Util.USER_NAME, Util.USER_PASS)

        try {
            cursor = db.query(
                Util.USERS_TABLE,
                columns,
                Util.USER_NAME + "=?",
                param,
                null,
                null,
                null
            )
            cursor.moveToFirst()
            user = cursor.getString(0)
            password = cursor.getString(1)
            cursor.close()

        } catch (e:Exception) {
            Toast.makeText(contex, "Username does not exist", Toast.LENGTH_LONG).show()
        }

        return Users(user, password)
    }

    fun getUserName(contex: Context, userName: String):String? {
        return queryUser(contex, userName).user
    }

    fun getUserPass(contex: Context, userName: String):String? {
        return queryUser(contex, userName).password
    }
}