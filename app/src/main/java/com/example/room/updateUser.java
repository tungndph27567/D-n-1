package com.example.room;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateUser extends AppCompatActivity {
    private EditText edName;
    private EditText edAddress;
    private Button btnUpdate;
    private user user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        user = (user) getIntent().getExtras().get("object_user");
        edName = (EditText) findViewById(R.id.ed_name);
        edAddress = (EditText) findViewById(R.id.ed_address);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        if (user != null) {
            edName.setText(user.getUsername());
            edAddress.setText(user.getAddress());
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               update();
            }
        });
    }

    private void update() {
        String strUser = edName.getText().toString();
        String strAddress = edAddress.getText().toString();
        if (TextUtils.isEmpty(strUser) || TextUtils.isEmpty(strAddress)) {
            return;
        }
        user.setAddress(strAddress);
        user.setUsername(strUser);
        userDatabase.getInstance(this).dao().updateUser(user);
        Toast.makeText(this, "update user succesfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent();
        setResult(Activity.RESULT_OK,i);
        finish();
    }
}