package com.example.myspacex.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myspacex.R

class ServiceUnavailable : BaseFragment(R.layout.fragment_service_unavailable) {
    private var retryListener: RetryListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        retryListener = context as RetryListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retryButton = view.findViewById<Button>(R.id.retryButton)
        retryButton.setOnClickListener {
            retryListener?.retry()
        }
    }

    override fun onDetach() {
        super.onDetach()
        retryListener = null
    }
}