package com.example.basic_banking_app_grip

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HistoryDatabaseHandler(context : Context):
    SQLiteOpenHelper(context,DATABASE_NAME_H,null,DATABASE_VERSION_H) {

    companion object{
        private const val DATABASE_VERSION_H =1
        private const val DATABASE_NAME_H = "HistoryDatabase"
        private const val TABLE_CONTACTS_H = "historyData"
        private const val key_ID_H = "_id"
        private const val custA_name_H = "cust_A_Name"
        private const val custB_name_H = "cust_B_Name"
        private const val custA_prevbal_H = "cust_A_prev_bal"
        private const val custB_prevBal_H = "cust_B_prev_bal"
        private const val custA_newbal_H= "cust_A_new_ball"
        private const val custB_newBal_H = "cust_B_new_bal"
        private const val amount_transfer_H = "Amount"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE_H =("CREATE TABLE " + HistoryDatabaseHandler.TABLE_CONTACTS_H + "("
                + HistoryDatabaseHandler.key_ID_H + " INTEGER PRIMARY KEY," + HistoryDatabaseHandler.custA_name_H + " TEXT,"
                + HistoryDatabaseHandler.custA_newbal_H + " TEXT," + HistoryDatabaseHandler.custA_prevbal_H + " TEXT,"
                + HistoryDatabaseHandler.custB_name_H + " TEXT," + HistoryDatabaseHandler.custB_prevBal_H + " TEXT,"
                + HistoryDatabaseHandler.custB_newBal_H + " TEXT," + HistoryDatabaseHandler.amount_transfer_H + " TEXT" +")")
        db?.execSQL(CREATE_CONTACTS_TABLE_H)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME_H")
        onCreate(db)
    }

    fun adddata(data : HistoryClassModel ):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(HistoryDatabaseHandler.key_ID_H,data.id)
        contentValues.put(HistoryDatabaseHandler.custA_name_H,data.NameA)
        contentValues.put(HistoryDatabaseHandler.custA_prevbal_H,data.prevBalA)
        contentValues.put(HistoryDatabaseHandler.custA_newbal_H,data.newBalA)
        contentValues.put(HistoryDatabaseHandler.custB_name_H,data.NameB)
        contentValues.put(HistoryDatabaseHandler.custB_prevBal_H,data.prevBalB)
        contentValues.put(HistoryDatabaseHandler.custB_newBal_H,data.newBalB)
        contentValues.put(HistoryDatabaseHandler.amount_transfer_H,data.Amount)

        val success = db.insert(TABLE_CONTACTS_H,null,contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getalldata():ArrayList<HistoryClassModel>{
        val arr :ArrayList<HistoryClassModel> = ArrayList<HistoryClassModel>()

        var selectQuary = "SELECT *FROM ${TABLE_CONTACTS_H}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuary,null)
        }catch (e: SQLException){
            db.execSQL(selectQuary)
            return ArrayList()
        }
        var id : Int
        var NameA: String
        var NameB: String
        var prevBalA: String
        var prevBalB: String
        var newBalA: String
        var newBalB: String
        var Amount: String
        if(cursor.moveToFirst()){
            do{
                id=cursor.getInt(cursor.getColumnIndex(key_ID_H))
                NameA = cursor.getString(cursor.getColumnIndex(custA_name_H))
                NameB = cursor.getString(cursor.getColumnIndex(custB_name_H))
                prevBalA= cursor.getString(cursor.getColumnIndex(custA_prevbal_H))
                prevBalB= cursor.getString(cursor.getColumnIndex(custB_prevBal_H))
                newBalA=cursor.getString(cursor.getColumnIndex(custA_newbal_H))
                newBalB=cursor.getString(cursor.getColumnIndex(custB_newBal_H))
                Amount=cursor.getString(cursor.getColumnIndex(amount_transfer_H))
                val hist = HistoryClassModel(id,NameA,NameB,prevBalA,prevBalB,newBalA,newBalB,Amount)
                arr.add(hist)
            }while (cursor.moveToNext())
        }
        return arr
    }
}