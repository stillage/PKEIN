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

import com.example.pkein.adapter.LaporanAdapterJembatan;
import com.example.pkein.model.DataLaporanJem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class allLaporan_jembatan extends AppCompatActivity {
    private DatabaseReference reference;

    ArrayList<DataLaporanJem> list;
    LaporanAdapterJembatan adapter;
    private RecyclerView mRecylcer;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_laporan_jembatan);
        mRecylcer = findViewById(R.id.list_laporan);
        mRecylcer.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("Jembatan");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    DataLaporanJem upload = dataSnapshot1.getValue(DataLaporanJem.class);
                    list.add(upload);
                }
                adapter = new LaporanAdapterJembatan(getApplicationContext(), list);
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
            startActivity(new Intent(allLaporan_jembatan.this, allLaporan.class));
        } else if (item.getItemId() == R.id.jembatan) {
            startActivity(new Intent(allLaporan_jembatan.this, allLaporan_jembatan.class));
        } else if (item.getItemId() == R.id.taman) {
            startActivity(new Intent(allLaporan_jembatan.this, allLaporan_taman.class));
        }

        return true;
    }
}