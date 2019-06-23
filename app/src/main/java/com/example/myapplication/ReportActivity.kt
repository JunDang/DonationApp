package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView

class ReportActivity : AppCompatActivity() {

lateinit var donation_list : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report_layout)
        donation_list = findViewById(R.id.all_donations_list)
        val list = this.intent.getParcelableArrayListExtra<Donation>("donations")
        donation_list.adapter = DonationListAdapter(this,R.layout.report_layout,list)
    }

}
class DonationListAdapter(context: Context, resource: Int, list:ArrayList<Donation>) : ArrayAdapter<Donation>(context,resource,list){

    var resource: Int
    var list : ArrayList<Donation>

    init {
        this.resource = resource
        this.list = list
    //   this.vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {

        return this.list.count()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var v = inflater.inflate(R.layout.list_row_layout,parent,false)

        var thisDonation = this.list.get(position)

        var amountText = v.findViewById<TextView>(R.id.donation_amount_id)
        var paymentText = v.findViewById<TextView>(R.id.payment_method_id)

        amountText.text = thisDonation.DonationAmount.toString()
        if (thisDonation.PaymentMethod == 1)
            paymentText.text = "PayPal"
        else
            paymentText.text = "Direct"

        return v

    }

}
