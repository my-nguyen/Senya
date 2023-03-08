package com.theandroidfactory.senya

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Repository {
    private fun parseAttractions(context: Context): List<Attraction> {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val adapter = moshi.adapter(Attractions::class.java)
        val text = context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        return adapter.fromJson(text)!!.attractions
    }
}