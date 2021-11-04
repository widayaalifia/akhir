package com.example.kepengku.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

    private String TABEL_USER= "tb_user";
    private String TABEL_KAS= "tb_kas";

    public SqliteHelper(Context context) {
        super(context, "kepengku", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Jalankan query
        db.execSQL(
                "CREATE TABLE tb_kas (kas_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, kas_type TEXT," +
                        "kas_total DOUBLE, kas_info TEXT, kas_date DATE DEFAULT CURRENT_DATE );"
        );

        db.execSQL(
                "CREATE TABLE tb_user (id_user INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username text, email text, password text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hapus tabel jika tabel tb_kas ada
        db.execSQL("DROP TABLE IF EXISTS tb_kas");
        db.execSQL("DROP TABLE IF EXISTS tb_user");
    }
}
