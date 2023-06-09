package com.theandroidfactory.senya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.theandroidfactory.senya.databinding.FragmentMainBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = AttractionsController(object : AttractionsController.OnClickListener {
            override fun onClick(id: String) {
                navController.navigate(R.id.action_mainFragment_to_detailFragment)
                viewModel.onAttractionSelected(id)
            }
        })

        val binding = FragmentMainBinding.bind(view)
        binding.recycler.setController(controller)
        // binding.recycler.addItemDecoration(DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL))

        // uncomment to simulate loading with a progress bar
        // adapter.isLoading = true
        viewModel.attractionsLive.observe(viewLifecycleOwner) { attractions ->
            controller.attractions = attractions
        }
    }
}