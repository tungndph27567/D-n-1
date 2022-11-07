package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addClass extends AppCompatActivity {
    private EditText edNameClass;
    private Spinner idSp;
    private Button btnAddClass;
    private RecyclerView idRecyClass;
    private ClassAdapter adapter;
    List<ClassUser> list;
    String nameUser;
    List<user> listuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        findID();
        loadData();

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClassUser();
            }
        });


    }


    public void findID() {
        edNameClass = (EditText) findViewById(R.id.ed_nameClass);
        idSp = (Spinner) findViewById(R.id.id_sp);
        btnAddClass = (Button) findViewById(R.id.btn_addClass);
        idRecyClass = (RecyclerView) findViewById(R.id.id_recyClass);

    }

    private void addClassUser() {
        String strname = edNameClass.getText().toString();
        list = new ArrayList<>();
        adapter = new ClassAdapter();
        adapter.setData(list);
        listuser = new ArrayList<>();
        listuser = userDatabase.getInstance(this).dao().getListUser();
        Spiner_User_adapter spadapter = new Spiner_User_adapter(listuser, this, R.layout.item_spin_user);
        idSp.setAdapter(spadapter);
        idSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nameUser = listuser.get(position).getUsername();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ClassUser classUser = new ClassUser(strname, Integer.parseInt(nameUser));
        ClassDatabase.getInstance(this).dao().insertClass(classUser);
        Toast.makeText(this, "Add class successfully", Toast.LENGTH_SHORT).show();


    }

    private void loadData() {
        list = ClassDatabase.getInstance(this).dao().getListClass();
        adapter.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        idRecyClass.setLayoutManager(linearLayoutManager);
        idRecyClass.setAdapter(adapter);

    }
}