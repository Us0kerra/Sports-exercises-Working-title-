package com.example.fitpet.ui.warmups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.fitpet.data.warmups.WarmupRepository

class WarmupsFragment : Fragment() {

    private lateinit var viewModel: WarmupsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создаём репозиторий, передаём контекст
        val repository = WarmupRepository()

        // Создаём ViewModel, передаём репозиторий
        viewModel = WarmupsViewModel()
    }

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