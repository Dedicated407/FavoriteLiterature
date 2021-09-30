package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.BooksListViewModel
import com.dedicated407.favoriteLiterature.Presentation.Views.Adapters.BooksListAdapter
import com.dedicated407.favoriteLiterature.databinding.ListFragmentBinding

class BooksListFragment : Fragment() {

    private lateinit var mViewModel: BooksListViewModel
    private lateinit var mBinding: ListFragmentBinding

    fun newInstance(): BooksListFragment {
        return BooksListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = ListFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.list.layoutManager = LinearLayoutManager(context)

        mBinding.back.setOnClickListener{ v ->
            Navigation.findNavController(v).popBackStack()
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                mViewModel.deleteBook((mBinding.list.adapter as BooksListAdapter).getData()[position])
            }
        }).attachToRecyclerView(mBinding.list)

        mViewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)
        mViewModel.getBooksList().observe(viewLifecycleOwner) { bookList: List<Book> ->
            mBinding.list.adapter = BooksListAdapter(bookList)
        }

        return mBinding.root
    }

}