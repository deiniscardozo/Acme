package com.example.acme.model.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import com.example.acme.model.DataBase
import com.example.acme.model.Util
import com.example.acme.model.entity.Customers

class CustomerRepository {

    private lateinit var conn:DataBase
    private lateinit var cursor:Cursor


    fun customersInsert(customers: Customers, context:Context) {
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val values = ContentValues()

        values.put(Util.CUSTOMERS_CUSTOMER, customers.customer)
        values.put(Util.CUSTOMERS_PHONE, customers.phone)
        values.put(Util.CUSTOMERS_ADDRESS, customers.address)

        val newRowId = db.insert(Util.CUSTOMERS_TABLE, null, values)

        Toast.makeText(context, "Id Registro: $newRowId", Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun queryCustomer(context:Context): ArrayList<Customers> {
        val sql = "select * from " + Util.CUSTOMERS_TABLE
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val customersList = ArrayList<Customers>()
        val cursor = db.rawQuery(sql, null)
        try {
        if (cursor.moveToFirst()) {
            do {
                val idCustomers = cursor.getInt(0)
                val custom = cursor.getString(1)
                val phone = cursor.getString(2)
                val address = cursor.getString(3)
                val customers = Customers(idCustomers,
                    custom,
                    phone,
                    address)
                customersList.add(customers)
            }
            while (cursor.moveToNext())
        }
        cursor.close()
        } catch (e:Exception) {
            Toast.makeText(context, "Customer does not exist", Toast.LENGTH_LONG).show()
        }
        return customersList
    }

    fun gtCustomer(context:Context, id:String): ArrayList<Customers> {
        val sql = "select * from " + Util.CUSTOMERS_TABLE + " where " + Util.CUSTOMERS_ID + "= ?"
        conn = DataBase(context)
        val db:SQLiteDatabase = conn.writableDatabase
        val customersList = ArrayList<Customers>()
        val cursor = db.rawQuery(sql, arrayOf(id))
        try {
            if (cursor.moveToFirst()) {
                do {
                    val idCustomers = cursor.getInt(0)
                    val custom = cursor.getString(1)
                    val phone = cursor.getString(2)
                    val address = cursor.getString(3)
                    val customers = Customers(idCustomers,
                        custom,
                        phone,
                        address)
                    customersList.add(customers)
                }
                while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e:Exception) {
            Toast.makeText(context, "Customer does not exist", Toast.LENGTH_LONG).show()
        }
        return customersList
    }

    fun deleteCustomer(context:Context,param: String, ) {
        val db: SQLiteDatabase = conn.writableDatabase
        val param = arrayOf(param)
        db.delete(Util.CUSTOMERS_TABLE,
            Util.CUSTOMERS_CUSTOMER + "=?",
            param)
        Toast.makeText(context, "Customer deleted successfully", Toast.LENGTH_LONG)
            .show()

        db.close()
    }

    fun updateCustomer(context:Context, customers:Customers, id:String) {
        val values = ContentValues()
        values.put(Util.CUSTOMERS_CUSTOMER, customers.customer)
        values.put(Util.CUSTOMERS_PHONE, customers.phone)
        values.put(Util.CUSTOMERS_ADDRESS, customers.address)
        val db: SQLiteDatabase = conn.writableDatabase
        db.update(
            Util.CUSTOMERS_TABLE,
            values,
            Util.CUSTOMERS_ID +" = ?",
            arrayOf(id)
        )
        Toast.makeText(context, "Customer successfully updated", Toast.LENGTH_LONG)
            .show()
        db.close()
    }
}