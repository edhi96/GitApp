package com.sarwoedhi.gitapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sarwoedhi.gitapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter =
            GitPagerAdapter(
                supportFragmentManager,
                "Users",
                "Repositories"
            )
        viewPagerGit.adapter = fragmentAdapter
        tabLayoutGit.setupWithViewPager(viewPagerGit)

    }
}
