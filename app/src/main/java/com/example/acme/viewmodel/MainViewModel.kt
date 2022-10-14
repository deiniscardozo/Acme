package com.example.acme.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acme.model.Util
import com.example.acme.model.repository.UserRepository
import com.example.acme.view.dashboard.DashboardActivity

class MainViewModel: ViewModel() {

    private val repository = UserRepository()

    private val _messageUser = MutableLiveData<String>()
    var messageUser:LiveData<String> = _messageUser

    private val _messagePass = MutableLiveData<String>()
    var messagePass:LiveData<String> = _messagePass

    private fun validateUser(context:Context, user: String): Boolean {
        val userName = repository.getUserName(context, user)

        return if (user.isEmpty()) {
            _messageUser.value = "Username can not be empty"
            false
        } else if (user != userName.toString()) {
            _messageUser.value = "Username does not exist"
            false
        } else {
            true
        }
    }

    private fun validatePass(context:Context, pass: String): Boolean {
        val userPass = repository.getUserPass(context, pass)

        return if (pass.isEmpty()) {
            _messagePass.value = "Password can not be empty"
            false
        } else if (pass != userPass.toString()) {
            _messagePass.value = "Password invalid"
            false
        } else {
            true
        }
    }

    fun login(context:Context, user: String, pass: String) {
        val userValid = validateUser(context, user)
        val passValid = validatePass(context, pass)

        if (userValid && passValid) {
            Util.intentActivity(context, DashboardActivity::class.java, "", "")
        }
    }
}