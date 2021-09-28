package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.BookInfoViewModel
import com.dedicated407.favoriteLiterature.databinding.BookInfoFragmentBinding

class BookInfoFragment : Fragment() {
    private var mViewModel: BookInfoViewModel? = null
    private var mBinding: BookInfoFragmentBinding? = null
    private lateinit var id: String
    companion object {
        public const val BOOK_ID_ARG: String = "currentBook"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString(BOOK_ID_ARG)!!

        mViewModel = ViewModelProvider(this)[BookInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = BookInfoFragmentBinding.inflate(layoutInflater, container, false)

        mBinding?.bookInfoBack?.setOnClickListener{ v ->
            Navigation.findNavController(v).popBackStack()
        }

        mViewModel?.getBook(id)?.observe(viewLifecycleOwner) { book ->
            mBinding!!.bookInfoName.text = book.name
            mBinding!!.bookInfoAuthor.text = book.author.toString()
            mBinding!!.bookInfoDescription.text = book.description
        }

        return mBinding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
        mViewModel = null
    }
}