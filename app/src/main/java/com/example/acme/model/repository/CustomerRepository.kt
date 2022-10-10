package com.example.acme.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.widget.Toast
import com.example.acme.model.DataBase
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers

class CustomerRepository {

    private lateinit var conn:DataBase
    private lateinit var cursor:Cursor
    private var customer: String = ""
    private var phone: String = ""
    private var address: String = ""

    fun CustomersInsert(context:Context, customer:String, phone:String, address:String) {
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        //insert into users (id,customer,phone,address)
        values.put(Util.CUSTOMERS_CUSTOMER, customer)
        values.put(Util.CUSTOMERS_PHONE, phone)
        values.put(Util.CUSTOMERS_ADDRESS, address)

        val newRowId = db.insert(Util.CUSTOMERS_TABLE, Util.CUSTOMERS_CUSTOMER, values)

        Toast.makeText(context, "Id Registro: $newRowId", Toast.LENGTH_SHORT).show()
        db.close()
    }

    private fun queryCustomer(contex:Context):Customers {
        conn = DataBase(contex)
        val db:SQLiteDatabase = conn.readableDatabase
       // val param = if(customerName.isEmpty()) { null } else { arrayOf(customerName) }
        //val select = if(customerName.isEmpty()) { null } else { Util.CUSTOMERS_CUSTOMER }
        val columns = arrayOf(Util.CUSTOMERS_CUSTOMER, Util.CUSTOMERS_PHONE, Util.CUSTOMERS_ADDRESS)

        try {
            cursor = db.query(
                Util.CUSTOMERS_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null
            )

            cursor.moveToFirst()
            customer = cursor.getString(0)
            phone = cursor.getString(1)
            address = cursor.getString(2)
            cursor.close()


        } catch (e:Exception) {
            Toast.makeText(contex, "Username does not exist", Toast.LENGTH_LONG).show()
        }

        return Customers(customer, phone, address)
    }

     fun getCustomer(contex:Context): List<Customers> {
         return listOf(queryCustomer(contex))
     }
}