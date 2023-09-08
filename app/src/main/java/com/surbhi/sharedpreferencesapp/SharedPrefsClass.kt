package com.surbhi.sharedpreferencesapp

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsClass {
     var sharedPreferences : SharedPreferences ?= null
     var editor : SharedPreferences.Editor? = null
    fun initSharedPreference(context: Context){
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(context.resources.getString(R.string.app_name),Context.MODE_PRIVATE)
            editor = sharedPreferences?.edit()
        }
    }
    fun saveString(key : String,value : String){
        editor?.putString(key,value)
        editor?.commit()
    }
    fun getString(name : String): String{
        return sharedPreferences?.getString(name,"#ffffff")?:"#ffffff"
    }
    fun saveInt(key : String,value : Int){
        editor?.putInt(key,value)
        editor?.commit()
    }
    fun getInt(name : String): Int{
        return sharedPreferences?.getInt(name,0)?:0
    }
    fun clear(){
                editor?.clear()
                editor?.commit()
    }
}
