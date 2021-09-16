package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dedicated407.favoriteLiterature.databinding.AddBookFragmentBinding

class AddBookFragment : Fragment() {
    private lateinit var binding: AddBookFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AddBookFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


}