package com.test.dm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static java.util.Calendar.MINUTE;

public class GetAppointment extends AppCompatActivity {



    private static final String TAG = "GetAppointment";


    Button dateButton, timeButton, bookBtn;
    TextView dateTextView, timeTextView;
    EditText mPhone;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_appointment);

        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);

        mPhone = findViewById(R.id.pNum);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

/**        bookBtn = findViewById(R.id.bookHauConfirm);

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = dateTextView.getText().toString();
                String time = timeTextView.getText().toString();
                String phoneN = mPhone.getText().toString();

                uid = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("Appointments").document(uid);
                Map<String, Object> user = new HashMap<>();
                user.put("Date", date);
                user.put("Time", time);
                user.put("Phone", phoneN);
                documentReference.set(user);

                Intent intent = new Intent(GetAppointment.this,ThanksBookingActivity.class);
                startActivity(intent);
            }




        }); **/



    }

    private void handleDateButton() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                dateTextView.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();




    }

    private void handleTimeButton() {
        final Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();
                timeTextView.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();


    }



    public void dialog(View view) {



        bookBtn = findViewById(R.id.bookHauConfirm);
        bookBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String time = timeTextView.getText().toString();
                String date = dateTextView.getText().toString();


                AlertDialog.Builder altdial = new AlertDialog.Builder(GetAppointment.this);
                altdial.setMessage("Your appointment is on \n" + date + "\n at " + time).setCancelable(false)

                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String time = timeTextView.getText().toString();
                                String date = dateTextView.getText().toString();
                                String phoneN = mPhone.getText().toString();

                                if (TextUtils.isEmpty(phoneN)) {
                                    mPhone.setError("Phone Number is Required");
                                    return;
                                }

                                if (phoneN.length() != 11) {
                                    mPhone.setError("Phone Number must be 11 digits");
                                    return;
                                }

                                else {

                                    uid = fAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("Appointments").document(uid);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Date", date);
                                    user.put("Time", time);
                                    user.put("Phone", phoneN);
                                    documentReference.update(user);

                                    Intent intent = new Intent(GetAppointment.this, ThanksBookingActivity.class);
                                    startActivity(intent);
                                }
                            }




                        })


                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });



                AlertDialog alert = altdial.create();
                alert.setTitle("Please Confirm your Appointment");
                alert.show();



            }







        });




    }


}
