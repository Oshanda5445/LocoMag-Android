package com.example.shan.anew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private Button bStart;
    private Button bGet;
    private Button bEnter;
    private Button bEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final TextView tvDriverID = (TextView) findViewById(R.id.tvDriverID);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String driver_id = intent.getStringExtra("driver_id");

        String message = "WELCOME " + username;
        tvWelcomeMsg.setText(message);
        tvDriverID.setText(driver_id);

        bStart = (Button) findViewById(R.id.bStart);
        bGet = (Button) findViewById(R.id.bGet);
        bEnter = (Button) findViewById(R.id.bEnter);
        bEnd = (Button) findViewById(R.id.bEnd);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStart();
            }
        });

        bGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGetFailure();
            }
        });

        bEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEnterFailure();
            }
        });

        bEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEnd();
            }
        });
    }
    public void openStart() {
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    public void openGetFailure() {
        Intent intent = new Intent(this, GetFailure.class);
        startActivity(intent);
    }

    public void openEnterFailure() {
        Intent intent = new Intent(this, EnterFailure.class);
        startActivity(intent);
    }
    public void openEnd() {
        Intent intent = new Intent(this, End.class);
        startActivity(intent);
    }
}

