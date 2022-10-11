package com.example.acme.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.acme.model.DataBase
import com.example.acme.model.Util
import com.example.acme.model.entity.Tickets

class TicketRepository {

    private lateinit var conn:DataBase
    private lateinit var cursor:Cursor
    private var work: String = ""
    private var dateCreated: String = ""
    private var dateSheduled: String = ""
    private var note: String = ""
    private var distance: String = ""
    private var deptClass: String = ""
    private var serviceType: String = ""
    private var reasonCall: String = ""

    fun TicketsInsert(context:Context, work: String, dateCreated: String, dateSheduled: String, note: String,
                      distance: String, deptClass: String, serviceType: String, reasonCall: String) {
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        //insert into tickets (idTickets,work,dateCreated,dateSheduled,note,distance,deptClass,serviceType,reasonCall)
        values.put(Util.TICKETS_WORK, work)
        values.put(Util.TICKETS_DATE, dateCreated)
        values.put(Util.TICKETS_SHEDULED, dateSheduled)
        values.put(Util.TICKETS_NOTE, note)
        values.put(Util.TICKETS_DISTANCE, distance)
        values.put(Util.TICKETS_DEPTCLASS, deptClass)
        values.put(Util.TICKETS_SERVICETYPE, serviceType)
        values.put(Util.TICKETS_REASONCALL, reasonCall)

        val newRowId = db.insert(Util.TICKETS_TABLE, Util.TICKETS_WORK, values)

        Toast.makeText(context, "Id Registro: $newRowId", Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun queryTicket(contex:Context):Tickets {
        conn = DataBase(contex)
        val db:SQLiteDatabase = conn.readableDatabase
        //val param = if(workTicket.isEmpty()) { null } else { arrayOf(workTicket) }
        //val select = if(workTicket.isEmpty()) { null } else { Util.TICKETS_WORK }
        val columns = arrayOf(Util.TICKETS_WORK, Util.TICKETS_DATE,
            Util.TICKETS_SHEDULED, Util.TICKETS_NOTE, Util.TICKETS_DISTANCE,
            Util.TICKETS_DEPTCLASS, Util.TICKETS_SERVICETYPE, Util.TICKETS_REASONCALL)

        try {
            cursor = db.query(
                Util.TICKETS_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null
            )
            cursor.moveToFirst()

            work = cursor.getString(0)
            dateCreated = cursor.getString(1)
            dateSheduled = cursor.getString(2)
            note = cursor.getString(3)
            distance = cursor.getString(4)
            deptClass = cursor.getString(5)
            serviceType = cursor.getString(6)
            reasonCall = cursor.getString(7)

            cursor.close()

        } catch (e:Exception) {
            Toast.makeText(contex, "Ticket does not exist", Toast.LENGTH_LONG).show()
        }

        return Tickets(work, dateCreated, dateSheduled, note,
            distance, deptClass, serviceType, reasonCall)
    }

    fun deleteTicket(context:Context, param: String, ) {
        val db: SQLiteDatabase = conn.writableDatabase
        val param = arrayOf<String>(param)
        db.delete(Util.TICKETS_TABLE,
            Util.TICKETS_WORK + "=?",
            param)
        Toast.makeText(context, "Ticket deleted successfully", Toast.LENGTH_LONG)
            .show()

        db.close()
    }

    fun updateTicket(context:Context, work: String, dateCreated: String, dateSheduled: String, note: String,
                             distance: String, deptClass: String, serviceType: String, reasonCall: String) {
        val db: SQLiteDatabase = conn.writableDatabase
        val param = arrayOf<String>(work)
        val values = ContentValues()
        values.put(Util.TICKETS_WORK, work)
        values.put(Util.TICKETS_DATE, dateCreated)
        values.put(Util.TICKETS_SHEDULED, dateSheduled)
        values.put(Util.TICKETS_NOTE, note)
        values.put(Util.TICKETS_DISTANCE, distance)
        values.put(Util.TICKETS_DEPTCLASS, deptClass)
        values.put(Util.TICKETS_SERVICETYPE, serviceType)
        values.put(Util.TICKETS_REASONCALL, reasonCall)
        db.update(Util.TICKETS_TABLE, values, Util.TICKETS_WORK + "=?", param)
        Toast.makeText(context, "Ticket successfully updated", Toast.LENGTH_LONG)
            .show()
        db.close()
    }

    fun getTicket(contex:Context): Tickets{
        return queryTicket(contex)
    }
}