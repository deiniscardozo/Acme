package com.example.acme.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object Util: AppCompatActivity() {

    //Constants user table fields
    const val USERS_TABLE = "Users"
    const val USER_ID = "id"
    const val USER_NAME = "user"
    const val USER_PASS = "password"
    const val CREATE_TABLE_USERS = "CREATE TABLE " +
            "" + USERS_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
             USER_NAME + " TEXT," + USER_PASS + " TEXT)"

    //Constants tickets table fields
    const val TICKETS_TABLE = "Tickets"
    const val TICKETS_ID = "idTickets"
    const val TICKETS_WORK = "work"
    const val TICKETS_DATE = "dateCreated"
    const val TICKETS_SHEDULED = "dateSheduled"
    const val TICKETS_NOTE = "note"
    const val TICKETS_DISTANCE = "distance"
    const val TICKETS_DEPTCLASS = "deptClass"
    const val TICKETS_SERVICETYPE = "serviceType"
    const val TICKETS_REASONCALL = "reasonCall"
    const val CREATE_TABLE_TICKETS = "CREATE TABLE " +
            "" + TICKETS_TABLE + " (" + TICKETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TICKETS_WORK + " TEXT," + TICKETS_DATE + " TEXT," + TICKETS_SHEDULED + " TEXT," +
            TICKETS_NOTE + " TEXT," + TICKETS_DISTANCE + " TEXT," + TICKETS_DEPTCLASS + " TEXT," +
            TICKETS_SERVICETYPE + " TEXT," + TICKETS_REASONCALL + " TEXT)"

    //Constants customers table fields
    const val CUSTOMERS_TABLE = "Customers"
    const val CUSTOMERS_ID = "id"
    const val CUSTOMERS_CUSTOMER = "customer"
    const val CUSTOMERS_PHONE = "phone"
    const val CUSTOMERS_ADDRESS = "address"
    const val CREATE_TABLE_CUSTOMERS = "CREATE TABLE " +
            "" + CUSTOMERS_TABLE + " (" + CUSTOMERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CUSTOMERS_CUSTOMER + " TEXT," + CUSTOMERS_PHONE + " TEXT," + CUSTOMERS_ADDRESS + " TEXT)"

    //Intent Activity
    fun intentActivity(context: Context?, activity: Class<*>, param: String, param1: String) {
        ContextCompat.startActivity(
            context!!,
            Intent(context, activity)
                .putExtra("param", param)
                .putExtra("param1", param1),
            Bundle()
        )
        finish()
    }
}