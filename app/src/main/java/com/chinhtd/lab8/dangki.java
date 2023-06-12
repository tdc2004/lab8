package com.chinhtd.lab8;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class dangki extends AppCompatActivity {

    public static String KEY_USERNAME = "user_name";
    public static String KEY_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        Button btn = findViewById(R.id.btnExit0);
        Button dk =findViewById(R.id.dangKy);
        EditText taikhoan = findViewById(R.id.tk);
        EditText matkhau = findViewById(R.id.mk);
        EditText rematkhau = findViewById(R.id.rmk);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),dangnhap.class);
                startActivity(intent);
            }
        });

        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk1 = taikhoan.getText().toString();
                String mk1 = matkhau.getText().toString();
                String rmk1 = rematkhau.getText().toString();

                if(tk1 == null || tk1.trim().equals("")){
                    Toast.makeText(dangki.this, "Chưa nhập Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mk1 == null || mk1.trim().equals("")){
                    Toast.makeText(dangki.this, "Chưa nhập Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rmk1 == null || rmk1.trim().equals("")){
                    Toast.makeText(dangki.this, "Chưa xác nhận Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!rmk1.equals(mk1)){
                    Toast.makeText(dangki.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), dangnhap.class);
                intent.putExtra(KEY_USERNAME, tk1);
                intent.putExtra(KEY_PASSWORD, mk1);
                intent.putExtra("thongBao","Đăng kí thành công" );
                try {
                    FileOutputStream fileOutputStream = openFileOutput("chinh.txt",MODE_PRIVATE);
                    fileOutputStream.write(tk1.getBytes());
                    fileOutputStream.write(",".getBytes());
                    fileOutputStream.write(mk1.getBytes());
                    fileOutputStream.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                startActivity(intent);
            }
        });
    }
}