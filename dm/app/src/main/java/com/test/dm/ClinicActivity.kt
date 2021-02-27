package com.test.dm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clinic.*

class ClinicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic)



        info1.setOnClickListener{
            startActivity(Intent(this,Clinic1::class.java))
            finish()
        }


        info2.setOnClickListener{
            startActivity(Intent(this,Clinic2::class.java))
            finish()
        }
    }
}
