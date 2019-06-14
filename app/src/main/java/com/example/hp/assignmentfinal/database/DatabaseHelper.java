package com.example.hp.assignmentfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.assignmentfinal.model.Lop;
import com.example.hp.assignmentfinal.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "DBCLass";
    public static final int VERSION = 1;
    public final String CREATE_TABLE = "CREATE TABLE Class (id VARCHAR PRIMARY KEY, name NVARCHAR)";
    public final String COLUMN_ID = "id";
    public final String COLUMN_NAME = "name";
    public final String TABLE_NAME_CLASS = "Class";

    public final String CREATE_TABLE_SINHVIEN = "CREATE TABLE SinhVien (nameStudent VARCHAR PRIMARY KEY, date NVARCHAR)";
//    public final String COLUMN_CLASS_ID = "lopID";
    public final String COLUMN_NAME_SINHVIEN = "nameStudent";
    public final String COLUMN_DATE_SINHVIEN = "date";
    public final String TABLE_NAME_SINHVIEN = "SinhVien";

    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_SINHVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SINHVIEN);
    }

    public long insertClass(Lop lop){
        //xin quyền
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //ghép cặp dữ liệu
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,lop.getId());
        contentValues.put(COLUMN_NAME,lop.getName());

        //sử dụng lệnh insert
        long result = sqLiteDatabase.insert(TABLE_NAME_CLASS,null,contentValues);

        //đóng kết nối
        sqLiteDatabase.close();

        return result;
    }

    public int deleteClass(String id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        int result = sqLiteDatabase.delete(TABLE_NAME_CLASS,COLUMN_ID + "=?",new String[]{id});
        sqLiteDatabase.close();
        return result;
    }

    public long updateClass(Lop lop){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,lop.getId());
        values.put(COLUMN_NAME,lop.getName());

        long result = sqLiteDatabase.update(TABLE_NAME_CLASS,values,COLUMN_ID+"=?", new String[]{lop.getId()});
        sqLiteDatabase.close();
        return result;
    }

    public List<Lop> getAllClass(){
        List<Lop> lopList = new ArrayList<>();
        //Xin quyền
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //viết câu lệnh select
        String select = "SELECT * FROM " + TABLE_NAME_CLASS;

        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                Lop lop = new Lop();
                lop.setId(cursor.getString(0));
                lop.setName(cursor.getString(1));

                lopList.add(lop);
            } while (cursor.moveToNext());

            cursor.close();
        }
        //đóng kết nối DB
        sqLiteDatabase.close();
        return lopList;
    }

    public long insertSinhVien(SinhVien sinhVien){
        //xin quyền
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //ghép cặp dữ liệu
        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_CLASS_ID,sinhVien.getLopID());
        contentValues.put(COLUMN_NAME_SINHVIEN,sinhVien.getNameStudent());
        contentValues.put(COLUMN_DATE_SINHVIEN,sinhVien.getDate());

        //sử dụng lệnh insert
        long result = sqLiteDatabase.insert(TABLE_NAME_SINHVIEN,null,contentValues);

        //đóng kết nối
        sqLiteDatabase.close();

        return result;
    }

    public int deleteSinhVien(String name){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        int result = sqLiteDatabase.delete(TABLE_NAME_SINHVIEN,COLUMN_NAME_SINHVIEN + "=?",new String[]{name});
        sqLiteDatabase.close();
        return result;
    }

    public long updateSinhVien(SinhVien sinhVien){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_SINHVIEN,sinhVien.getNameStudent());
        values.put(COLUMN_DATE_SINHVIEN,sinhVien.getDate());

        long result = sqLiteDatabase.update(TABLE_NAME_SINHVIEN,values,COLUMN_NAME_SINHVIEN+"=?", new String[]{sinhVien.getNameStudent()});
        sqLiteDatabase.close();
        return result;
    }

    public List<SinhVien> getAllSinhVien(){
        List<SinhVien> sinhVienList = new ArrayList<>();
        //Xin quyền
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        //viết câu lệnh select
        String select = "SELECT * FROM " + TABLE_NAME_SINHVIEN;

        Cursor cursor = sqLiteDatabase.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do {
                SinhVien sinhVien = new SinhVien();
//                sinhVien.setLopID(cursor.getString(0));
                sinhVien.setNameStudent(cursor.getString(0));
                sinhVien.setDate(cursor.getString(1));

                sinhVienList.add(sinhVien);
            } while (cursor.moveToNext());

            cursor.close();
        }
        //đóng kết nối DB
        sqLiteDatabase.close();
        return sinhVienList;
    }

}
