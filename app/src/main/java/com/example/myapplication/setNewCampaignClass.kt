package com.example.myapplication

import android.app.Activity
import android.app.DialogFragment
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.zip.Inflater

class setNewCampaignClass : DialogFragment(), View.OnClickListener {

lateinit var et: EditText


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        val v = inflater?.inflate(R.layout.new_campaign_fragment, container, false)

        et = v?.findViewById(R.id.inputtext)!!
        val tv = v?.findViewById(R.id.promptmessage) as TextView

        tv.text = "Please enter the new Campaign Target..."

        val dismissBtn = v.findViewById(R.id.btn_dismiss) as Button
        dismissBtn.setOnClickListener(this)

        val saveBtn = v.findViewById(R.id.btn_save) as Button
        saveBtn.setOnClickListener(this)
        et = v.findViewById(R.id.inputtext) as EditText

        return v

    }


    override fun onClick(v: View) {
        val act = activity as onDialogDone

        if (v.id == R.id.btn_save) {
                val tv = view!!.findViewById<View>(R.id.inputtext) as TextView
                act.onDialogDone(this.tag, false, tv.text.toString().toInt())
                dismiss()
                return
            }

        if (v.id == R.id.btn_dismiss)
            {
                act.onDialogDone(this.tag, true, 0)
                dismiss()
                return
            }



    }

}