package com.example.basic_banking_app_grip

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.w3c.dom.Text

class DatabaseHandler(context : Context) :
    SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)  {

    companion object{
        private const val DATABASE_VERSION =1
        private const val DATABASE_NAME = "CustomerDatabase"
        private const val TABLE_CONTACTS = "CustomerData"
        private const val key_ID = "_id"
        private const val key_Name = "name"
        private const val key_email = "email"
        private const val key_balance = "balance"
        private const val key_Extra = "extra"

//        private const val DATABASE_VERSION_H =1
//        private const val DATABASE_NAME_H = "HistoryDatabase"
//        private const val TABLE_CONTACTS_H = "historyData"
//        private const val key_ID_H = "_id"
//        private const val custA_name_H = "cust_A_Name"
//        private const val custB_name_H = "cust_B_Name"
//        private const val custA_prevbal_H = "cust_A_prev_bal"
//        private const val custB_prevBal_H = "cust_B_prev_bal"
//        private const val custA_newbal_H= "cust_A_new_ball"
//        private const val custB_newBal_H = "cust_B_new_bal"
//        private const val amount_transfer_H = "Amount"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + key_ID + " INTEGER PRIMARY KEY," + key_Name + " TEXT,"
                + key_email + " TEXT," + key_balance + " INTEGER," + key_Extra + " TEXT" +")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

//        val CREATE_CONTACTS_TABLE_H =("CREATE TABLE " + DatabaseHandler.TABLE_CONTACTS_H + "("
//                + DatabaseHandler.key_ID_H + " INTEGER PRIMARY KEY," + DatabaseHandler.custA_name_H + " TEXT,"
//                + DatabaseHandler.custA_newbal_H + " TEXT," + DatabaseHandler.custA_prevbal_H + " TEXT,"
//                + DatabaseHandler.custB_name_H + " TEXT," + DatabaseHandler.custB_prevBal_H + " TEXT,"
//                + DatabaseHandler.custB_newBal_H + " TEXT," + DatabaseHandler.amount_transfer_H + " TEXT" +")")
//        db?.execSQL(CREATE_CONTACTS_TABLE_H)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXIST " + TABLE_CONTACTS)
//        db!!.execSQL("DROP TABLE IF EXIST " + TABLE_CONTACTS_H)
        onCreate(db)
    }


    fun addCustomer(cust : CustomerModelClass ):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(key_ID,cust.id)
        contentValues.put(key_Name,cust.name)
        contentValues.put(key_email,cust.email)
        contentValues.put(key_balance,cust.balance)
        contentValues.put(key_Extra,cust.extra)

        val success = db.insert(TABLE_CONTACTS,null,contentValues)
        db.close()
        return success
    }
//    fun adddata(data : HistoryClassModel ):Long{
//        val db = this.writableDatabase
//
//        val contentValues = ContentValues()
//
//        contentValues.put(DatabaseHandler.key_ID_H,data.id)
//        contentValues.put(DatabaseHandler.custA_name_H,data.NameA)
//        contentValues.put(DatabaseHandler.custA_prevbal_H,data.prevBalA)
//        contentValues.put(DatabaseHandler.custA_newbal_H,data.newBalA)
//        contentValues.put(DatabaseHandler.custB_name_H,data.NameB)
//        contentValues.put(DatabaseHandler.custB_prevBal_H,data.prevBalB)
//        contentValues.put(DatabaseHandler.custB_newBal_H,data.newBalB)
//        contentValues.put(DatabaseHandler.amount_transfer_H,data.Amount)
//
//        val success = db.insert(DatabaseHandler.TABLE_CONTACTS_H,null,contentValues)
//        db.close()
//        return success
//    }


    @SuppressLint("Range")
    fun viewCustomer():ArrayList<CustomerModelClass>{
        val custlist:ArrayList<CustomerModelClass> = ArrayList<CustomerModelClass>()

        var selectQuary = "SELECT *FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(selectQuary,null)
        }catch (e:SQLException){
            db.execSQL(selectQuary)
            return ArrayList()
        }
        var id:Int
        var name:String
        var email:String
        var balance:Int
        var extra:String

        if(cursor.moveToFirst()){
            do{
                id=cursor.getInt(cursor.getColumnIndex(key_ID))
                name = cursor.getString(cursor.getColumnIndex(key_Name))
                email = cursor.getString(cursor.getColumnIndex(key_email))
                balance =cursor.getInt(cursor.getColumnIndex(key_balance))
                extra = cursor.getString(cursor.getColumnIndex(key_Extra))
                val cust = CustomerModelClass(id=id,name = name,email=email,balance = balance,extra = extra)
                custlist.add(cust)

            }while (cursor.moveToNext())
        }
        return custlist
    }

//    @SuppressLint("Range")
//    fun getalldata():ArrayList<HistoryClassModel>{
//        val arr :ArrayList<HistoryClassModel> = ArrayList()
//        var selectQuary_h = "SELECT *FROM ${DatabaseHandler.TABLE_CONTACTS_H}"
//        val db = this.readableDatabase
//        var cursor: Cursor? = null
//        try{
//            cursor = db.rawQuery(selectQuary_h,null)
//        }catch (e: SQLException){
//            db.execSQL(selectQuary_h)
//            return ArrayList()
//        }
//        var id_h : Int
//        var NameA: String
//        var NameB: String
//        var prevBalA: String
//        var prevBalB: String
//        var newBalA: String
//        var newBalB: String
//        var Amount: String
//        if(cursor.moveToFirst()){
//            do{
//                id_h=     cursor.getInt(cursor.getColumnIndex(DatabaseHandler.key_ID_H))
//                NameA =   cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//                NameB =   cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//                prevBalA= cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//                prevBalB= cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//                newBalA=  cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//                newBalB=  cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//                Amount=   cursor.getString(cursor.getColumnIndex(DatabaseHandler.custA_name_H))
//            }while (cursor.moveToNext())
//        }
//        return arr
//    }
//
    fun updateData(cust_id: Int? , cust_balance : Int?)
    {
        val db:SQLiteDatabase= this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(key_ID,cust_id)
        contentValues.put(key_balance,cust_balance)
        db.update(TABLE_CONTACTS,contentValues, key_ID + " = "+ cust_id,null)

    }

}