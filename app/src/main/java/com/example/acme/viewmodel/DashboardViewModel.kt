package com.example.acme.viewmodel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Html
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.example.acme.R
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers
import com.example.acme.model.entity.Tickets
import com.example.acme.model.repository.CustomerRepository
import com.example.acme.model.repository.TicketRepository
import com.example.acme.view.DatePickerFragment
import com.example.acme.view.GetDirectionsActivity
import com.example.acme.view.WorkTicketActivity


class DashboardViewModel: ViewModel() {

    private val repositoryTicket = TicketRepository()
    private val repositoryCustom = CustomerRepository()
    private lateinit var work: TextView
    private lateinit var customer: TextView

    fun calendar(context:Context) {
        val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)

        builder.setTitle("Calendar")
            .setView(R.layout.calendar)

        builder.create()
        builder.show()
    }

    fun addTicket(context:Context, date:String, fragmentManager:FragmentManager) {
        val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.new_ticket, null)
        work = view.findViewById<TextView>(R.id.work)
        val sheduled = view.findViewById<TextView>(R.id.sheduled)
        val note = view.findViewById<TextView>(R.id.dispatchtNote)
        val distance = "100"
        val dept = view.findViewById<TextView>(R.id.deptClass)
        val service = view.findViewById<TextView>(R.id.service)
        val reason = view.findViewById<TextView>(R.id.reasonCall)
        customer = view.findViewById<TextView>(R.id.customer)
        val phone = view.findViewById<TextView>(R.id.phone)
        val address = view.findViewById<TextView>(R.id.address)

        sheduled.setOnClickListener {
            showDatePickerDialog(sheduled, fragmentManager)
        }

        builder.setTitle("New Ticket")
            .setView(view)
            .setPositiveButton(
                Html.fromHtml(
                "<font color='#32CD32'><b>Save</b></font>"),
                DialogInterface.OnClickListener { dialog, id ->

                    ticketsRegister(context, work.text.toString(), date, sheduled.text.toString(),
                        note.text.toString(), distance, dept.text.toString(), service.text.toString(),
                        reason.text.toString())

                    customersRegister(context, customer.text.toString(), phone.text.toString(), address.text.toString())

                    Toast.makeText(context, "Ticket $work created successfully",
                        Toast.LENGTH_SHORT).show()

                    val idTicket = view.findViewById<TextView>(R.id.ticketNum)
                   // idTicket

                    dialog.cancel()

                    Util.intentActivity(context, WorkTicketActivity::class.java)

                })

        builder.create()
        builder.show()
    }

    fun ticketsRegister(context:Context, work: String, dateCreated: String, dateSheduled: String,
                      note: String, distance: String, deptClass: String, serviceType: String,
                      reasonCall: String) {

        repositoryTicket.TicketsInsert(context, work, dateCreated, dateSheduled, note,
            distance, deptClass, serviceType, reasonCall)
    }

    fun customersRegister(context:Context, customer: String, phone: String, address: String){

        repositoryCustom.CustomersInsert(context, customer, phone, address)
    }

    fun getTickets(context:Context):List<Tickets> {

        return listOf(repositoryTicket.getTicket(context, work.text.toString()))
    }

    fun getCustomer(context:Context):List<Customers> {

        return repositoryCustom.getCustomer(context, customer.text.toString())
    }

    fun showPopup(context:Context, v:View) {
        val popup = PopupMenu(context, v)
        val inflater:MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.dashboard_activity_menu, popup.menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_work_ticket ->
                    Util.intentActivity(context, WorkTicketActivity::class.java)
                R.id.action_new_ticket ->
                    Util.intentActivity(context, GetDirectionsActivity::class.java)
            }
            true
        })

        popup.show()
    }

    fun showDatePickerDialog(dateSheduled:TextView, fragmentManager:FragmentManager){

            val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day  ->
                // +1 because January is zero
                val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
                dateSheduled.text = selectedDate
            })

            newFragment.show(fragmentManager, "datePicker")
    }
}