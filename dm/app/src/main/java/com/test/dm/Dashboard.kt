package com.test.dm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_dashboard.*


class Dashboard : AppCompatActivity() {



    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        /*
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(item.itemId)
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
         */
        loadFragment(item.itemId)
        true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.nav_home); // change to whichever id should be default
        }
    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        var fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.nav_home -> {
                HomeFragment.newInstance()
            }

            R.id.nav_appointment -> {
                AppointmentFragment.newInstance(1)
            }

            R.id.nav_profile -> {
                ProfileFragment.newInstance(1)
            }

            R.id.nav_settings -> {
                SettingsFragment.newInstance()
            }

            else -> {
                null
            }
        }




        // replace fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment, tag)
                .commit()
        }
    }
}


/*    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        auth = FirebaseAuth.getInstance()





    val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val email = user.email
            welcome.setText("Welcome "+email+"!")
        }

        btnSign_out.setOnClickListener {view->
            Toast.makeText(baseContext,"Loging Out!",Toast.LENGTH_SHORT).show()
            signOut()
        }
    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

}


 */