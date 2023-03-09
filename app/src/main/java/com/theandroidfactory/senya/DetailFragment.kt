package com.theandroidfactory.senya

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.LinearSnapHelper
import com.squareup.picasso.Picasso
import com.theandroidfactory.senya.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment() {
    lateinit var binding: FragmentDetailBinding

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

        viewModel.selectedAttractionLive.observe(viewLifecycleOwner) { attraction ->
            binding.title.text = attraction.title
            binding.description.text = attraction.description
            binding.monthsToVisit.text = attraction.months_to_visit
            binding.headerRecycler.setControllerAndBuildModels(
                HeaderController(attraction.image_urls))
            LinearSnapHelper().attachToRecyclerView(binding.headerRecycler)
            binding.indicator.attachToRecyclerView(binding.headerRecycler)

            binding.facts.text = "${attraction.facts.size} facts"
            binding.facts.setOnClickListener {
                val builder = StringBuilder()
                attraction.facts.forEach {
                    // add bullet points
                    builder.append("\u2022 $it").append("\n\n")
                }
                val string = builder.toString()
                val message = string.substring(0, string.lastIndexOf("\n"))
                // with this theme, the dialog won't show the OK and Cancel buttons
                // AlertDialog.Builder(requireContext(), R.style.ThemeDialog)
                AlertDialog.Builder(requireContext())
                    .setTitle("${attraction.title} Facts")
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, which ->
                        // run positive code
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        // run negative code
                    }
                    // prevent dismissing the dialog when clicking outside the dialog
                    .setCancelable(false)
                    .show()
            }
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_location -> {
                        val attraction = viewModel.selectedAttractionLive.value ?: return true
                        viewModel.selectedLocationLive.postValue(attraction)
                        true
                    }
                    else -> false
                }
            }
        })
    }
}