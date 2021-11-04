package com.example.kepengku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kepengku.entity.ListKepeng;
import com.example.kepengku.helper.SqliteHelper;
import com.example.kepengku.other.KasAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Click extends AppCompatActivity {

    private static final String TAG = Click.class.getSimpleName();

    FrameLayout fLayout;
    RecyclerView kasView;
    TextView textJudul;
    ImageView imgIc;

    SQLiteOpenHelper sqliteHelper;
    LinearLayoutManager linierlayoutmanager;
    private ArrayList<ListKepeng> listKepengArrayList = new ArrayList<>();
    private KasAdapter mAdapter;
    String queryGetAllKasPengeluaran, queryGetAllKasPemasukan;

    public String kasId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        fLayout = findViewById(R.id.activity_to_do);
        kasView = findViewById(R.id.kas_list);
        textJudul = findViewById(R.id.txt_judul);
        imgIc   = findViewById(R.id.img_ic);

        sqliteHelper            = new SqliteHelper(this);
        linierlayoutmanager = new LinearLayoutManager(this);
        textJudul.setText(MainActivity.navigation.equals("pengeluaran") ? "Pengeluaran" : "Pemasukan");
        Resources res = getResources();
        imgIc.setImageDrawable(res.getDrawable(MainActivity.navigation.equals("pengeluaran") ? R.mipmap.ic_out : R.mipmap.ic_in));
    }

    @Override
    public void onResume(){
        super.onResume();
        queryGetAllKasPengeluaran =
                "SELECT kas_id, kas_type, kas_total, kas_info, kas_date, strftime('%d/%m/%Y', kas_date) AS tgl " +
                        "FROM tb_kas WHERE kas_type = 'KELUAR' ORDER BY kas_id DESC";
        queryGetAllKasPemasukan =
                "SELECT kas_id, kas_type, kas_total, kas_info, kas_date, strftime('%d/%m/%Y', kas_date) AS tgl " +
                        "FROM tb_kas WHERE kas_type = 'MASUK' ORDER BY kas_id DESC";
        KasAdapter();
    }

    private void KasAdapter(){
        kasView.setLayoutManager(linierlayoutmanager);
        kasView.setHasFixedSize(true);
        listKepengArrayList = listKas();

        if(listKepengArrayList.size() > 0){
            kasView.setVisibility(View.VISIBLE);
            mAdapter = new KasAdapter(this, listKepengArrayList);
            kasView.setAdapter(mAdapter);

            mAdapter.setOnItemClickCallback(new KasAdapter.OnItemClickCallback() {
                @Override
                public void onItemClicked(View v) {
                    MainActivity.kasId = ((TextView) v.findViewById(R.id.text_kas_id)).getText().toString();
                    KasListMenu();
                }
            });
        }else {
            kasView.setVisibility(View.GONE);
            Toast.makeText(this, "Belum ada data. Tambah sekarang yuk!", Toast.LENGTH_LONG).show();
        }
    }

    public void KasListMenu(){
        final Dialog dialog = new Dialog(Click.this);

        dialog.setContentView(R.layout.operation_menu);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView textEdit  = dialog.findViewById(R.id.text_edit);
        TextView textHapus = dialog.findViewById(R.id.text_hapus);
        dialog.show();

        textEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                go to edit activity
                startActivity(new Intent(Click.this, Edit.class));
            }
        });
        textHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                go to confirm delete action
                KasDelete();
            }
        });
    }

    public void KasDelete(){
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Konfirmasi");
        alertdialog.setMessage("Anda Yakin mau dihapus?");
        alertdialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

//                delete data action
                SQLiteDatabase database = sqliteHelper.getWritableDatabase();
                database.execSQL("DELETE FROM tb_kas WHERE kas_id = '" + MainActivity.kasId + "'");
                Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_LONG).show();
                KasAdapter();
            }
        });
        alertdialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertdialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sqliteHelper != null){
            sqliteHelper.close();
        }
    }

    public ArrayList<ListKepeng> listKas(){
        NumberFormat formatRp = NumberFormat.getInstance(Locale.GERMANY);
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();
        ArrayList<ListKepeng> storeKas = new ArrayList<>();
        Cursor cursor;
        String queryDefinition = MainActivity.navigation.equals("pengeluaran") ? queryGetAllKasPengeluaran : queryGetAllKasPemasukan;
        cursor = db.rawQuery(queryDefinition, null);

        if(cursor.moveToFirst()){
            do{
                String kas_id = cursor.getString(0);
                String kas_type = cursor.getString(1);
                String kas_total = "Rp. "+formatRp.format(cursor.getDouble(2));
                String kas_info = cursor.getString(3);
//                String kas_date = cursor.getString(4);
                String kas_date_fr = cursor.getString(5);
                storeKas.add(new ListKepeng(kas_id, kas_type, kas_total, kas_info, kas_date_fr));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeKas;
    }
}