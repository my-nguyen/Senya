package com.theandroidfactory.senya

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val repository = Repository()
    val attractionsLive = MutableLiveData<List<Attraction>>()
    val selectedAttractionLive = MutableLiveData<Attraction>()
    val selectedLocationLive = MutableLiveData<Attraction>()

    fun init(context: Context) = attractionsLive.postValue(repository.parseAttractions(context))

    fun onAttractionSelected(id: String) {
        val attraction = attractionsLive.value?.find { attraction ->
            attraction.id == id
        } ?: return

        selectedAttractionLive.postValue(attraction)
    }
}