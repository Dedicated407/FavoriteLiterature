package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dedicated407.favoriteLiterature.databinding.MyAccountFragmentBinding

class MyAccountFragment: Fragment() {
    private lateinit var mBinding: MyAccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MyAccountFragmentBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

}