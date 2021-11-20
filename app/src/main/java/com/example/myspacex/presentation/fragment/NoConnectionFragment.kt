package com.example.myspacex.presentation.fragment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myspacex.R

class NoConnectionFragment : BaseFragment(R.layout.fragment_no_connection) {
    private var retryListener: RetryListener? = null
    private var receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            retryListener?.retry()
        }
    }

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

        val intentFilter = IntentFilter().apply {
            addAction("android.net.conn.CONNECTIVITY_CHANGE")
        }
        context?.registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(receiver)
    }

    override fun onDetach() {
        super.onDetach()
        retryListener = null
    }
}