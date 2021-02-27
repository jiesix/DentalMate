package com.test.dm


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_settings.view.*

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    companion object {
        fun newInstance(): SettingsFragment {
            val fragmentSettings = SettingsFragment()
            val args = Bundle()
            fragmentSettings.arguments = args
            return fragmentSettings
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view: View = inflater!!.inflate(R.layout.fragment_settings, container, false)


        view.logOut.setOnClickListener { view ->
            val intent = Intent(activity, LogoutActivity::class.java)
            startActivity(intent)
        }

        return view

    }




}
