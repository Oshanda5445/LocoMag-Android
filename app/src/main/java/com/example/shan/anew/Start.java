package com.example.shan.anew;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Start extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    Button bPicker;
    TextView tvDate;
    TextView tvTime;

    int day, month, year, hour, minute;
    int dayfinal, monthfinal, yearfinal, hourfinal, minutefinal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final EditText etRoute =(EditText) findViewById(R.id.etRoute);
        final EditText etDriverID =(EditText) findViewById(R.id.etDriverID);
        final EditText etGuard =(EditText) findViewById(R.id.etGuard);
        final EditText etLoco =(EditText) findViewById(R.id.etLoco);
        final EditText etDate = (EditText) findViewById(R.id.etDate);
        final EditText etTime = (EditText) findViewById(R.id.etTime);
        final EditText etFailure =(EditText) findViewById(R.id.etFailure);
        final Button bSubmit =(Button) findViewById(R.id.bSubmit);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String route_ID = etRoute.getText().toString();
                final String driver_ID = etDriverID.getText().toString();
                final String guard_ID = etGuard.getText().toString();
                final String loco_ID = etLoco.getText().toString();
                final String date = etDate.getText().toString();
                final String start_time = etTime.getText().toString();
                final String any_failures = etFailure.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(Start.this, Login.class);
                                Start.this.startActivity(intent);
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Start.this);
                                builder.setMessage("Submit Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                StartRequest startRequest = new StartRequest(route_ID,driver_ID,guard_ID,loco_ID,date,start_time,any_failures, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Start.this);
                queue.add(startRequest);
            }
        });


        Intent intent = getIntent();
        String driver_id = intent.getStringExtra("driver_id");

        String message = "WELCOME " + driver_id;
        etDriverID.setText(message);

        bPicker = (Button) findViewById(R.id.bPicker);
        bPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar= Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Start.this, Start.this, year,month,day);
                datePickerDialog.show();

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearfinal = i;
        monthfinal = i1 + 1;
        dayfinal = i2;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Start.this, Start.this, hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hourfinal = i;
        minutefinal = i1;

        tvDate.setText(yearfinal + "\n" + "." + monthfinal + "\n" + "." + dayfinal);
        tvTime.setText(hourfinal + "\n" + ":" + minutefinal + "\n");

    }
}

