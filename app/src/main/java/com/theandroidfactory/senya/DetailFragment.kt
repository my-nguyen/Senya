package com.theandroidfactory.senya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.theandroidfactory.senya.databinding.FragmentDetailBinding

class DetailFragment: BaseFragment() {
    lateinit var binding: FragmentDetailBinding
    private val safeArgs: DetailFragmentArgs by navArgs()
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.id }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = attraction.title
        binding.description.text = attraction.description
        binding.monthsToVisit.text = attraction.months_to_visit
        Picasso.get().load(attraction.image_urls[0]).into(binding.header)
        binding.facts.text = "${attraction.facts.size} facts"
        binding.facts.setOnClickListener {

        }
    }
}