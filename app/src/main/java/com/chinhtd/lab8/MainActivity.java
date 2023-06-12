package com.chinhtd.lab8;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SachModel> list = new ArrayList<>();
    SachAdapter adapter;
    int indexSachEdit = -1;
    String File_name = "sv.txt";
    ActivityResultLauncher<Intent> getData =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                SachModel sachModel = (SachModel) result.getData().getSerializableExtra("sach");
                list.set(indexSachEdit,sachModel);
                adapter.notifyDataSetChanged();

                luuListDuLieu();

            }
        }
    });
    public void luuListDuLieu(){
        try {
            FileOutputStream fileOutputStream = openFileOutput(File_name,MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void docDuLieu(){
        try {
            FileInputStream fileInputStream = openFileInput(File_name);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (ArrayList<SachModel>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        docDuLieu();
        if (list.size() == 0){
            list.add(new SachModel("Quynh Yeu Anh", 2004, "Tong Doanh Chinh"));
            list.add(new SachModel("Quynh Yeu Oanh", 2004, "Tong Doanh Chinh"));
            list.add(new SachModel("Quynh Yeu Nhan", 2004, "Tong Doanh Chinh"));
            list.add(new SachModel("Quynh Yeu Hoa", 2004, "Tong Doanh Chinh"));
        }


        ListView listView = findViewById(R.id.lv_sach);

        adapter = new SachAdapter(this,list);
        listView.setAdapter(adapter);

    }

    private class SachAdapter extends BaseAdapter {

        Context context;
        ArrayList<SachModel> list;

        public SachAdapter(Context context, ArrayList<SachModel> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_sach, parent, false);

            TextView ten = convertView.findViewById(R.id.tv_tensach);
            TextView nam = convertView.findViewById(R.id.tv_nam);
            TextView tacGia = convertView.findViewById(R.id.tv_tacgia);

            ten.setText(list.get(position).getTenSach());
            nam.setText(list.get(position).getYear() + "");
            tacGia.setText(list.get(position).getTacGia());
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    new AlertDialog.Builder(context).setMessage("Bạn có chắc muốn xóa không").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            list.remove(position);
                            notifyDataSetChanged();

                            luuListDuLieu();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();


                    return true;
                }
            });

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    indexSachEdit=position;
                    Intent intent = new Intent(getApplicationContext(), ActivityUpdateSAch.class);
                    intent.putExtra("sach",list.get(position));
                    getData.launch(intent);
                }
            });
            return convertView;
        }
    }
}