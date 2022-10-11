package com.example.acme.viewmodel

import android.content.Context
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModel
import com.example.acme.R
import com.example.acme.model.Util
import com.example.acme.view.GetDirectionsActivity
import com.example.acme.view.WorkTicketActivity
import com.example.acme.view.dashboard.DashboardActivity

class GetDirectionViewModel: ViewModel() {

    fun showPopup(context: Context, v: View) {
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.get_ticket_activity_menu, popup.menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_dashboard ->
                    Util.intentActivity(context, DashboardActivity::class.java)
                R.id.action_new_ticket ->
                    Util.intentActivity(context, WorkTicketActivity::class.java)
            }
            true
        })

        popup.show()
    }
}