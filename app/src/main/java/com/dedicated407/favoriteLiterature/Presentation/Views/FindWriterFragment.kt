package com.dedicated407.favoriteLiterature.Presentation.Views

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dedicated407.favoriteLiterature.Domain.Model.Writer
import com.dedicated407.favoriteLiterature.Presentation.ViewModels.FindWriterViewModel
import com.dedicated407.favoriteLiterature.Presentation.Views.Adapters.WritersListAdapter
import com.dedicated407.favoriteLiterature.databinding.FindWriterFragmentBinding

class FindWriterFragment: Fragment() {
    private lateinit var mViewModel: FindWriterViewModel
    private lateinit var mBinding: FindWriterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FindWriterFragmentBinding.inflate(layoutInflater, container, false)
        mBinding.FindWriterList.RecyclerViewList.layoutManager = LinearLayoutManager(context)

        mBinding.findWriterSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    mViewModel.setWritersList(query)
                    return true
                }
            }
        )

        mViewModel = ViewModelProvider(this).get(FindWriterViewModel::class.java)

        mViewModel.getWritersList().observe(viewLifecycleOwner) { writersList: List<Writer> ->
            mBinding.FindWriterList.RecyclerViewList.adapter = WritersListAdapter(writersList)
        }
        return mBinding.root
    }

}