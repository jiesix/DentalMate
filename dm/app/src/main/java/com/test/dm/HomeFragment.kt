package com.test.dm


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import android.R.id
import android.util.Log
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentHome = HomeFragment()
            val args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }

    }

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        auth = FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser


        val rootView = inflater.inflate(R.layout.fragment_home,container,false)


        user?.let {
            val email = user.email

            val textView: TextView = rootView.findViewById(R.id.welcome) as TextView
            textView.text="Hi there, $email!"

            rootView.findClinic.setOnClickListener { view ->
                val intent = Intent(activity, ClinicActivity::class.java)
                startActivity(intent)
            }
            return rootView;
        }






        return rootView



    }



}



 /*       auth = FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val email = user.email
            welcome.setText("Welcome "+email+"!")
        }



        btnSign_out.setOnClickListener {view->
            Toast.makeText(getActivity(),"Loging Out!",Toast.LENGTH_SHORT).show()
            signOut()
        }
        */








   /* private fun signOut() {
        FirebaseAuth.getInstance().signOut()

        activity?.let{
            val intent = Intent (it, MainActivity::class.java)
            it.startActivity(intent)
        }
    }
*/


