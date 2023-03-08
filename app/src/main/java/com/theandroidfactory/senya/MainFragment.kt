package com.theandroidfactory.senya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.theandroidfactory.senya.databinding.FragmentMainBinding
import layout.AttractionsAdapter

class MainFragment: BaseFragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AttractionsAdapter(attractions, object: AttractionsAdapter.OnClickListener {
            override fun onClick(position: Int) {
            }
        })
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(DividerItemDecoration(requireActivity(), RecyclerView.VERTICAL))
    }
}