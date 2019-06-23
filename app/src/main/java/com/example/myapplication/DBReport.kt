package com.example.myapplication

import android.app.ListActivity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import java.util.zip.Inflater

class DBReport : ListActivity(), LoaderManager.LoaderCallbacks<Cursor> {


    lateinit var adapter : SimpleCursorAdapter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_report_layout)

        fillData()
        this.listView.dividerHeight = 2

    }
fun fillData (){

    val from = arrayOf( DonationTableHandler.amountCol , DonationTableHandler.paymentmethod )
    var to = intArrayOf(R.id.db_amount_row, R.id.db_payment_method_row)
    loaderManager.initLoader(1, null, this)
    adapter = SimpleCursorAdapter(this,R.layout.db_list_donation_row,null,from,to,0)
    this.listView.adapter = adapter
}

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val projection = arrayOf("_id",DonationTableHandler.amountCol , DonationTableHandler.paymentmethod )
        return CursorLoader(this, DonationDBProvider.CONTENT_URI, projection, null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        adapter.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        adapter.swapCursor(null)
    }


}