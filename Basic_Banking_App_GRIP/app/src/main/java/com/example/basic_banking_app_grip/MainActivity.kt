package com.example.basic_banking_app_grip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var TransferButton: Button
lateinit var ViewAll: Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewAll = findViewById(R.id.ViewAll)
        TransferButton = findViewById(R.id.Transactionbtn)

        val databaseHandler  = DatabaseHandler(this)
        databaseHandler.addCustomer(CustomerModelClass(0,"Arya Singh","arya2792@gmail.com",5000,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(1,"Ayushi Gautam","singh92@gmail.com",9500,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(2,"Suresh","sina2792@gmail.com",1600,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(3,"Random Name","siarya2792@gmail.com",2600,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(4,"Rakesh","sing2792@gmail.com",4600,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(5,"Anushka Singh","sin2@gmail.com",4000,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(6,"Gopal Singh Chauhan","singh792@gmail.com",5500,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(7,"Anmol Yadav","singh92@gmail.com",9500,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(8," Snehil Gupta","singh92@gmail.com",7500,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(9,"Pallavi Kumari","singh92@gmail.com",9800,"extra"))
        databaseHandler.addCustomer(CustomerModelClass(10,"Shyamlee Kumari","singh92@gmail.com",4500,"extra"))


        ViewAll.setOnClickListener {
            val i  =  Intent(this,customer_list::class.java)
            startActivity(i)

        }
        TransferButton.setOnClickListener {
            val i = Intent(this,History::class.java)
            startActivity(i)
        }


    }

}