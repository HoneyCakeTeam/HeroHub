package com.example.herohub.ui.category

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CategoryViewPagerAdapter(container: Fragment, private val fragmentList: List<Fragment>) :
    FragmentStateAdapter(container) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}