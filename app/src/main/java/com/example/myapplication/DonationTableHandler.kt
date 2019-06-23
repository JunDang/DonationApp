package com.example.myapplication

import android.database.sqlite.SQLiteDatabase


class DonationTableHandler {



    companion object{

        val table_name = "Donation"
        val amountCol = "amount"
        var paymentmethod ="method"
        var idCol = "_id"

    val createTableQuery = "create table "+ table_name + "( _id int primary key , " + amountCol +" int not null , " +
    paymentmethod + " text not null );"


        fun onCreate(database: SQLiteDatabase) {
            database.execSQL(createTableQuery)

        }

        fun onUpgrade(database: SQLiteDatabase) {

            database.execSQL("drop table if Exist " + table_name)
            onCreate(database)
        }
    }
}