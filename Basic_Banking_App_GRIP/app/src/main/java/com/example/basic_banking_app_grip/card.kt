package com.example.basic_banking_app_grip

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.log

class card : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        var cstname: TextView = findViewById(R.id.name_of_cst);
        var cstemail: TextView = findViewById(R.id.email_of_cust);
        var cstbalance: TextView = findViewById(R.id.balance_of_cst)
        var cstno: TextView = findViewById(R.id.phone_of_cst)
        var transferbtn : Button = findViewById(R.id.transferbtn)
        val cstid: String

        cstname.text= intent.getStringExtra("name")
        cstemail.text= intent.getStringExtra("email").toString()
        cstbalance.text = intent.getStringExtra("amount").toString()
        cstno.text = intent.getStringExtra("phno").toString()
        cstid = intent.getStringExtra("id").toString()
        transferbtn.setOnClickListener(){
            val mDialogView =  layoutInflater.inflate(R.layout.activity_amount_box,null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Transfer Money")
            val mAlertDialog = mBuilder.show()
            var amountbtn : Button = mDialogView.findViewById(R.id.select_btn)
            amountbtn.setOnClickListener(){
                var amount : EditText = mDialogView.findViewById(R.id.enter_amount)
                val money_to_transfer : String = amount.text.toString()
                var i = Intent(this,customer_list::class.java)
                i.putExtra("isTransfer","true")
                i.putExtra("amount",money_to_transfer)
                i.putExtra("id",cstid)
                startActivity(i)
            }
        }
    }

}