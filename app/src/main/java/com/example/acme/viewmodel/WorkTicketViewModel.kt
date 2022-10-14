package com.example.acme.viewmodel

import android.content.Context
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModel
import com.example.acme.R
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets
import com.example.acme.model.repository.CustomerRepository
import com.example.acme.model.repository.TicketRepository
import com.example.acme.view.GetDirectionsActivity
import com.example.acme.view.dashboard.DashboardActivity

class WorkTicketViewModel: ViewModel() {

    private val repositoryTicket = TicketRepository()
    private val repositoryCustom = CustomerRepository()

    fun getTickets(context:Context):List<Tickets> {
        return repositoryTicket.queryTicket(context)
    }

    fun getCustomer(context:Context):List<Customers> {
        return repositoryCustom.queryCustomer(context)
    }

    fun getTicketView(context:Context, id:String):List<Tickets> {
        return repositoryTicket.getTicket(context, id)
    }

    fun getCustomerView(context:Context, id:String):List<Customers> {
        return repositoryCustom.gtCustomer(context, id)
    }

    fun deleteCustomer(context:Context, customName: String) {
        repositoryCustom.deleteCustomer(context, customName)
    }

    fun updateCustomer(context:Context, customers:Customers, id:String) {
        repositoryCustom.updateCustomer(context, customers, id)
    }

    fun updateTickets(context:Context, tickets:Tickets, id:String) {
        repositoryTicket.updateTicket(context, tickets, id)
    }

    fun deleteTickets(context:Context, work: String) {
        repositoryTicket.deleteTicket(context,work)
    }

    fun showPopup(context:Context, v:View) {
        val popup = PopupMenu(context, v)
        val inflater:MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.work_ticket_activity_menu, popup.menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_dashboard ->
                    Util.intentActivity(context, DashboardActivity::class.java, "", "")
                R.id.action_new_ticket ->
                    Util.intentActivity(context, GetDirectionsActivity::class.java, "Caracas", "")
            }
            true
        })

        popup.show()
    }
}