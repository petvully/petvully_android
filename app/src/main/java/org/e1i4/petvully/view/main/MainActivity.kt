package org.e1i4.petvully.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.e1i4.petvully.R
import org.e1i4.petvully.base.BaseActivity
import org.e1i4.petvully.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    val homeFragment = HomeFragment()
    val adoptionFragment = AdoptionFragment()
    val donationFragment = DonationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFragments()
        setNavigation()
    }

    private fun initFragments(){
        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_fragment_container, homeFragment)
            add(R.id.main_fragment_container, adoptionFragment)
            add(R.id.main_fragment_container, donationFragment)
            commit()
        }
        replaceFragment(homeFragment)
    }
    private fun setNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_main_bottom_home -> replaceFragment(homeFragment)
                R.id.menu_main_bottom_adoption -> replaceFragment(adoptionFragment)
                R.id.menu_main_bottom_donation -> replaceFragment(donationFragment)
                else -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
    }
    private fun replaceFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            hide(homeFragment)
            hide(adoptionFragment)
            hide(donationFragment)
            show(fragment)
            commit()
        }
    }

}