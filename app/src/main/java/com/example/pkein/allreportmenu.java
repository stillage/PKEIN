package com.example.pkein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class allreportmenu extends AppCompatActivity {
    Button btnJalan, btnJembatan, btnTaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allreportmenu);
        btnJalan = (Button)findViewById(R.id.jalanraya);
        btnJembatan = (Button)findViewById(R.id.jembatan);
        btnTaman = (Button)findViewById(R.id.taman);

        btnJalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lapor = new Intent(allreportmenu.this, laporan.class);
                startActivity(lapor);
            }
        });
        btnJembatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lapor2 = new Intent(allreportmenu.this, laporan_jembatan.class);
                startActivity(lapor2);
            }
        });
        btnTaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lapor3 = new Intent(allreportmenu.this, laporan_taman.class);
                startActivity(lapor3);
            }
        });
    }
}