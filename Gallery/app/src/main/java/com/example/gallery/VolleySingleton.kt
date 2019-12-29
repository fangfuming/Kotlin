package com.example.gallery

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton private constructor(context: Context){
    companion object {
        //单例
        private var INSTANCE:VolleySingleton?=null
        fun getInstance(context: Context) :VolleySingleton{
            return INSTANCE ?: synchronized(this) {
                VolleySingleton(context).also { INSTANCE = it }
            }
        }
    }

    val requQueue:RequestQueue by lazy { Volley.newRequestQueue(context.applicationContext) }
}