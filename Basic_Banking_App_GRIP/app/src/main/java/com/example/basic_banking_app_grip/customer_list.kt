package com.example.basic_banking_app_grip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class customer_list : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CustomerModelClass>
    private lateinit var layout: ConstraintLayout
    companion object{
        fun getContext():Context{
            return getContext()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)
        var databaseHandler: DatabaseHandler = DatabaseHandler(this)
        newArrayList = databaseHandler.viewCustomer()
        newRecyclerView = findViewById(R.id.customerDataList)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.adapter = CustomerAdapter(newArrayList)

//
//        layout.findViewById<ConstraintLayout>(R.id.layout_for_card)
//        layout.setOnClickListener(){
//            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
//        }


    }




}