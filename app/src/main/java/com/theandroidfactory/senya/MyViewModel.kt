package com.theandroidfactory.senya

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val repository = Repository()
    val attractionsLiveData = MutableLiveData<List<Attraction>>()

    fun init(context: Context) {
        attractionsLiveData.postValue(repository.parseAttractions(context))
    }
}