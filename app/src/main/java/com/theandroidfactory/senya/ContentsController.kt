package com.theandroidfactory.senya

import com.airbnb.epoxy.EpoxyController
import com.theandroidfactory.senya.databinding.ModelDescriptionBinding
import com.theandroidfactory.senya.databinding.ModelFactBinding
import com.theandroidfactory.senya.databinding.ModelHeaderFactsBinding
import com.theandroidfactory.senya.databinding.ModelMonthsToVisitBinding

class ContentsController(val attraction: Attraction): EpoxyController() {
    var isGridMode: Boolean = false
    lateinit var onLayoutChange: () -> Unit

    override fun buildModels() {
        DescriptionModel(attraction.description).id("description").addTo(this)

        FactsHeaderModel("${attraction.facts.size} Facts", isGridMode, onLayoutChange)
            .id("facts_header").addTo(this)

        attraction.facts.forEachIndexed { index, fact ->
            FactModel(fact).id("fact_$index").addTo(this)
        }

        MonthsToVisitModel(attraction.months_to_visit).id("months_to_visit").addTo(this)
    }

    data class MonthsToVisitModel(val monthsToVisit: String) :
        ViewBindingKotlinModel<ModelMonthsToVisitBinding>(R.layout.model_months_to_visit) {

        override fun ModelMonthsToVisitBinding.bind() {
            text.text = monthsToVisit
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class DescriptionModel(val description: String) :
        ViewBindingKotlinModel<ModelDescriptionBinding>(R.layout.model_description) {

        override fun ModelDescriptionBinding.bind() {
            text.text = description
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class FactsHeaderModel(val text: String, val isGridMode: Boolean, val onLayoutChange: () -> Unit) :
        ViewBindingKotlinModel<ModelHeaderFactsBinding>(R.layout.model_header_facts) {

        override fun ModelHeaderFactsBinding.bind() {
            facts.text = text
            toggle.setOnClickListener {
                onLayoutChange.invoke()
            }

            toggle.setImageResource(if (isGridMode) R.drawable.ic_list_view else R.drawable.ic_grid_view)
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class FactModel(val fact: String) : ViewBindingKotlinModel<ModelFactBinding>(R.layout.model_fact) {
        override fun ModelFactBinding.bind() {
            text.text = fact
        }
    }
}