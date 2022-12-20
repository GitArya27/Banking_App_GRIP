package com.example.basic_banking_app_grip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private var histList: ArrayList<HistoryClassModel>, private var listner: History) :
    RecyclerView.Adapter<HistoryAdapter.myViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_card,
            parent,false)
        return HistoryAdapter.myViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = histList[position]
        holder.amount.text = currentItem.Amount.toString()
        holder.BnowBal.text = currentItem.newBalB.toString()
        holder.custB_name.text = currentItem.NameB.toString()
        holder.custA_name.text = currentItem.NameA.toString()
        holder.AnowBal.text = currentItem.newBalA.toString()
        holder.Aprev_bal.text = currentItem.prevBalA.toString()
        holder.Bprev_bal.text = currentItem.prevBalB.toString()

    }

    override fun getItemCount(): Int {
        return histList.size
    }
    class myViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val custA_name : TextView = itemView.findViewById(R.id.Aname)
        val custB_name : TextView = itemView.findViewById(R.id.Bname)
        val Aprev_bal : TextView = itemView.findViewById(R.id.Aprevbal)
        val Bprev_bal : TextView = itemView.findViewById(R.id.BprevBal)
        val AnowBal : TextView = itemView.findViewById(R.id.AnowBal)
        val BnowBal : TextView = itemView.findViewById(R.id.Bnowbal)
        val amount : TextView = itemView.findViewById(R.id.amount)
    }
}