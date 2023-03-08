package com.theandroidfactory.senya

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.theandroidfactory.senya.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment() {
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
                .setPositiveButton("OK") { dialog, _ ->
                    // run positive code
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    // run negative code
                }
                // prevent dismissing the dialog when clicking outside the dialog
                .setCancelable(false)
                .show()
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.menu_location -> {
                        // reference: https://developers.google.com/maps/documentation/urls/android-intents#kotlin_2
                        // Creates an Intent that will load a map of San Francisco
                        val uri =
                            Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
                        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(mapIntent)
                        true
                    }
                    else -> false
                }
            }
        })
    }
}