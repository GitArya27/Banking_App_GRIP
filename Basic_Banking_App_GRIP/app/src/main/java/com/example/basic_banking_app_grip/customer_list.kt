package com.example.basic_banking_app_grip

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class customer_list : AppCompatActivity() ,Select_listner {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CustomerModelClass>
    private lateinit var layout: ConstraintLayout
    private var isSecondTime: Boolean = false
    private lateinit var fromcustid: String
    private lateinit var tocustid: String
    private lateinit var amountToTransfer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)
        if(intent.getStringExtra("isTransfer")=="true")
        {
            amountToTransfer = intent.getStringExtra("amount").toString()
            fromcustid = intent.getStringExtra("id").toString()
            isSecondTime = true
            Toast.makeText(this, "working", Toast.LENGTH_SHORT).show()
        }

        var databaseHandler: DatabaseHandler = DatabaseHandler(this)
        newArrayList = databaseHandler.viewCustomer()
        newRecyclerView = findViewById(R.id.customerDataList)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.adapter = CustomerAdapter(newArrayList,this)


    }

    override fun onItemClicked(customerModelClass: CustomerModelClass) {

        if(isSecondTime)
        {
            tocustid = customerModelClass.id.toString()

            //update the data
            //make transaction history


            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        else {
            val i = Intent(this, card::class.java)
            i.putExtra("name", customerModelClass.name);
            i.putExtra("id", customerModelClass.id);
            i.putExtra("phno", customerModelClass.extra);
            i.putExtra("amount", customerModelClass.balance.toString());
            i.putExtra("email", customerModelClass.email);
            i.putExtra("istransfer", false)
            startActivity(i)
        }
    }


}