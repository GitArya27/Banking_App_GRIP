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
        databaseHandler.addCustomer(CustomerModelClass(0,"Arya Singh","arya2792@gmail.com",5000,"8780774665"))
        databaseHandler.addCustomer(CustomerModelClass(1,"Ayushi Gautam","singh92@gmail.com",9500,"9876543210"))
        databaseHandler.addCustomer(CustomerModelClass(2,"Suresh","sina2792@gmail.com",1600,"9652301478"))
        databaseHandler.addCustomer(CustomerModelClass(3,"Random Name","siarya2792@gmail.com",2600,"9874563210"))
        databaseHandler.addCustomer(CustomerModelClass(4,"Rakesh","sing2792@gmail.com",4600,"9012365478"))
        databaseHandler.addCustomer(CustomerModelClass(5,"Anushka Singh","sin2@gmail.com",4000,"9025836147"))
        databaseHandler.addCustomer(CustomerModelClass(6,"Gopal Singh Chauhan","singh792@gmail.com",5500,"9036258147"))
        databaseHandler.addCustomer(CustomerModelClass(7,"Anmol Yadav","singh92@gmail.com",9500,"987415263"))
        databaseHandler.addCustomer(CustomerModelClass(8," Snehil Gupta","singh92@gmail.com",7500,"9638527410"))
        databaseHandler.addCustomer(CustomerModelClass(9,"Pallavi Kumari","singh92@gmail.com",9800,"9658741230"))
        databaseHandler.addCustomer(CustomerModelClass(10,"Shyamlee Kumari","singh92@gmail.com",4500,"6321054789"))


        ViewAll.setOnClickListener {
            val i  =  Intent(this,customer_list::class.java)
            startActivity(i)

        }
        TransferButton.setOnClickListener {
            val i = Intent(this,History::class.java)
            intent.putExtra("istransfer",false);
            startActivity(i)
        }


    }

}