package com.example.fitpet.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.viewmodel.compose.viewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitpet.data.repository.SettingsRepository
import com.example.fitpet.databinding.FragmentSettingsBinding
import com.example.fitpet.ui.settings.SettingsViewModel

class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создаём репозиторий, передаём контекст
        val repository = SettingsRepository(requireContext())

        // Создаём ViewModel, передаём репозиторий
        viewModel = SettingsViewModel(repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SettingsScreen(viewModel)
            }
        }
    }


//    @SuppressLint("ViewModelConstructorInComposable")
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        Log.d("SettingsFragment", "Compose screen loaded")
//        return ComposeView(requireContext()).apply {
//            setContent {
//                val vm = SettingsViewModel(requireContext())
//                SettingsScreen(viewModel = vm)
//            }
//        }
//    }

}