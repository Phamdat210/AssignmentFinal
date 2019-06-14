package com.example.hp.assignmentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.assignmentfinal.adapter.StudentAdapter;
import com.example.hp.assignmentfinal.database.DatabaseHelper;
import com.example.hp.assignmentfinal.model.Lop;
import com.example.hp.assignmentfinal.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class ListQuanLy extends AppCompatActivity {
    public static List<SinhVien> sinhVienList = new ArrayList<>();
    private List<Lop> lopList;
    public ListView lv;
    DatabaseHelper sqLite;
    StudentAdapter adapter ;
    private EditText edName;
    private EditText edDate;
    Spinner spinner;
    private String lopIDSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quan_ly);

        edName = (EditText) findViewById(R.id.edName);
        edDate = (EditText) findViewById(R.id.edDate);
        spinner = findViewById(R.id.spinner);
        lv = findViewById(R.id.lv);
        sqLite = new DatabaseHelper(ListQuanLy.this);
        sinhVienList = sqLite.getAllSinhVien();

        adapter = new StudentAdapter(ListQuanLy.this,sinhVienList);
        lv.setAdapter(adapter);

        try{
            Intent intent=getIntent();
            if(intent!=null){
                Bundle bundle=intent.getBundleExtra("bundlee");
                edName.setText(bundle.getString("nameStudent"));
                edDate.setText(bundle.getString("date"));

            }
        }catch (Exception ex){
            Log.e("Intent","Wrong");
        }
        SetUpSpinner();
    }

    private void SetUpSpinner(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lopIDSelected = lopList.get(position).getName();
                Log.e("lopIDSelected", lopIDSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        lopList = sqLite.getAllClass();
        ArrayAdapter<Lop> lopArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lopList);
        lopArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(lopArrayAdapter);
    }

    public void addSV(View view) {
        sqLite = new DatabaseHelper(getApplicationContext());
        SinhVien sinhVien = new SinhVien(
//                lopIDSelected,
                edName.getText().toString(),
                edDate.getText().toString());
        if (sqLite.insertSinhVien(sinhVien)>0){
            Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ListQuanLy.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
        }
    }

    public void updateSV(View view) {
        sqLite = new DatabaseHelper(ListQuanLy.this);
        SinhVien sinhVien = new SinhVien(
//                lopIDSelected,
                edName.getText().toString(),
                edDate.getText().toString());
        if (sqLite.updateSinhVien(sinhVien)==1){
            Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ListQuanLy.this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),"Cập nhật thất bại",Toast.LENGTH_LONG).show();
        }
    }
}
