package com.dedicated407.favoriteLiterature.Presentation.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dedicated407.favoriteLiterature.MainActivity
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.MyAccountViewModel
import com.dedicated407.favoriteLiterature.databinding.MyAccountFragmentBinding

class MyAccountFragment : Fragment() {
    private lateinit var mBinding: MyAccountFragmentBinding
    private lateinit var mViewModel: MyAccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("AuthKey", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("Login", null) == null)
            findNavController().navigate(
                MyAccountFragmentDirections.actionMyAccToAuth()
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = MyAccountFragmentBinding.inflate(layoutInflater, container, false)

        mBinding.btnLogOut.setOnClickListener {
            val signOutAcc = mViewModel.signOut(requireContext())
            if (signOutAcc != null) {
                signOutAcc.addOnCompleteListener {
                    view?.findNavController()?.navigate(
                        MyAccountFragmentDirections.actionMyAccToAuth()
                    )

                    val bottomNav = (requireActivity() as MainActivity)
                        .binding
                        .bottomNavigation

                    bottomNav.visibility = View.GONE
                }
            } else {
                Toast.makeText(context, "You can`t sign out", Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[MyAccountViewModel::class.java]

        return mBinding.root
    }

}