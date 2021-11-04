package com.example.kepengku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Catatan extends AppCompatActivity {
    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);

        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIPS1();

            }
        });
        btn2=(Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIPS2();

            }
        });
        btn3=(Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIPS3();

            }
        });

    }
    public void TIPS1(){
        Intent intent= new Intent(this,Tips1.class);
        startActivity(intent);
    }
    public void TIPS2(){
        Intent intent=new Intent(this,Tips2.class);
        startActivity(intent);

    }
    public void TIPS3(){
        Intent intent=new Intent(this,Tips3.class);
        startActivity(intent);

    }
}