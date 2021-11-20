package com.example.myspacex.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.myspacex.R
import com.example.myspacex.presentation.LaunchDetailsViewModel
import androidx.lifecycle.Observer
import com.example.myspacex.presentation.LaunchUi
import com.example.myspacex.presentation.adapter.LaunchDetailsAdapter

import java.util.*

class LaunchDetailsFragment : BaseFragment(R.layout.fragment_launch_details) {

    companion object {
        const val EXTRA_YEAR = "extra_year"
        const val EXTRA_POSITION = "extra_position"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val position = arguments?.getInt(EXTRA_POSITION, 0)
        val year =
            arguments?.getString(EXTRA_YEAR, Calendar.getInstance().get(Calendar.YEAR).toString())
        recyclerView.setHasFixedSize(true)
        val viewModel = activity?.run {
            ViewModelProviders.of(this).get(LaunchDetailsViewModel::class.java)
        }
        viewModel?.let { model ->
            model.launchData.observe(viewLifecycleOwner) {
                recyclerView.adapter = LaunchDetailsAdapter(it)
            }
            model.showData(year!!, position)
        }
    }
}