package com.example.acme

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.acme.databinding.ActivityUsersRegisterBinding
import com.example.acme.model.DataBase
import com.example.acme.model.Util


class UsersRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersRegisterBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_register)
        binding = ActivityUsersRegisterBinding.inflate(layoutInflater)
    }

    private fun UsersRregister() {
        val conn = DataBase(this)
        val db:SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        //insert into users (id,user,password)
        values.put(Util.USER_NAME, binding.userNameReg.text.toString())
        values.put(Util.USER_PASS, binding.userPassReg.text.toString())
        val idR = db.insert(Util.USER_NAME, Util.USER_PASS, values)
        Toast.makeText(applicationContext, "Registration Id: $idR", Toast.LENGTH_SHORT).show()
        db.close()
    }
}