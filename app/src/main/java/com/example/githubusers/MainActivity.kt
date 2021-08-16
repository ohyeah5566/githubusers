package com.example.githubusers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubusers.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabList = listOf("users","mine")
        binding.pager.adapter = object:FragmentStateAdapter(this){
            val hashMap = mapOf(
                0 to UserListFragment(),
                1 to MineFragment()
            )
            override fun getItemCount(): Int {
                return tabList.size
            }

            override fun createFragment(position: Int): Fragment {
                return hashMap[position]!!
            }
        }

        TabLayoutMediator(binding.tabview, binding.pager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }
}