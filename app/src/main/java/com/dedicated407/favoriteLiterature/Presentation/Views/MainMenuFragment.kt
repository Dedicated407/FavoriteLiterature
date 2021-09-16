package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dedicated407.favoriteLiterature.databinding.MainMenuFragmentBinding

class MainMenuFragment : Fragment() {
    private lateinit var binding: MainMenuFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MainMenuFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}