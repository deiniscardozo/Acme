package com.example.acme.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.acme.R

object Util: AppCompatActivity() {

    //Constants user table fields
    const val USERS_TABLE = "Users"
    const val USER_ID = "id"
    const val USER_NAME = "user"
    const val USER_PASS = "password"
    const val CREATE_TABLE_USERS = "CREATE TABLE " +
            "" + USERS_TABLE + " (" + USER_ID + " INTEGER AUTOINCREMENT, " +
             USER_NAME + " TEXT PRIMARY KEY," + USER_PASS + " TEXT)"

    fun intentActivity(context: Context?, activity: Class<*>) {
        ContextCompat.startActivity(
            context!!,
            Intent(context, activity),
            Bundle()
        )

        finish()
    }

    fun Toast(message: String){
        val view = layoutInflater.inflate(R.layout.toast_ok, null)
        val text: TextView = view.findViewById(R.id.toastMessage)

        Toast(this).apply {
            setGravity(Gravity.CENTER, 0, 0)
            setView(view)
            text.text = message
        }.show()
    }
}