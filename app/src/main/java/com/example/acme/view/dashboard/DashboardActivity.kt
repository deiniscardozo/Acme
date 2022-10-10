package com.example.acme.view.dashboard

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acme.databinding.ActivityDashboardBinding
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets
import com.example.acme.model.repository.TicketRepository
import com.example.acme.view.dashboard.adapter.DashboardAdapter
import com.example.acme.viewmodel.DashboardViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDashboardBinding
    private lateinit var viewModel:DashboardViewModel
    var listTickets: MutableList<Tickets> = mutableListOf()
    var listCustomer: MutableList<Customers> = mutableListOf()
    private val repositoryTicket = TicketRepository()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = this.let {
            ViewModelProvider(it)[DashboardViewModel::class.java]
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MMM, dd-yyyy")
        val formatted = current.format(formatter)

        binding.tvDate.text = formatted

        binding.appBarDashboard.toolbar.title = ""
        setSupportActionBar(binding.appBarDashboard.toolbar)

        binding.appBarDashboard.calendar.setOnClickListener {
            viewModel.calendar(this, this)
        }

        binding.appBarDashboard.add.setOnClickListener {
            viewModel.addTicket(this, current.toString(), supportFragmentManager)
        }

        binding.appBarDashboard.menu.setOnClickListener {
            viewModel.showPopup(this, binding.appBarDashboard.menu)
        }

        binding.reciclerTickets.apply {
            isVisible = true
            layoutManager = LinearLayoutManager(context)
            adapter = DashboardAdapter(listTickets, listCustomer, context)
        }

        DashboardAdapter(listTickets, listCustomer, this).setItems(
            viewModel.getTickets(this),
            viewModel.getCustomer(this)
        )

    }
}