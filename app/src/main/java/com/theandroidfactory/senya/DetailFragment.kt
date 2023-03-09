package com.theandroidfactory.senya

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.theandroidfactory.senya.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment() {
    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedAttractionLive.observe(viewLifecycleOwner) { attraction ->
            binding.title.text = attraction.title
            binding.headerRecycler.setControllerAndBuildModels(
                HeaderController(attraction.image_urls))
            LinearSnapHelper().attachToRecyclerView(binding.headerRecycler)
            binding.indicator.attachToRecyclerView(binding.headerRecycler)

            var isGridMode: Boolean = binding.contentsRecycler.layoutManager is GridLayoutManager
            val contentsController = ContentsController(attraction)
            contentsController.isGridMode = isGridMode
            contentsController.onLayoutChange = {
                binding.contentsRecycler.layoutManager =
                    if (isGridMode) LinearLayoutManager(requireContext()) else GridLayoutManager(requireContext(), 2)

                isGridMode = !isGridMode
                contentsController.isGridMode = isGridMode
                contentsController.requestModelBuild()
            }
            binding.contentsRecycler.setControllerAndBuildModels(contentsController)
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