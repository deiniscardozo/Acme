package com.example.acme.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.example.acme.databinding.ActivityUsersRegisterBinding
import com.example.acme.model.Util
import com.example.acme.viewmodel.UsersRegisterViewModel

class UsersRegisterActivity: AppCompatActivity() {

    private lateinit var binding: ActivityUsersRegisterBinding
    private lateinit var viewModel: UsersRegisterViewModel

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = this.let {
                ViewModelProvider(it)[UsersRegisterViewModel::class.java]
            }

        binding.btnRegister.setOnClickListener {
            val user = binding.userNameReg.text.toString()
            val pass = binding.userPassReg.text.toString()

            viewModel.UsersRegister(applicationContext, user, pass)
            Util.intentActivity(this, MainActivity::class.java, "")
        }

    }
}