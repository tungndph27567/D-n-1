package com.example.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUESTCODE = 10;
    private EditText edName;
    private EditText edAddress;
    private Button btnAdd;
    private RecyclerView idRecy;
    private userAdapter adapter;
    private List<user> list;
    private TextView tv_deleteall;
    private EditText ed_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findID();
        list = new ArrayList<>();

        adapter = new userAdapter(new userAdapter.clickItem() {
            @Override
            public void updateUser(user user) {
                update(user);
            }

            @Override
            public void deleteUser(user user) {
                delete(user);
            }
        });
        loadData();
        adapter.setDaTa(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        idRecy.setLayoutManager(linearLayoutManager);
        idRecy.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
        tv_deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });
        ed_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    SearchInFor();
                }
                return false;
            }
        });


    }

    private void delete(user user) {
        new AlertDialog.Builder(this)
                .setTitle("Delete User")
                .setMessage("Are you sure delete user: " + user.getUsername() + " ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDatabase.getInstance(MainActivity.this).dao().deleteUser(user);
                        Toast.makeText(MainActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    private void deleteAll() {
        new AlertDialog.Builder(this)
                .setTitle("Delete User")
                .setMessage("Are you sure delete user: ")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userDatabase.getInstance(MainActivity.this).dao().deleteAll();
                        Toast.makeText(MainActivity.this, "Delete All Successfully", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    private void findID() {
        edName = (EditText) findViewById(R.id.ed_name);
        edAddress = (EditText) findViewById(R.id.ed_address);
        btnAdd = (Button) findViewById(R.id.btn_add);
        idRecy = (RecyclerView) findViewById(R.id.id_recy);
        tv_deleteall = findViewById(R.id.tv_deleteall);
        ed_search = findViewById(R.id.ed_search);
    }

    private void addUser() {
        String strUser = edName.getText().toString();
        String strAddress = edAddress.getText().toString();
        if (TextUtils.isEmpty(strUser) || TextUtils.isEmpty(strAddress)) {
            return;
        }
        user user = new user(strUser, strAddress);
        if (checkUser(user)) {
            Toast.makeText(this, "user đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }

        userDatabase.getInstance(this).dao().insertUser(user);
            Toast.makeText(this, "Add user successfully", Toast.LENGTH_SHORT).show();
        edName.setText("");
        edAddress.setText("");
        hideKeyBoard();
        loadData();
    }

    public void hideKeyBoard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void loadData() {
        list = userDatabase.getInstance(this).dao().getListUser();
        adapter.setDaTa(list);
    }

    public boolean checkUser(user user) {
        List<user> list = userDatabase.getInstance(this).dao().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }

    public void update(user user) {
        Intent intent = new Intent(MainActivity.this, updateUser.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUESTCODE);
    }

    private void SearchInFor() {
        String keyWord = ed_search.getText().toString().trim();
        list = new ArrayList<>();
        list = userDatabase.getInstance(this).dao().searchUser(keyWord);
        adapter.setDaTa(list);
       hideKeyBoard();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUESTCODE && resultCode == RESULT_OK) ;
        loadData();
    }
}