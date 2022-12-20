package com.example.basic_banking_app_grip


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView



class CustomerAdapter(private var custList: ArrayList<CustomerModelClass>, private var listner:Select_listner) :
    RecyclerView.Adapter<CustomerAdapter.myViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_customer_view_list_card,
            parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = custList[position]
        holder.cust_name.text = currentItem.name.toString()
        holder.cust_email.text = currentItem.email.toString()
        holder.cust_balance.text = currentItem.balance.toString()
        holder.cust_extra.text = currentItem.extra.toString()
        holder.card_layout.setOnClickListener(){
            listner.onItemClicked(currentItem);
        }

    }

    override fun getItemCount(): Int {
        return custList.size
    }
    class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val cust_name : TextView = itemView.findViewById(R.id.custName)
        val cust_email : TextView = itemView.findViewById(R.id.custEmail)
        val cust_balance : TextView = itemView.findViewById(R.id.custbalance)
        val cust_extra : TextView = itemView.findViewById(R.id.custExtra)
        val card_layout : ConstraintLayout = itemView.findViewById(R.id.card_layout)

    }

}