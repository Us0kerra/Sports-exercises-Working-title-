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
import com.example.fitpet.databinding.FragmentSettingsBinding
import com.example.fitpet.ui.settings.SettingsViewModel

class SettingsFragment : Fragment() {

//    private var _binding: FragmentSettingsBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!

    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("SettingsFragment", "Compose screen loaded")
        return ComposeView(requireContext()).apply {
            setContent {
                val vm = SettingsViewModel(requireContext())
                SettingsScreen(viewModel = vm)
            }
        }


        //TODO: закоменчено пока пробую compose
//        val settingsViewModel =
//            ViewModelProvider(this).get(SettingsViewModel::class.java)
//
//        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textSettings
//        settingsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}