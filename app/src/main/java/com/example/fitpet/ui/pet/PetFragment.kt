package com.example.fitpet.ui.pet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fitpet.FitPetApplication
import com.example.fitpet.R
import com.example.fitpet.data.PetViewModelFactory

class PetFragment : Fragment() {

    private lateinit var viewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val application = requireActivity().application as FitPetApplication
        val repository = application.petRepository
        val factory = PetViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[PetViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PetScreen(
                    viewModel = viewModel,
                    onNavigateToWarmups = {
                        findNavController().navigate(R.id.navigation_warmups)
                    }
                )
            }
        }
    }
}
