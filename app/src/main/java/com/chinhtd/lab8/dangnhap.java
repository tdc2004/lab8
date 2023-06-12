package com.chinhtd.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class dangnhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Spinner spinner = findViewById(R.id.sp);
        List<String> items = Arrays.asList("Việt Nam - Tiếng Việt", "Singapore - English");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedlanguage = items.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextInputEditText edtUsername = findViewById(R.id.tk_dn);
        TextInputEditText edtPassword = findViewById(R.id.mk_dn);
        String sUserName = getIntent().getStringExtra(dangki.KEY_USERNAME);
        String sPassword = getIntent().getStringExtra(dangki.KEY_PASSWORD);
        edtUsername.setText(sUserName);
        edtPassword.setText(sPassword);
        String string = getIntent().getStringExtra("thongBao");
        Toast.makeText(this, string + "", Toast.LENGTH_SHORT).show();

        Button btn_dn = findViewById(R.id.btnDN);
        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = new FileInputStream("chinh.txt");
                    int read = -1;
                    StringBuilder builder =new StringBuilder();
                    while(((read = fis.read())!= -1)){
                        builder.append((char) read);
                    }
                    String data =builder.toString();
                    String[] arrdata =data.split(",");
                    if (arrdata != null && arrdata.length == 2){
                        String username = arrdata[0];
                        String password = arrdata[1];
                        edtUsername.setText(username);
                        edtPassword.setText(password);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Button btn = findViewById(R.id.btnDK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dangki.class);
                startActivity(intent);
            }
        });
    }
}