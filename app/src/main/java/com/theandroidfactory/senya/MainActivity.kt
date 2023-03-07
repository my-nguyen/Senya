package com.theandroidfactory.senya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.theandroidfactory.senya.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}