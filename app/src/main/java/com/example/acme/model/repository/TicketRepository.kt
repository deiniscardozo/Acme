package com.example.acme.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.acme.model.DataBase
import com.example.acme.model.Util
import com.example.acme.model.entity.Tickets

class TicketRepository {

    private lateinit var conn:DataBase

    fun ticketsInsert(tickets: Tickets, context:Context) {
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        values.put(Util.TICKETS_WORK, tickets.work)
        values.put(Util.TICKETS_DATE, tickets.dateCreated)
        values.put(Util.TICKETS_SHEDULED, tickets.dateSheduled)
        values.put(Util.TICKETS_NOTE, tickets.note)
        values.put(Util.TICKETS_DISTANCE, tickets.distance)
        values.put(Util.TICKETS_DEPTCLASS, tickets.deptClass)
        values.put(Util.TICKETS_SERVICETYPE, tickets.serviceType)
        values.put(Util.TICKETS_REASONCALL, tickets.reasonCall)

        val newRowId = db.insert(Util.TICKETS_TABLE, null, values)

        Toast.makeText(context, "Id Registro: $newRowId", Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun queryTicket(context:Context): ArrayList<Tickets> {
        val sql = "select * from " + Util.TICKETS_TABLE
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val ticketsList = ArrayList<Tickets>()
        val cursor = db.rawQuery(sql, null)
        try {
        if (cursor.moveToFirst()) {
            do {
                val idTickets = cursor.getInt(0)
                val work = cursor.getString(1)
                val dateCreated = cursor.getString(2)
                val dateSheduled = cursor.getString(3)
                val note = cursor.getString(4)
                val distance = cursor.getString(5)
                val deptClass = cursor.getString(6)
                val serviceType = cursor.getString(7)
                val reasonCall = cursor.getString(8)
                val tickets = Tickets(idTickets,
                                      work,
                                      dateCreated,
                                      dateSheduled,
                                      note,
                                      distance,
                                      deptClass,
                                      serviceType,
                                      reasonCall)
                ticketsList.add(tickets)
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        } catch (e:Exception) {
            Toast.makeText(context, "Ticket does not exist", Toast.LENGTH_LONG).show()
        }
        return ticketsList
    }

    fun getTicket(context:Context, id:String): ArrayList<Tickets> {
        val sql = "select * from " + Util.TICKETS_TABLE + " where " + Util.TICKETS_ID + "= ?"
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val ticketsList = ArrayList<Tickets>()
        val cursor = db.rawQuery(sql, arrayOf(id))
        try {
            if (cursor.moveToFirst()) {
                do {
                    val idTickets = cursor.getInt(0)
                    val work = cursor.getString(1)
                    val dateCreated = cursor.getString(2)
                    val dateSheduled = cursor.getString(3)
                    val note = cursor.getString(4)
                    val distance = cursor.getString(5)
                    val deptClass = cursor.getString(6)
                    val serviceType = cursor.getString(7)
                    val reasonCall = cursor.getString(8)
                    val tickets = Tickets(idTickets,
                        work,
                        dateCreated,
                        dateSheduled,
                        note,
                        distance,
                        deptClass,
                        serviceType,
                        reasonCall)
                    ticketsList.add(tickets)
                }
                while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e:Exception) {
            Toast.makeText(context, "Ticket does not exist", Toast.LENGTH_LONG).show()
        }
        return ticketsList
    }

    fun deleteTicket(context:Context, param: String, ) {
        val db: SQLiteDatabase = conn.writableDatabase
        val param = arrayOf(param)
        db.delete(Util.TICKETS_TABLE,
            Util.TICKETS_ID + "=?",
            param)
        Toast.makeText(context, "Ticket deleted successfully", Toast.LENGTH_LONG)
            .show()

        db.close()
    }

    fun updateTicket(context:Context, tickets: Tickets, id:String) {
        val values = ContentValues()
        values.put(Util.TICKETS_WORK, tickets.work)
        values.put(Util.TICKETS_DATE, tickets.dateCreated)
        values.put(Util.TICKETS_SHEDULED, tickets.dateSheduled)
        values.put(Util.TICKETS_NOTE, tickets.note)
        values.put(Util.TICKETS_DISTANCE, tickets.distance)
        values.put(Util.TICKETS_DEPTCLASS, tickets.deptClass)
        values.put(Util.TICKETS_SERVICETYPE, tickets.serviceType)
        values.put(Util.TICKETS_REASONCALL, tickets.reasonCall)
        val db: SQLiteDatabase = conn.writableDatabase
        db.update(
            Util.TICKETS_TABLE,
            values,
            Util.TICKETS_ID +" = ?",
            arrayOf(id)
        )
        Toast.makeText(context, "Ticket successfully updated", Toast.LENGTH_LONG)
            .show()
        db.close()
    }
}