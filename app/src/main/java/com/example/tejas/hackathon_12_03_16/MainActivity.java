package com.example.tejas.hackathon_12_03_16;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.Go);
        name = (EditText) findViewById(R.id.name2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendActivity.acceptedNames.add(name.getText().toString());
                startActivity(new Intent(getApplicationContext(), SendActivity.class));
            }
        });
    }
}
