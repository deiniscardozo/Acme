package com.example.acme.view.dashboard.adapter

import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.acme.databinding.ItemDashboardBinding
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets
import com.example.acme.view.WorkTicketActivity
import java.time.format.DateTimeFormatter

class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDashboardBinding.bind(view)

    @RequiresApi(Build.VERSION_CODES.O)
    fun render(ticketModel: Tickets, customerModel: Customers, context:Context) {

        val dateCreated = ticketModel.dateCreated
        val formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy")
        val formatterTime = DateTimeFormatter.ofPattern("HH:mm a")
        val formatted = dateCreated?.format(formatter)
        val formattedTime = dateCreated?.format(formatterTime)

        binding.dateCreated.text = formatted
        binding.timeCreated.text = formattedTime
        binding.ticketNum.text = ticketModel.idTickets.toString()
        binding.work.text = ticketModel.work
        binding.address.text = customerModel.address
        binding.viewTicket.setOnClickListener { Util.intentActivity(context, WorkTicketActivity::class.java) }

    }
}