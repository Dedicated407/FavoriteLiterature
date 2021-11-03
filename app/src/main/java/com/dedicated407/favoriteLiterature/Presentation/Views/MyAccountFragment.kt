package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AddBookViewModel
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.MyAccountViewModel
import com.dedicated407.favoriteLiterature.databinding.MyAccountFragmentBinding

class MyAccountFragment : Fragment() {
    private lateinit var mBinding: MyAccountFragmentBinding
    private lateinit var mViewModel: MyAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MyAccountFragmentBinding.inflate(layoutInflater, container, false)

        mBinding.btnLogOut.setOnClickListener {
            if (mViewModel.signOut(requireContext())) {
                view?.findNavController()?.navigate(
                    MyAccountFragmentDirections.actionMyAccToAuth()
                )

                val bottomNav = (requireActivity() as MainActivity)
                    .binding
                    .bottomNavigation

                bottomNav.visibility = View.INVISIBLE

            } else {
                Toast.makeText(context, "You can`t sign out", Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[MyAccountViewModel::class.java]

        return mBinding.root
    }

}