package com.example.acme.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.acme.databinding.ActivityMainBinding
import com.example.acme.model.Util
import com.example.acme.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = this.let {
            ViewModelProvider(it)[MainViewModel::class.java]
        }

        binding.btnRegister.setOnClickListener {
            Util.intentActivity(this, UsersRegisterActivity::class.java)
        }

        binding.btnLogin.setOnClickListener {
            val user = binding.userName.text.toString()
            val pass = binding.userPass.text.toString()

            viewModel.login(this, user, pass)

            viewModel.messageUser.observe(this) { message ->
               binding.userName.error = message
            }

            viewModel.messagePass.observe(this) { message ->
                binding.userPass.error = message
            }
        }
    }

}