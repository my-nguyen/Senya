package com.theandroidfactory.senya

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {
    private val repository = Repository()
    val attractionsLive = MutableLiveData<List<Attraction>>()
    val selectedAttractionLive = MutableLiveData<Attraction>()
    val selectedLocationLive = MutableLiveData<Attraction>()

    fun init(context: Context) {
        viewModelScope.launch {
            // uncomment to simulate loading with a progress bar
            // delay(5_000)
            attractionsLive.postValue(repository.parseAttractions(context))
        }
    }

    fun onAttractionSelected(id: String) {
        val attraction = attractionsLive.value?.find { attraction ->
            attraction.id == id
        } ?: return

        selectedAttractionLive.postValue(attraction)
    }
}