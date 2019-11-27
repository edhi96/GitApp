package com.sarwoedhi.gitapp.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarwoedhi.gitapp.R
import com.sarwoedhi.gitapp.data.models.SearchEntity
import kotlinx.android.synthetic.main.fragment_search_repositories.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchRepositoriesFragment : Fragment() {

    private val searchViewModel: SearchRepositoriesViewModel by sharedViewModel()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchView: SearchView
    private lateinit var mProgressBarSearch: ProgressBar
    private lateinit var recyclerSearch: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_repositories, container, false)
        recyclerSearch = view.rvResultSearchList
        mProgressBarSearch = view.progressBarSearch
        mProgressBarSearch.visibility = View.INVISIBLE
        searchAdapter = SearchAdapter(requireContext())
        searchView = view.searchRepos

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                if (query.length > 2) {
                    mProgressBarSearch.visibility = View.VISIBLE
                    loadDataSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    searchAdapter.resetResults()
                    recyclerSearch.adapter = searchAdapter
                    mProgressBarSearch.visibility = View.GONE
                }
                return false
            }
        })
        recyclerSearch.adapter = searchAdapter
        recyclerSearch.layoutManager = LinearLayoutManager(requireContext())
        recyclerSearch.setHasFixedSize(true)
        return view
    }


    private fun loadDataSearch(q: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val dataSearch = searchViewModel.getResultSearch(q)
            withContext(Dispatchers.Main) {
                dataSearch.observe(requireActivity(), getResult)
            }
        }
    }

    private val getResult = Observer<List<SearchEntity>> {
        if (it != null) {
            searchAdapter.setResults(it)
            searchAdapter.notifyDataSetChanged()
            mProgressBarSearch.visibility = View.GONE
        } else {
            mProgressBarSearch.visibility = View.VISIBLE
        }

    }

}