package com.example.gallery.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.gallery.VolleySingleton
import com.example.gallery.bean.PhotoItem
import com.example.gallery.bean.Pixabay
import com.google.gson.Gson

class GalleyViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive = MutableLiveData<List<PhotoItem>>()
    val photoListLive:LiveData<List<PhotoItem>>
    get() = _photoListLive

    fun fetchData(){
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _photoListLive.value = Gson().fromJson(it,Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener {
                Log.d("ss","出错了${it.message}")
            }

        )
        VolleySingleton.getInstance(getApplication()).requQueue.add(stringRequest)
    }

    private fun getUrl():String{
        return "https://pixabay.com/api/?key=14731825-14955c3e62f03753a080259b2&q=${keyWords.random()}"
    }

    private val keyWords = arrayOf("cat","dog","car","phone","girl")
}