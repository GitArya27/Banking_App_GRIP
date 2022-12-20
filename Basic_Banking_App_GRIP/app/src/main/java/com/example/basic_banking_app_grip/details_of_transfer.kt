package com.example.basic_banking_app_grip



data class details_of_transfer(val from_cust_id: Int ,val to_cust_Id:Int, val from_cust_balance:Int,val to_cust_balance :Int , val amount:Int)
{

    var id_from_cust = from_cust_id
    var id_to_cust = to_cust_Id
    var balance_from_cust = from_cust_balance
    var balance_to_cust = to_cust_balance
    val to_transfer = amount


}
