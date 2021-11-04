package com.example.kepengku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kepengku.helper.SqliteHelper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Home extends AppCompatActivity {
    LinearLayout catatan_keuangan, artikel;
    Button logout;
    TextView saldo;
    SessionManagement sessionManagement;
    SqliteHelper sqliteHelper;
    Cursor cursor;
    TextView textMasuk, textKeluar;
    String querymasuk, querykeluar;
    SwipeRefreshLayout swipeRefresh;
    ArrayList<HashMap<String,
            String>> kasArrayList = new ArrayList<>();

    double i = 0;
    double e = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManagement = new SessionManagement(Home.this);
        saldo=findViewById(R.id.saldoku);

        //Sum();

        sqliteHelper   = new SqliteHelper(this);
        catatan_keuangan = findViewById(R.id.catatan_keuangan);
        catatan_keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity();

            }
        });

        artikel=findViewById(R.id.artikel);
        artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catatan();

            }
        });
        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManagement.logoutUser();

            }
        });

    }
    public void MainActivity(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void Catatan(){
        Intent intent=new Intent(this,Catatan.class);
        startActivity(intent);

    }
    public void Logout(){
        sessionManagement.logoutUser();

    }
    @Override
    public void onResume(){
        super.onResume();

        querymasuk   = "SELECT SUM(kas_total) FROM tb_kas WHERE kas_type='MASUK' ";
        querykeluar   = "SELECT SUM(kas_total) FROM tb_kas WHERE kas_type='KELUAR'";
        Sum();
    }

    private void Sum() {

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();

        cursor = db.rawQuery(querymasuk, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            if (cursor.isNull(0)) {
                //saldo.setText("0");
                i = 0;
            } else {
                // saldo.setText(cursor.getString(0).toString());
                i = Double.parseDouble(cursor.getString(0).toString());
            }

            cursor = db.rawQuery(querykeluar, null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                cursor.moveToPosition(0);
                if (cursor.isNull(0)) {
                    // textKeluar.setText("0");
                    e = 0;
                } else {
                    //textKeluar.setText(cursor.getString(0).toString());
                    e = Double.parseDouble(cursor.getString(0).toString());
                }

                double total = i - e;
                saldo.setText(Double.toString(total));
//            swipeRefresh.setRefreshing(false);

            }


        }
    }

}