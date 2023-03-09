package com.theandroidfactory.senya

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.theandroidfactory.senya.databinding.HeaderBinding
import com.theandroidfactory.senya.databinding.ItemAttractionBinding

class AttractionsAdapter(val listener: OnClickListener): EpoxyController() {
    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var attractions = listOf<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    interface OnClickListener {
        fun onClick(id: String)
    }

    inner class AttractionModel(val attraction: Attraction):
        ViewBindingKotlinModel<ItemAttractionBinding>(R.layout.item_attraction) {
        override fun ItemAttractionBinding.bind() {
            title.text = attraction.title
            monthsToVisit.text = attraction.months_to_visit
            Picasso.get().load(attraction.image_urls[0]).into(header)
            root.setOnClickListener {
                listener.onClick(attraction.id)
            }
        }
    }

    data class HeaderModel(val text: String): ViewBindingKotlinModel<HeaderBinding>(R.layout.header) {
        override fun HeaderBinding.bind() {
            header.text = text
        }
    }

    override fun buildModels() {
        if (isLoading) {
            LoadingModel().id("loading_state").addTo(this)
            return
        }
        if (attractions.isEmpty()) {
            // todo show empty state
            return
        }

        HeaderModel("Recently Viewed").id("group_1").addTo(this)
        val group1 = attractions.filter { it.title.startsWith("S") || it.title.startsWith("D") }
        group1.forEach { attraction ->
            AttractionModel(attraction).id(attraction.id).addTo(this)
        }

        HeaderModel("All Attractions").id("group_2").addTo(this)
        attractions.forEach { attraction ->
            AttractionModel(attraction).id(attraction.id).addTo(this)
        }
    }
}