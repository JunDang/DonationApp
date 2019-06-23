package com.example.myapplication

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


val DATABASE_NAME = "DonationsDB"
val DATABASE_VERSION = 1

class MyDBHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    private val myCR: ContentResolver
    init {
        myCR = context.contentResolver

    }
    override fun onCreate(db: SQLiteDatabase) {
        DonationTableHandler.onCreate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        DonationTableHandler.onCreate(db)

    }
    fun insertDonation(newDonation : Donation){

        val values = ContentValues()
        values.put(DonationTableHandler.amountCol, newDonation.DonationAmount)
        values.put(DonationTableHandler.paymentmethod, "paypal")
        myCR.insert(DonationDBProvider.CONTENT_URI, values)
    }
}