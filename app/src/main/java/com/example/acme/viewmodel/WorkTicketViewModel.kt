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

        return listOf(repositoryTicket.getTicket(context))
    }

    fun getCustomer(context:Context):List<Customers> {

        return repositoryCustom.getCustomer(context)
    }

    fun updateCustomer(context:Context,customName: String, phone: String, address: String) {

        repositoryCustom.updateCustomer(context, customName, phone, address)
    }

    fun deleteCustomer(context:Context, customName: String) {
        repositoryCustom.deleteCustomer(context, customName)
    }

    fun updateTickets(context:Context, work: String, dateCreated: String, dateSheduled: String, note: String,
                      distance: String, deptClass: String, serviceType: String, reasonCall: String) {

        repositoryTicket.updateTicket(context,work, dateCreated, dateSheduled, note,
            distance, deptClass, serviceType, reasonCall)
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
                    Util.intentActivity(context, DashboardActivity::class.java)
                R.id.action_new_ticket ->
                    Util.intentActivity(context, GetDirectionsActivity::class.java)
            }
            true
        })

        popup.show()
    }
}