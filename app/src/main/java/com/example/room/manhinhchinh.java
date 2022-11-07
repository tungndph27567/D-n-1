package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manhinhchinh extends AppCompatActivity {
    private Button addUser;
    private Button addClass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhchinh);
        addUser = (Button) findViewById(R.id.addUser);
        addClass = (Button) findViewById(R.id.addClass);
        Intent i1 = new Intent(manhinhchinh.this,MainActivity.class);
        Intent i2 = new Intent(manhinhchinh.this,addClass.class);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i1);
            }
        });
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i2);
            }
        });
    }
}