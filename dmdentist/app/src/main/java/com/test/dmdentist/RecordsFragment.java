package com.test.dmdentist;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {

    Button manageRecords;


    public static RecordsFragment newInstance(int index) {
        RecordsFragment recordsFragment = new RecordsFragment();
        Bundle args = new Bundle(1);
        args.putInt("someInt", index);
        recordsFragment.setArguments(args);
        return recordsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_records, container, false);

        manageRecords = (Button)view.findViewById(R.id.manageRecords);

        manageRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ManageRecords.class);
                startActivity(intent);

            }
        });


        return view;
    }

}
