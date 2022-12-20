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
        val cstid: Int
        val cstbal: Int

        cstname.text= intent.getStringExtra("name")
        cstemail.text= intent.getStringExtra("email").toString()
        cstbalance.text = intent.getStringExtra("amount2").toString()
        cstno.text = intent.getStringExtra("phno").toString()
        cstid = intent.getIntExtra("id",-1)
        cstbal = intent.getIntExtra("amount",-1)


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

                val money = money_to_transfer.toInt()
//                Toast.makeText(this,money_to_transfer,Toast.LENGTH_LONG).show()
                if(money == null){

                    Toast.makeText(this,"Please Enter Valid Input",Toast.LENGTH_SHORT).show()

                }else{
                    if(money>cstbal)
                    {
                        Toast.makeText(this,"you do not have enough balance",Toast.LENGTH_SHORT).show()
                    }
                    else{

                        var i = Intent(this,customer_list::class.java)
                        i.putExtra("isTransfer","true")
                        i.putExtra("amount",money)
                        i.putExtra("id",cstid)
                        i.putExtra("balance",cstbal)
                        i.putExtra("name",cstname.text.toString())
                        startActivity(i)

                    }
                }


            }
        }
    }

}