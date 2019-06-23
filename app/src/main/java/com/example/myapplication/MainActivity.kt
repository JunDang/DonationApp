package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.widget.*
import com.example.myapplication.Campaign
import com.example.myapplication.Donation
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import java.util.ArrayList


class MainActivity : AppCompatActivity(), onDialogDone {



    lateinit var picker : NumberPicker
    lateinit var amountProggressBar : ProgressBar
    var target = 1000
    lateinit var myCampaign: Campaign
    lateinit var statusText : TextView
    var newTarget : Int = 0
    lateinit var db : MyDBHandler

    override fun onDialogDone(tag: String, cancelled: Boolean, target: Int) {

        if (!cancelled){
            myCampaign.target = target
            updateTheText()
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = MyDBHandler(this,"DonationsDB",null,1)

         picker = findViewById<NumberPicker>(R.id.donersNum)
         amountProggressBar = findViewById<ProgressBar>(R.id.amountProgressBar)

        setUI()
        myCampaign = Campaign(1000)
     //   myCampaign.setNewCampaign(1000)

        statusText = findViewById<TextView>(R.id.donationStatuse)

        updateTheText()
         }

    fun updateTheText(){
        statusText.text = "The Campaign Target is " + myCampaign.target + "The donation amount till now is " + myCampaign.getCurrentAmount()



    }

    fun DonationClicked(view :View){
        if (myCampaign.isFinished){
            Toast.makeText(this, "You reach The compaing target", 3).show()

        }
        else {

            var donation = Donation(0, 0)
            var paymentMethod: Int
            var payPalradioBut: RadioButton = findViewById(R.id.paypalradiobut)
            // var directradioBut = findViewById<RadioButton>(R.id.directradionbut)
            if (payPalradioBut.isSelected) {
                donation.PaymentMethod = 1 // for paypal
            } else donation.PaymentMethod = 2 // for direct

            var amountText = findViewById<EditText>(R.id.amountText)
            donation.DonationAmount = (amountText.text.toString()).toInt()


            if (donation.DonationAmount + myCampaign.getCurrentAmount() <= myCampaign.target) {

                amountProggressBar.setProgress(donation.DonationAmount)
                myCampaign.numberOfDoners++

                picker.value = myCampaign.numberOfDoners
                myCampaign.donations += donation

                updateTheText()
                db.insertDonation(donation)
            } else {

                myCampaign.isFinished = true
                Toast.makeText(this, "You reach The compaing target", 3).show()
                amountProggressBar.setProgress(myCampaign.target)
                myCampaign.numberOfDoners++
                picker.value = myCampaign.numberOfDoners
                myCampaign.donations += donation
                updateTheText()
                db.insertDonation(donation)

            }
        }

    }

fun setUI(){
    picker.maxValue = 100
    picker.minValue = 0
    amountProggressBar.progress = 0
    amountProggressBar.max = target
    amountProggressBar.min = 0

}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        super.onCreateOptionsMenu(menu)
        var inflatar : MenuInflater = menuInflater;
        inflatar.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            if (item!!.itemId == R.id.set_campaing)
            {
                myCampaign = Campaign(2000)
                val ft = fragmentManager.beginTransaction()
                val pdf = setNewCampaignClass()
                pdf.show(ft, "main")
            }
        else if (item.itemId == R.id.report){

                var intent = Intent(this,ReportActivity::class.java)
                intent.putParcelableArrayListExtra("donations",myCampaign.donations as ArrayList<out Parcelable>)
                startActivity(intent)
            }
        else if (item.itemId == R.id.dbreport){
                var intent = Intent(this,DBReport::class.java)
                startActivity(intent)


            }


        return super.onOptionsItemSelected(item)
    }
}
