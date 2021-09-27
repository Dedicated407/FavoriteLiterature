package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.BookInfoViewModel
import com.dedicated407.favoriteLiterature.databinding.BookInfoFragmentBinding

class BookInfoFragment : Fragment() {
    private var mViewModel: BookInfoViewModel? = null
    private var mBinding: BookInfoFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = BookInfoFragmentBinding.inflate(layoutInflater, container, false)

        mViewModel = ViewModelProvider(this)[BookInfoViewModel::class.java]

        return mBinding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
        mViewModel = null
    }
}