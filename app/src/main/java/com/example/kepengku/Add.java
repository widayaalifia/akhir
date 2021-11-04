package com.example.kepengku;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.kepengku.helper.SqliteHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Add extends AppCompatActivity {

    RadioGroup radioType;
    EditText editTotal, editInfo;
    RippleView ripSimpan;
    String type;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        type = "";
        sqliteHelper = new SqliteHelper(this);

        radioType    = findViewById(R.id.radio_type);
        editTotal    = findViewById(R.id.edit_kas_total);
        editInfo     = findViewById(R.id.edit_kas_info);
        ripSimpan    = findViewById(R.id.rip_simpan);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.tambah);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.listd:
                                startActivity(new Intent(getApplicationContext()
                                        ,MainActivity.class));
                                overridePendingTransition(0,0);
                                return true;
                            case R.id.tambah:
                                return true;
                            case R.id.cari:
                                startActivity(new Intent(getApplicationContext()
                                        ,Search.class));

                                overridePendingTransition(0,0);
                                return true;
                        }
                        return  false;
                    }
                });

        radioType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radio_kas_type_masuk:
                        type = "MASUK";
                        break;
                    case R.id.radio_kas_type_keluar:
                        type = "KELUAR";
                        break;
                }
                Log.d("Log type", type);
            }
        });


        ripSimpan.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (type.equals("") || editTotal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Isi data dengan lengkap", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO tb_kas(kas_type, kas_total, kas_info) VALUES('" +
                            type + "','" +
                            editTotal.getText().toString() + "','" +
                            editInfo.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}