package com.example.okayme.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.okayme.R
import com.example.okayme.adapter.ViewPagerAdapter
import com.example.okayme.db.ArticleDatabase
import com.example.okayme.fragments.ArticleFragment
import com.example.okayme.fragments.BreakingNewsFragment
import com.example.okayme.fragments.SavedNewsFragment
import com.example.okayme.fragments.SearchNewsFragment
import com.example.okayme.repository.NewsRepository

import com.google.android.material.tabs.TabLayout


class NewsActivity : AppCompatActivity() {
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        viewPager = findViewById(R.id.viewpager)


        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter!!.add(BreakingNewsFragment(), "News")
        viewPagerAdapter!!.add(ArticleFragment(), "Article")
        viewPagerAdapter!!.add(SavedNewsFragment(), "Save")
        viewPagerAdapter!!.add(SearchNewsFragment(), "Search")
        viewPager!!.setAdapter(viewPagerAdapter)
        tabLayout = findViewById(R.id.tab_layout)
        tabLayout!!.setupWithViewPager(viewPager)
    }
}
