package com.test.dm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity {

    EditText mFirstName, mLastName, mPhone;
    Button mSaveBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mFirstName = findViewById(R.id.fname);
        mLastName = findViewById(R.id.lname);
        mPhone = findViewById(R.id.phone);
        mSaveBtn = findViewById(R.id.btnSave);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String phone = mPhone.getText().toString();


                if (TextUtils.isEmpty(firstName)) {
                    mFirstName.setError("First Name is Required");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    mLastName.setError("Last Name is Required");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    mPhone.setError("Phone Number is Required");
                    return;
                } else {
                    Toast.makeText(CreateProfileActivity.this, "Profile Created", Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("First Name", firstName);
                    user.put("Last Name", lastName);
                    user.put("Phone", phone);
                    documentReference.set(user);

                }

                Intent intent = new Intent(CreateProfileActivity.this, Dashboard.class);
                startActivity(intent);
            }

        });



    }
}
