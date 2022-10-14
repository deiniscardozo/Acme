package com.example.acme.view.dashboard.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.acme.databinding.ItemDashboardBinding
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets
import com.example.acme.view.WorkTicketActivity

class DashboardViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemDashboardBinding.bind(view)

    fun render(ticketModel: Tickets, customerModel: Customers, context:Context) {

        val dateCreated = ticketModel.dateCreated
        val month = dateCreated?.subSequence(5,6).toString()
        val year = dateCreated?.subSequence(0,4).toString()
        val day = dateCreated?.subSequence(8,9).toString()
        val date = "$month/$day/$year"
        val hour = dateCreated?.subSequence(11,16)

        binding.dateCreated.text = date
        binding.timeCreated.text = hour
        binding.ticketNum.text = ticketModel.idTickets.toString()
        binding.work.text = ticketModel.work
        binding.address.text = customerModel.address
        binding.viewTicket.setOnClickListener {
            Util.intentActivity(context, WorkTicketActivity::class.java,
                ticketModel.idTickets.toString(), customerModel.idCustomers.toString())
        }
    }
}