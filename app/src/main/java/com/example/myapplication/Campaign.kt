package com.example.myapplication

class Campaign (target : Int) {

    var target = 0
    var numberOfDoners = 0
 lateinit var donations : List<Donation>
    var isFinished = false

init {
    this.target = target
    donations = ArrayList<Donation>()
}

    fun setNewCampaign(campaignTarget : Int){

        this.target = campaignTarget


    }
    fun getCurrentAmount() : Int{
        var amount = 0
        for (donation in this.donations){

            amount = amount + donation.DonationAmount
        }
        return amount
    }
}