package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.BooksListViewModel
import com.dedicated407.favoriteLiterature.Presentation.Views.Adapters.BooksListAdapter
import com.dedicated407.favoriteLiterature.databinding.ListFragmentBinding

class BooksListFragment : Fragment() {

    private lateinit var mViewModel: BooksListViewModel
    private lateinit var mBinding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = ListFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.RecyclerViewList.layoutManager = LinearLayoutManager(context)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) { }
        }).attachToRecyclerView(mBinding.RecyclerViewList)

        mViewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)
        mViewModel.getAllBooks().observe(viewLifecycleOwner) { bookList: List<BookListViewDTO> ->
            mBinding.RecyclerViewList.adapter = BooksListAdapter(bookList)
        }

        return mBinding.root
    }

}