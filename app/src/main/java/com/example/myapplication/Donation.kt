package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class Donation (DonationAmount:Int, PaymentMethod : Int) : Parcelable {

    var DonationAmount = 0
    var PaymentMethod = 0

    constructor(parcel: Parcel) : this(0,1)
    {
        DonationAmount = parcel.readInt()
        PaymentMethod = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(DonationAmount)
        parcel.writeInt(PaymentMethod)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Donation> {
        override fun createFromParcel(parcel: Parcel): Donation {
            return Donation(parcel)
        }

        override fun newArray(size: Int): Array<Donation?> {
            return arrayOfNulls(size)
        }
    }
}