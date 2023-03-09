package layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.theandroidfactory.senya.Attraction
import com.theandroidfactory.senya.R
import com.theandroidfactory.senya.ViewBindingKotlinModel
import com.theandroidfactory.senya.databinding.ItemAttractionBinding

class AttractionsAdapter(val attractions: List<Attraction>, val listener: OnClickListener): RecyclerView.Adapter<AttractionsAdapter.ViewHolder>() {
    interface OnClickListener {
        fun onClick(id: String)
    }

    inner class ViewHolder(val binding: ItemAttractionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(attraction: Attraction) {
            binding.title.text = attraction.title
            binding.monthsToVisit.text = attraction.months_to_visit
            Picasso.get().load(attraction.image_urls[0]).into(binding.header)
            binding.root.setOnClickListener {
                listener.onClick(attraction.id)
            }
        }
    }

    data class EpoxyModel(val attraction: Attraction, val onClick: (String) -> Unit):
        ViewBindingKotlinModel<ItemAttractionBinding>(R.layout.item_attraction) {
        override fun ItemAttractionBinding.bind() {
            title.text = attraction.title
            monthsToVisit.text = attraction.months_to_visit
            Picasso.get().load(attraction.image_urls[0]).into(header)
            root.setOnClickListener {
                // listener.onClick(attraction.id)
                onClick(attraction.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAttractionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = attractions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(attractions[position])
}