package com.example.acme.view

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.acme.databinding.ActivityMainBinding
import com.example.acme.model.DataBase
import com.example.acme.model.Util


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.acme.R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    fun onClick(view: View) {
        var miIntent: Intent? = null
        when (view.id) {
            binding.btnRegister.id -> miIntent =
                Intent(this, UsersRegisterActivity::class.java)
            binding.btnLogin.id -> miIntent =
                Intent(this, DashboardActivity::class.java)

        }
        miIntent?.let { startActivity(it) }
    }

    private fun consultarSql() {
        val conn = DataBase(this)
        val db: SQLiteDatabase = conn.readableDatabase
        val param = arrayOf(binding.userName.toString())
        try {
            //select name,password from users where name=?
            val cursor: Cursor = db.rawQuery(
                "SELECT " + Util.USER_NAME.toString() + "," + Util.USER_PASS.toString() +
                        " FROM " + Util.USERS_TABLE.toString() + " WHERE " + Util.USER_NAME.toString() + "=? ",
                param
            )
            cursor.moveToFirst()
            binding.userName.setText(cursor.getString(0))
            binding.userPass.setText(cursor.getString(1))
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "El documento no existe", Toast.LENGTH_LONG).show()
            clear()
        }
    }

    private fun clear() {
        binding.userName.setText("")
        binding.userPass.setText("")
    }

}