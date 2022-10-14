package com.example.acme.viewmodel

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.provider.CalendarContract
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
import java.util.*


class DashboardViewModel: ViewModel() {

    private val repositoryTicket = TicketRepository()
    private val repositoryCustom = CustomerRepository()

    fun calendar(context:Context, activity: Activity) {
        val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)

        builder.setTitle("Calendar")
            .setView(R.layout.calendar)
            .setPositiveButton(
                Html.fromHtml(
                    "<font color='#32CD32'><b>Save</b></font>"),
                DialogInterface.OnClickListener { dialog, id ->
                    calendarevent(activity)
                    dialog.cancel()
                })

        builder.create()
        builder.show()
    }

    fun addTicket(context:Context, date:String, fragmentManager:FragmentManager) {
        val builder = AlertDialog.Builder(context, R.style.CustomDialogTheme)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.new_ticket, null)
        val work = view.findViewById<TextView>(R.id.work)
        val sheduled = view.findViewById<TextView>(R.id.sheduled)
        val note = view.findViewById<TextView>(R.id.dispatchtNote)
        val distance = "100"
        val dept = view.findViewById<TextView>(R.id.deptClass)
        val service = view.findViewById<TextView>(R.id.service)
        val reason = view.findViewById<TextView>(R.id.reasonCall)
        val customer = view.findViewById<TextView>(R.id.customer)
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

                    val tickets = Tickets(0,
                                          work.text.toString(),
                                          date,
                                          sheduled.text.toString(),
                                          note.text.toString(),
                                          distance,
                                          dept.text.toString(),
                                          service.text.toString(),
                                          reason.text.toString())

                    ticketsRegister(tickets, context)

                    val customers = Customers(0,
                            customer.text.toString(),
                            phone.text.toString(),
                            address.text.toString()
                    )

                    customersRegister(customers, context)

                    val ticketsNum = tickets.idTickets.toString()

                    Toast.makeText(context, "Ticket $ticketsNum created successfully",
                        Toast.LENGTH_SHORT).show()

                    dialog.cancel()

                    Util.intentActivity(context, WorkTicketActivity::class.java, "", "")

                })

        builder.create()
        builder.show()
    }

    fun ticketsRegister(tickets: Tickets ,context:Context) {
        repositoryTicket.ticketsInsert(tickets, context)
    }

    fun customersRegister(customers:Customers, context:Context){
        repositoryCustom.customersInsert(customers, context)
    }

    fun getTickets(context:Context, id:String):List<Tickets> {
        return repositoryTicket.queryTicket(context)
    }

    fun getCustomer(context:Context):List<Customers> {
        return repositoryCustom.queryCustomer(context)
    }

    fun showPopup(context:Context, v:View) {
        val popup = PopupMenu(context, v)
        val inflater:MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.dashboard_activity_menu, popup.menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_work_ticket ->
                    Util.intentActivity(context, WorkTicketActivity::class.java, "", "")
                R.id.action_new_ticket ->
                    Util.intentActivity(context, GetDirectionsActivity::class.java, "", "")
            }
            true
        })

        popup.show()
    }

    fun showDatePickerDialog(dateSheduled:TextView, fragmentManager:FragmentManager){
            val newFragment = DatePickerFragment.newInstance(
                DatePickerDialog.OnDateSetListener { _, year, month, day  ->
                // +1 because January is zero
                val selectedDate = day.toString() + "-" + (month + 1) + "-" + year
                dateSheduled.text = selectedDate
            })

            newFragment.show(fragmentManager, "datePicker")
    }

    fun calendarevent(activity: Activity) {
        var cal: Calendar = Calendar.getInstance()

        cal.set(Calendar.DAY_OF_MONTH, 29)
        cal.set(Calendar.MONTH, 4)
        cal.set(Calendar.YEAR, 2013)

        cal.set(Calendar.HOUR_OF_DAY, 22)
        cal.set(Calendar.MINUTE, 45)

        var intent = Intent(Intent.ACTION_EDIT)

        intent.type = "vnd.android.cursor.item/event";

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.timeInMillis);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.timeInMillis +60*60*1000);

        intent.putExtra(CalendarContract.Events.ALL_DAY, false);
        intent.putExtra(CalendarContract.Events.RRULE , "FREQ=DAILY");
        intent.putExtra(CalendarContract.Events.TITLE, "Título de vuestro evento");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "Descripción");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"Calle ....");

        activity.startActivity(intent)
    }
}