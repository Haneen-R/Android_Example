package com.example.myapplication

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.adapters.TaskAdapter
import com.example.myapplication.fragments.PageOneFragment
import com.example.myapplication.fragments.PageThreeFragment
import com.example.myapplication.fragments.PageTwoFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {
    val firstFragment = PageOneFragment()
    val secondFragment = PageTwoFragment()
    val thirdFragment = PageThreeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val toolBar:Toolbar=findViewById(R.id.topAppBar)
        setSupportActionBar(toolBar)
        setCurrentFragment(firstFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.page_1 -> setCurrentFragment(firstFragment)
            R.id.page_2 -> setCurrentFragment(secondFragment)
            R.id.page_3 -> setCurrentFragment(thirdFragment)
        }
        return true
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }


}