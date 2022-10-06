package com.example.acme.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.acme.model.DataBase
import com.example.acme.model.Util

class UsersRegisterActivityViewModel: ViewModel() {

    val _user = MutableLiveData<String>()
    var user: LiveData<String> = _user

    val _pass = MutableLiveData<String>()
    var pass: LiveData<String> = _pass

    val _context = MutableLiveData<Context>()
    var context: LiveData<Context> = _context

    private fun UsersRregister() {
        val conn = DataBase(context.value)
        val db: SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        //insert into users (id,user,password)
        values.put(Util.USER_NAME, user.value.toString())
        values.put(Util.USER_PASS, pass.value.toString())
        val idR = db.insert(Util.USER_NAME, Util.USER_PASS, values)
        Toast.makeText(context.value, "Registration Id: $idR", Toast.LENGTH_SHORT).show()
        db.close()
    }
}