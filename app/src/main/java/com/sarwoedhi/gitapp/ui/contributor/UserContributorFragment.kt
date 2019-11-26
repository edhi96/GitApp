package com.sarwoedhi.gitapp.ui.contributor


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarwoedhi.gitapp.R
import kotlinx.android.synthetic.main.fragment_user_contributor.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class UserContributorFragment : Fragment() {
    private lateinit var recycleContributes: RecyclerView
    private lateinit var mAdapter: ContributorAdapter
    private lateinit var mProgressBar: ProgressBar
    private val mViewModel: UserContributorViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_contributor, container, false)

        recycleContributes = view.rvContributors
        mProgressBar = view.progressBar

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            mAdapter = ContributorAdapter(it)
            loadData()
        }
    }

    private fun loadData() {
        val dataUsers = mViewModel.data
        dataUsers.observe(requireActivity(), Observer {
            if (!it.isNullOrEmpty()) {
                mAdapter.setUsersData(it)
                mProgressBar.visibility = View.GONE
                recycleContributes.adapter = mAdapter
                recycleContributes.layoutManager = LinearLayoutManager(requireContext())
                recycleContributes.setHasFixedSize(true)
            } else {
                mProgressBar.visibility = View.VISIBLE
            }
        })
    }
}
