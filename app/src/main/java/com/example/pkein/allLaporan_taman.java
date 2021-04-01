package com.example.pkein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.pkein.adapter.LaporanAdapterTaman;
import com.example.pkein.model.DataLaporanTam;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class allLaporan_taman extends AppCompatActivity {
    private DatabaseReference reference;

    ArrayList<DataLaporanTam> list;
    LaporanAdapterTaman adapter;
    private RecyclerView mRecylcer;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_laporan_taman);
        mRecylcer = findViewById(R.id.list_laporan);
        mRecylcer.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("Taman");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    DataLaporanTam upload = dataSnapshot1.getValue(DataLaporanTam.class);
                    list.add(upload);
                }
                adapter = new LaporanAdapterTaman(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.jalan){
            startActivity(new Intent(allLaporan_taman.this, allLaporan.class));
        } else if (item.getItemId() == R.id.jembatan) {
            startActivity(new Intent(allLaporan_taman.this, allLaporan_jembatan.class));
        } else if (item.getItemId() == R.id.taman) {
            startActivity(new Intent(allLaporan_taman.this, allLaporan_taman.class));
        }

        return true;
    }
}