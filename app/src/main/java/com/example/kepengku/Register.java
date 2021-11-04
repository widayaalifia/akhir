package com.example.kepengku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kepengku.helper.SqliteHelper;


public class Register extends AppCompatActivity {

    EditText edtEmail, edtPassword,edtUser, edtPass;
    Button tonReg;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqliteHelper = new SqliteHelper(this);

        edtEmail = (EditText) findViewById(R.id.editemail);
        edtPassword = (EditText) findViewById(R.id.editpassword);
        edtPass = (EditText) findViewById(R.id.editkonfpass);
        edtUser =(EditText) findViewById(R.id.edituser);
        tonReg=(Button) findViewById(R.id.buttonregister);

        tonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUser.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String konfPass = edtPass.getText().toString();


                if(email.equals("") || email.trim().isEmpty() || password.equals("") || password.trim().isEmpty() ||
                        username.equals("") || username.trim().isEmpty() || konfPass.equals("") || konfPass.trim().isEmpty())//cek kondisi agar kolom tidak kosong
                {
                    Toast.makeText(Register.this,"Semua kolom harus diisi",Toast.LENGTH_LONG).show();
                }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){//cek format email
                    Toast.makeText(Register.this,"Masukkan format email yang benar",Toast.LENGTH_LONG).show();

                }else if(!konfPass.equals(password)){//memastikan password yang dimasukkan sama
                    Toast.makeText(Register.this,"Konfirmasi password salah",Toast.LENGTH_LONG).show();
                }else{//jika semua kondisi terpenuhi maka berhasil

                    SQLiteDatabase db = sqliteHelper.getWritableDatabase();
                    db.execSQL("INSERT INTO tb_user(username, email, password) VALUES('" +
                            username + "','" +
                            email + "','" +
                            password+ "')");
                    Toast.makeText(getApplicationContext(), "Berhasil Mendaftar", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
}