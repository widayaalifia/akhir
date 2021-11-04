package com.example.kepengku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kepengku.helper.SqliteHelper;


public class Login extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    TextView tvEmail;
    Button buttonLogin, buttonRegis;
    SessionManagement sessionManagement;
    SqliteHelper sqliteHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManagement = new SessionManagement(Login.this);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btnlogin);
        buttonRegis = findViewById(R.id.signup);
        sqliteHelper = new SqliteHelper(this);

        if(sessionManagement.isLoggedIn()){
            goToActivity();;
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(email.equals("") || email.trim().isEmpty() || password.equals("") || password.trim().isEmpty()  ) {

                    Toast.makeText(Login.this,"Username Password harus diisi",Toast.LENGTH_LONG).show();
                }else if(!(email.contains("@"))){

                    Toast.makeText(Login.this,"Masukkan format email yang benar",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SQLiteDatabase db = sqliteHelper.getReadableDatabase();
                    cursor = db.rawQuery("SELECT * FROM tb_user WHERE email = '" + email + "' AND password = '"+ password+ "'",null);
                    cursor.moveToFirst();
                    if(cursor.getCount()>0){

                        sessionManagement.createLoginSession(cursor.getString(0).toString(),cursor.getString(1).toString(),cursor.getString(2).toString(), password);
                        goToActivity();
                    }else {

                        Toast.makeText(Login.this, "Username Password harus ada di database", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        buttonRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(),Register.class);
                startActivity(mIntent);
            }
        });
    }
    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(),Home.class);
        startActivity(mIntent);
    }
}
