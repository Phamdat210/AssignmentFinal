package com.example.hp.assignmentfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.assignmentfinal.ListQuanLy;
import com.example.hp.assignmentfinal.R;
import com.example.hp.assignmentfinal.database.DatabaseHelper;
import com.example.hp.assignmentfinal.model.SinhVien;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    public Context context;
    public List<SinhVien> sinhVienList;
    public DatabaseHelper sqLite;

    public StudentAdapter(Context context, List<SinhVien> productList){
        this.sinhVienList = productList;
        this.context = context;
        sqLite = new DatabaseHelper(context);

    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_student,parent,false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDate = convertView.findViewById(R.id.tvDate);
        ImageView imgDel = convertView.findViewById(R.id.imgDel);
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = sinhVienList.get(position);
                if (sqLite.deleteSinhVien(sinhVien.getNameStudent())>0){
                    sinhVienList.remove(sinhVien);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final SinhVien sinhVien1 = (SinhVien) getItem(position);
        tvName.setText(sinhVien1.getNameStudent());
        tvDate.setText(sinhVien1.getDate());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SinhVien sinhVien = sinhVienList.get(position);
                Intent intent= new Intent(context, ListQuanLy.class);
                Bundle bundle= new Bundle();
                bundle.putString("nameStudent",sinhVien.getNameStudent());
                bundle.putString("date",sinhVien.getDate());
                intent.putExtra("bundlee",bundle);
                context.startActivity(intent);

            }
        });
        return convertView;
    }

}
