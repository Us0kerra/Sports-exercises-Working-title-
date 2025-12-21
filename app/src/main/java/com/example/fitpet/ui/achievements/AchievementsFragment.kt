package com.example.fitpet.ui.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitpet.data.repository.SettingsRepository
import com.example.fitpet.databinding.FragmentAchievementsBinding
import com.example.fitpet.ui.settings.SettingsScreen
import com.example.fitpet.ui.settings.SettingsViewModel

class AchievementsFragment : Fragment() {

    private lateinit var viewModel: AchievementsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создаём репозиторий, передаём контекст
//        val repository = SettingsRepository(requireContext())

        // Создаём ViewModel, передаём репозиторий
//        viewModel = SettingsViewModel(repository)
        viewModel = AchievementsViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AchievementsScreen(viewModel)
            }
        }
    }
}