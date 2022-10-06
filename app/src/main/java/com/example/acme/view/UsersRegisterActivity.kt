package com.example.acme.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.example.acme.R
import com.example.acme.databinding.ActivityUsersRegisterBinding
import com.example.acme.model.Util
import com.example.acme.viewmodel.UsersRegisterActivityViewModel

class UsersRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersRegisterBinding
    private lateinit var viewModel: UsersRegisterActivityViewModel

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_register)
        binding = ActivityUsersRegisterBinding.inflate(layoutInflater)

        viewModel = this.let {
                ViewModelProvider(it)[UsersRegisterActivityViewModel::class.java]
            }

        viewModel._user.value = binding.userNameReg.text.toString()
        viewModel._pass.value = binding.userPassReg.text.toString()

    }

    fun onClick(view: View) {
        viewModel.UsersRegister(this)
        Util.intentActivity(this, MainActivity::class.java)
    }
}