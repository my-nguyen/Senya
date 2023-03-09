package com.theandroidfactory.senya

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
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

    inner class EpoxyModel(val attraction: Attraction):
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

    override fun buildModels() {
        if (isLoading) {
            LoadingModel().id("loading_state").addTo(this)
            return
        }
        if (attractions.isEmpty()) {
            // todo show empty state
            return
        }
        attractions.forEach { attraction ->
            EpoxyModel(attraction).id(attraction.id).addTo(this)
        }
    }
}