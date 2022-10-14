package com.example.acme.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.acme.R
import com.example.acme.databinding.ActivityWorkTicketBinding
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets
import com.example.acme.view.dashboard.DashboardActivity
import com.example.acme.view.dashboard.adapter.DashboardViewHolder
import com.example.acme.viewmodel.WorkTicketViewModel

class WorkTicketActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWorkTicketBinding
    private lateinit var viewModel:WorkTicketViewModel

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = this.let {
            ViewModelProvider(it)[WorkTicketViewModel::class.java]
        }

        val idTickets = intent.extras?.getString("param")
        val idCustomer = intent.extras?.getString("param1")

        binding.appBarDashboardWork.menu.setOnClickListener {
            viewModel.showPopup(this, binding.appBarDashboardWork.menu)
        }

        binding.appBarDashboardWork.back.setOnClickListener {
            Util.intentActivity(this, DashboardActivity::class.java, "", "")
        }

        val tab = binding.tabLayout
        tab.addTab(tab.newTab().setText("Overview"))
        tab.addTab(tab.newTab().setText("Work Details"))
        tab.addTab(tab.newTab().setText("Purchasing"))
        tab.addTab(tab.newTab().setText("Finishing Up"))
        tab.addTab(tab.newTab().setIcon(R.drawable.ic_baseline_photo_camera_24))

        binding.getDirections.setOnClickListener {
            Util.intentActivity(this, GetDirectionsActivity::class.java, "", "")
        }

        val customer = if(idTickets.isNullOrEmpty()) {
            viewModel.getCustomer(this).last()
        } else {
            viewModel.getCustomerView(this, idCustomer.toString())[0]
        }

        val ticket: Tickets = if(idTickets.isNullOrEmpty()) {
            viewModel.getTickets(this).last()
        } else {
            viewModel.getTicketView(this, idTickets)[0]
        }

        binding.customer.setText(customer.customer)
        binding.phone.setText(customer.phone)
        binding.dateSheduled.setText(ticket.dateCreated)
        binding.address.setText(customer.address)
        binding.note.setText(ticket.note)
        binding.distances.text = "Approx. 17 Minutes"
        binding.dclass.setText(ticket.deptClass)
        binding.serviceType.setText(ticket.serviceType)
        binding.reasonDes.setText(ticket.reasonCall)
        binding.ticketNum.text = ticket.idTickets.toString()

        binding.wyDelete.setOnClickListener {
            viewModel.deleteCustomer(this, customer.customer.toString())
            viewModel.deleteTickets(this, ticket.idTickets.toString())
            Util.intentActivity(this, WorkTicketActivity::class.java, "", "")
        }

        binding.wySave.setOnClickListener {
            val customers = Customers(
                customer.idCustomers,
                binding.customer.text.toString(),
                binding.phone.text.toString(),
                binding.address.text.toString()
            )

            viewModel.updateCustomer( this, customers, idCustomer.toString())

            val tickets = Tickets(
                ticket.idTickets,
                ticket.work,
                ticket.dateCreated,
                binding.dateSheduled.text.toString(),
                binding.note.text.toString(),
                binding.distance.text.toString(),
                binding.deptClass.text.toString(),
                binding.serviceType.text.toString(),
                binding.reasonDes.text.toString())

            viewModel.updateTickets(this, tickets, ticket.idTickets.toString())
        }
    }
}