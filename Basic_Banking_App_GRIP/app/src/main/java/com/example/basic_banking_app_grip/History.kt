package com.example.basic_banking_app_grip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class History : AppCompatActivity() {
    private lateinit var newArrayList: ArrayList<HistoryClassModel>
    private lateinit var newRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        newRecyclerView=findViewById(R.id.history_layout)
        var databaseHandler = HistoryDatabaseHandler(this)
        newArrayList = databaseHandler.getalldata()
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.adapter = HistoryAdapter(newArrayList,this)

    }
}