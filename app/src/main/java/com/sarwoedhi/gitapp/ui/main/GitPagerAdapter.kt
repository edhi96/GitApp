package com.sarwoedhi.gitapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sarwoedhi.gitapp.ui.contributor.UserContributorFragment
import com.sarwoedhi.gitapp.ui.search.SearchRepositoriesFragment

class GitPagerAdapter(
    fm: FragmentManager, private val userMode: String,
    private val repoMode: String
) : FragmentPagerAdapter(fm) {
    private val pagesList = listOf(
        UserContributorFragment(),
        SearchRepositoriesFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pagesList[p0]
    }

    override fun getCount(): Int {
        return pagesList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> userMode
            else -> return repoMode
        }
    }

}