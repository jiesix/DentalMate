package com.test.dm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Clinic2 extends AppCompatActivity {

    TextView mClinicName;
    Button mButton;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic2);

        mClinicName = findViewById(R.id.clinicName);
        mButton = findViewById(R.id.book2);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String clinicName = mClinicName.getText().toString();
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("Appointments").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("Clinic", clinicName);
                documentReference.set(user);


                Intent intent = new Intent(Clinic2.this, GetAppointment.class);
                startActivity(intent);

            }




        });




    }
}
