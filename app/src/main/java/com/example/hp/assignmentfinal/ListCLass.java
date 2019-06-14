package com.example.hp.assignmentfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.hp.assignmentfinal.adapter.ClassAdapter;
import com.example.hp.assignmentfinal.database.DatabaseHelper;
import com.example.hp.assignmentfinal.model.Lop;

import java.util.ArrayList;
import java.util.List;

public class ListCLass extends AppCompatActivity {

    public static List<Lop>  lopList = new ArrayList<>();
    public ListView lv;
    DatabaseHelper sqLite;
    ClassAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        lv = findViewById(R.id.lv);
        sqLite = new DatabaseHelper(ListCLass.this);
        lopList = sqLite.getAllClass();

        adapter = new ClassAdapter(ListCLass.this,lopList);
        lv.setAdapter(adapter);
    }
}
