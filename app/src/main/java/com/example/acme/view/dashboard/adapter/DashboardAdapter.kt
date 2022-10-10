package com.example.acme.view.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.acme.R
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets

class DashboardAdapter (private val listTickets: MutableList<Tickets>,
                        private val listCustomers: MutableList<Customers>,
                        private val context:Context) :
    RecyclerView.Adapter<DashboardViewHolder>(){

    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): DashboardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DashboardViewHolder(layoutInflater.inflate(
            R.layout.item_dashboard, parent
            , false))
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val itemTicket = listTickets[position]
        val itemCustomer = listCustomers[position]
        holder.render(itemTicket, itemCustomer, context)

    }
    override fun getItemCount(): Int = listTickets.size

   fun setItems(listT: List<Tickets>, listC: List<Customers>) {
       listTickets.clear()
       listTickets.addAll(listT)
       listCustomers.clear()
       listCustomers.addAll(listC)
       notifyDataSetChanged()
    }
}