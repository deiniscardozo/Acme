package com.example.acme.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.acme.model.repository.UserRepository

class UsersRegisterViewModel: ViewModel() {

    private val repository = UserRepository()

    fun UsersRegister(context: Context, user: String, pass: String) {

        repository.UsersInsert(context, user, pass)
        Toast.makeText(context, "User $user created successfully",
            Toast.LENGTH_SHORT).show()
    }
}