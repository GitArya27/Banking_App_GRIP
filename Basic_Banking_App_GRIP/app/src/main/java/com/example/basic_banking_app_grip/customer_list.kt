package com.example.basic_banking_app_grip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class customer_list : AppCompatActivity() ,Select_listner {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CustomerModelClass>
    private lateinit var layout: ConstraintLayout
    private var isSecondTime: Boolean = false
    private var fromcustid: Int = -1
    private lateinit var cust_name_from : String
    private var amountToTransfer: Int =-1
    private var current_balance: Int =-1
    private lateinit var arrayList: ArrayList<HistoryClassModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        if(intent.getStringExtra("isTransfer")=="true")
        {
            cust_name_from = intent.getStringExtra("name").toString()
            current_balance = intent.getIntExtra("balance",-1)
            amountToTransfer = intent.getIntExtra("amount",-1)
            fromcustid = intent.getIntExtra("id",-1)
            isSecondTime = true

        }

        var databaseHandler: DatabaseHandler = DatabaseHandler(this)
        newArrayList = databaseHandler.viewCustomer()
        newRecyclerView = findViewById(R.id.customerDataList)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.adapter = CustomerAdapter(newArrayList,this)


    }

    override fun onItemClicked(customerModelClass: CustomerModelClass) {


        val updatedata : DatabaseHandler = DatabaseHandler(this)

        if(isSecondTime)
        {
            if(current_balance != -1 && amountToTransfer !=-1 && fromcustid !=-1)
            {
                val i = Intent(this, MainActivity::class.java)
                var a:Int = current_balance-amountToTransfer
                var b : Int = customerModelClass.balance + amountToTransfer
                Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show()
                updatedata.updateData(fromcustid,a)
                updatedata.updateData(customerModelClass.id,b)

                //make transaction history

                val historyhandler = HistoryDatabaseHandler(this)
                historyhandler.adddata(HistoryClassModel(0,cust_name_from,customerModelClass.name,current_balance.toString(),customerModelClass.balance.toString(),a.toString(),b.toString(),amountToTransfer.toString()))
                startActivity(i)
            }
            else{
                Toast.makeText(this,"Error!",Toast.LENGTH_LONG).show()
            }
        }




        else {
            val i = Intent(this, card::class.java)
            i.putExtra("name", customerModelClass.name);
            i.putExtra("id", customerModelClass.id);
            i.putExtra("phno", customerModelClass.extra);
            i.putExtra("amount", customerModelClass.balance);
            i.putExtra("amount2", customerModelClass.balance.toString());
            i.putExtra("email", customerModelClass.email);
            i.putExtra("istransfer", false)
            startActivity(i)
        }
    }


}