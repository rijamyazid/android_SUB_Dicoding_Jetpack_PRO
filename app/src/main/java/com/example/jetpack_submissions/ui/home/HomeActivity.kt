package com.example.jetpack_submissions.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpack_submissions.R
import com.example.jetpack_submissions.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind view
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Memasang ViewPager di Activity
        val homePagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = homePagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f

    }

}