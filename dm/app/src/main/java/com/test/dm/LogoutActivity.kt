package com.test.dm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)



        auth = FirebaseAuth.getInstance()

        btnSign_out.setOnClickListener {view->
            Toast.makeText(baseContext,"Loging Out!", Toast.LENGTH_SHORT).show()
            signOut()
        }


        btnBack.setOnClickListener {view->
            startActivity(Intent(this,Dashboard::class.java))
        }


    }

    private fun signOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
