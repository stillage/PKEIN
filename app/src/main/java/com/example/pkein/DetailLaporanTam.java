package com.example.pkein;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetailLaporanTam extends AppCompatActivity{
    ImageView imageView;
    TextView vemail, valamat, vtanggal, vdeskripsi;
    String email, alamat, tanggal, deskripsi, gambar;
    StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference first = databaseReference.child("Taman");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan_tam);
        vemail = findViewById(R.id.tv_email);
        valamat = findViewById(R.id.tv_alamat);
        vtanggal = findViewById(R.id.tv_tanggal);
        vdeskripsi = findViewById(R.id.tv_deskripsi);
        imageView = findViewById(R.id.gambar_laporan);

        email = getIntent().getStringExtra("EMAIL");
        alamat = getIntent().getStringExtra("ALAMAT");
        tanggal = getIntent().getStringExtra("TANGGAL");
        deskripsi = getIntent().getStringExtra("DESKRIPSI");
        gambar = getIntent().getStringExtra("URL");

        vemail.setText("Pengirim : "+email);
        valamat.setText(alamat);
        vtanggal.setText("Tanggal : "+tanggal);
        vdeskripsi.setText("Deskripsi : "+deskripsi);
        storageReference = FirebaseStorage.getInstance().getReference("Taman");
    }

    @Override
    protected void onStart() {
        super.onStart();
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.get().load(grimaces(gambar)).into(imageView);
            }
            private String grimaces(String Taman) {
                storageReference.child(Taman).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageView);
                    }
                });
                return Taman;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}