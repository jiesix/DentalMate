package com.test.dmdentist;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentFragment extends Fragment {


    TextView clinic, date, time;

    String uid;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;





    public static AppointmentFragment newInstance(int index) {
        AppointmentFragment appointmentFragment = new AppointmentFragment();
        Bundle args = new Bundle(1);
        args.putInt("someInt", index);
        appointmentFragment.setArguments(args);
        return appointmentFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_appointment, container, false);


        clinic = (TextView)view.findViewById(R.id.clinicView);
        date = (TextView)view.findViewById(R.id.dateTextView);
        time = (TextView)view.findViewById(R.id.timeTextView);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        uid = fAuth.getCurrentUser().getUid();


        final DocumentReference documentReference = fStore.collection("Appointments").document(uid);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                clinic.setText(documentSnapshot.getString("Clinic"));
                date.setText(documentSnapshot.getString("Date"));
                time.setText(documentSnapshot.getString("Time"));
            }
        });


        Button cancel = (Button)view.findViewById(R.id.cancelApp);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setMessage("Are you sure to cancel your appointment?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {



                            public void onClick(DialogInterface dialog, int which) {
                                final DocumentReference documentReference = fStore.collection("Appointments").document(uid);
                                documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                                        documentReference.delete();

                                        Toast.makeText(getActivity(), "Appointment has been cancelled", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }



                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();
                alert.setTitle("Cancel Appointment");
                alert.show();


            }
        });


        return view;
    }

}
