package com.test.dm;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    TextView mFirstName, mLastName, mPhone;
    Button mCreate;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    public static ProfileFragment newInstance(int index) {
        ProfileFragment profileFragment= new ProfileFragment();
        Bundle args = new Bundle(1);
        args.putInt("someInt", index);
        profileFragment.setArguments(args);
        return profileFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);




        mFirstName = (TextView)view.findViewById(R.id.fname);
        mLastName  = (TextView)view.findViewById(R.id.lname);
        mPhone = (TextView)view.findViewById(R.id.phone);

        mCreate = (Button)view.findViewById(R.id.createProfile);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mFirstName.setText(documentSnapshot.getString("First Name"));
                mLastName.setText(documentSnapshot.getString("Last Name"));
                mPhone.setText(documentSnapshot.getString("Phone"));
            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateProfileActivity.class);
                startActivity(intent);

            }
        });




        return  view;
    }



}
