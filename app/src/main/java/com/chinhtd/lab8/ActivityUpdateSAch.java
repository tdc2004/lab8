package com.chinhtd.lab8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityUpdateSAch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sach);
        EditText edtTenSach = findViewById(R.id.edt_tenSach);
        EditText edtNam = findViewById(R.id.edt_nam);
        EditText edtTacGia = findViewById(R.id.edt_tacGia);

        SachModel sachModel = (SachModel) getIntent().getSerializableExtra("sach");

        edtTenSach.setText(sachModel.getTenSach());
        edtNam.setText(sachModel.getYear()+"");
        edtTacGia.setText(sachModel.getTacGia());
        Button button = findViewById(R.id.btn_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = edtTenSach.getText().toString();
                String namXb = edtNam.getText().toString();
                String tacgia = edtTacGia.getText().toString();

                // validate
                int yeardXB = Integer.parseInt(namXb);

                SachModel sachModel1 = new SachModel(tenSach,yeardXB,tacgia);

                Intent data = new Intent();
                data.putExtra("sach",sachModel1);
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }
}