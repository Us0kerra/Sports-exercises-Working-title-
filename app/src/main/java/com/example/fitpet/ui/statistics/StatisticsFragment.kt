package com.example.fitpet.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.fitpet.ui.warmups.WarmupsScreen
import com.example.fitpet.ui.warmups.WarmupsViewModel

class StatisticsFragment : Fragment() {

    private lateinit var viewModel: WarmupsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                WarmupsScreen(viewModel)
            }
        }
    }
}