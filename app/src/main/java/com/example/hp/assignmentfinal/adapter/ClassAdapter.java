package com.example.hp.assignmentfinal.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.assignmentfinal.ListCLass;
import com.example.hp.assignmentfinal.R;
import com.example.hp.assignmentfinal.database.DatabaseHelper;
import com.example.hp.assignmentfinal.model.Lop;

import java.util.List;

public class ClassAdapter extends BaseAdapter{

    public Context context;
    public List<Lop> lopList;
    public DatabaseHelper sqLite;

    public ClassAdapter(Context context, List<Lop> productList){
        this.lopList = productList;
        this.context = context;
        sqLite = new DatabaseHelper(context);

    }

    @Override
    public int getCount() {
        return lopList.size();
    }

    @Override
    public Object getItem(int position) {
        return lopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        TextView tvID = convertView.findViewById(R.id.tvID);
        TextView tvName = convertView.findViewById(R.id.tvName);
        ImageView imgDel = convertView.findViewById(R.id.imgDel);
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop lop = lopList.get(position);
                if (sqLite.deleteClass(lop.getId())>0){
                    lopList.remove(lop);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Lop lop1=(Lop) getItem(position);
        tvID.setText(lop1.getId());
        tvName.setText(lop1.getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Lop lop = lopList.get(position);
//                Intent intent= new Intent(context, MainActivity.class);
//                Bundle bundle= new Bundle();
//                bundle.putString("id",lop.getId());
//                bundle.putString("name",lop.getName());
//                intent.putExtra("bundle",bundle);
//                context.startActivity(intent);

                showDialog(position);
            }
        });
        return convertView;
    }
    private void showDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_update_class, null);

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

        Lop lop = lopList.get(position);
        edMaLop.setText(lop.getId());
        edTenLop.setText(lop.getName());

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
                sqLite = new DatabaseHelper(context);
                Lop lop = new Lop(edMaLop.getText().toString(),
                        edTenLop.getText().toString());
                if (sqLite.updateClass(lop) > 0) {
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(context,ListCLass.class);
                    context.startActivity(i);
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.create().show();
    }
}
