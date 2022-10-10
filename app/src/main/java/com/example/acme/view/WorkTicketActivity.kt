package com.example.acme.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.acme.R
import com.example.acme.databinding.ActivityWorkTicketBinding
import com.example.acme.model.Util
import com.example.acme.view.dashboard.DashboardActivity
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

        binding.appBarDashboardWork.menu.setOnClickListener {
            viewModel.showPopup(this, binding.appBarDashboardWork.menu)
        }

        binding.appBarDashboardWork.back.setOnClickListener {
            Util.intentActivity(this, DashboardActivity::class.java)
        }

        val tab = binding.tabLayout
        tab.addTab(tab.newTab().setText("Overview"))
        tab.addTab(tab.newTab().setText("Work Details"))
        tab.addTab(tab.newTab().setText("Purchasing"))
        tab.addTab(tab.newTab().setText("Finishing Up"))
        tab.addTab(tab.newTab().setIcon(R.drawable.ic_baseline_photo_camera_24))

        binding.getDirections.setOnClickListener {

            Util.intentActivity(this, GetDirectionsActivity::class.java)
        }

        val customer = viewModel.getCustomer(this)[0]
        val ticket = viewModel.getTickets(this)[0]

        binding.customer.text = customer.customer
        binding.phone.text = customer.phone
        binding.dateSheduled.text = ticket.dateSheduled
        binding.address.text = customer.address
        binding.note.text = ticket.note
        binding.distances.text = "Approx. 17 Minutes"
        binding.dclass.text = ticket.deptClass
        binding.serviceType.text = ticket.serviceType
        binding.reasonDes.text = ticket.reasonCall
        binding.ticketNum.text = ticket.idTickets.toString()
    }
}