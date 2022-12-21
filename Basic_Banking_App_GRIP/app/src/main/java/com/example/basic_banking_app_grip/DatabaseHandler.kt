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
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + key_ID + " INTEGER PRIMARY KEY," + key_Name + " TEXT,"
                + key_email + " TEXT," + key_balance + " INTEGER," + key_Extra + " TEXT" +")")
        db?.execSQL(CREATE_CONTACTS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXIST " + TABLE_CONTACTS)
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

    fun updateData(cust_id: Int? , cust_balance : Int?)
    {
        val db:SQLiteDatabase= this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(key_ID,cust_id)
        contentValues.put(key_balance,cust_balance)
        db.update(TABLE_CONTACTS,contentValues, key_ID + " = "+ cust_id,null)

    }

}