package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.AddBookViewModel
import com.dedicated407.favoriteLiterature.R
import com.dedicated407.favoriteLiterature.databinding.AddBookFragmentBinding

class AddBookFragment : Fragment() {
    private lateinit var mViewModel: AddBookViewModel
    private lateinit var mBinding: AddBookFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = AddBookFragmentBinding.inflate(layoutInflater, container, false)

        mBinding.btnShowAllBooks.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.action_addBook_to_listBooks)
        }

        mBinding.btnAddBook.setOnClickListener { v ->
            if (mBinding.inputBookName.text.toString().isNotEmpty()) {
                mViewModel.addBook(
                    mBinding.inputBookName.text.toString(),
                    User("", mBinding.inputAuthorName.text.toString()),
                    mBinding.inputBookDescription.text.toString()
                )
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(v).navigate(R.id.action_addBook_to_listBooks)
            } else {
                Toast.makeText(context, "You have not entered all the data", Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel = ViewModelProvider(this)[AddBookViewModel::class.java]

        return mBinding.root
    }

}