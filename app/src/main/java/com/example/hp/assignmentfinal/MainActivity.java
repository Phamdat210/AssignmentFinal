package com.example.hp.assignmentfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.assignmentfinal.database.DatabaseHelper;
import com.example.hp.assignmentfinal.model.Lop;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dialogAdd(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View dialog = LayoutInflater.from(this).inflate(R.layout.dialog_add_class,null);

        builder.setView(dialog);

        final EditText edMaLop;
        final EditText edTenLop;
        Button btnDel;
        Button btnLuu;
        Button btnUpdate;

        edMaLop = (EditText) dialog.findViewById(R.id.edMaLop);
        edTenLop = (EditText) dialog.findViewById(R.id.edTenLop);
        btnDel = (Button) dialog.findViewById(R.id.btnDel);
        btnLuu = (Button) dialog.findViewById(R.id.btnLuu);


        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edMaLop.setText("");
                edTenLop.setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(getApplicationContext());
                Lop lop = new Lop(edMaLop.getText().toString(),
                        edTenLop.getText().toString());
                if (databaseHelper.insertClass(lop)>0){
                    Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,ListCLass.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.create().show();

    }

    public void listClass(View view) {
        Intent intent = new Intent(MainActivity.this,ListCLass.class);
        startActivity(intent);
    }

    public void viewQuanLy(View view) {
        Intent intent = new Intent(MainActivity.this,ListQuanLy.class);
        startActivity(intent);
    }
}
