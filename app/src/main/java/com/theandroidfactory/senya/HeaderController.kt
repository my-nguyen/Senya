package com.theandroidfactory.senya

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.theandroidfactory.senya.databinding.ModelHeaderImageBinding

class HeaderController(val imageUrls: List<String>): EpoxyController() {
    override fun buildModels() {
        imageUrls.forEachIndexed { index, url ->
            HeaderModel(url).id(index).addTo(this)
        }
    }

    inner class HeaderModel(val imageUrl: String):
        ViewBindingKotlinModel<ModelHeaderImageBinding>(R.layout.model_header_image) {
        override fun ModelHeaderImageBinding.bind() {
            Picasso.get().load(imageUrl).into(image)
        }
    }
}