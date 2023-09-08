package com.surbhi.sharedpreferencesapp

object SingletonObj {
    init {
        println("in singleton init")
    }
    val sharedPrefsClass: SharedPrefsClass = SharedPrefsClass()
}