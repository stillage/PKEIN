package com.example.pkein.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pkein.DetailLaporanTam;
import com.example.pkein.R;
import com.bumptech.glide.Glide;
import com.example.pkein.model.DataLaporanTam;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LaporanAdapterTaman extends RecyclerView.Adapter<LaporanAdapterTaman.MyViewHolder> {
    private Context context;
    private ArrayList<DataLaporanTam> dataLaporans;

    private DatabaseReference ref;
    private StorageReference reference;

    public LaporanAdapterTaman(Context cont, ArrayList<DataLaporanTam> data) {
        context = cont;
        dataLaporans = data;
        ref = FirebaseDatabase.getInstance().getReference().child("Taman");
        reference = FirebaseStorage.getInstance().getReference("Taman");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan_taman, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.vnama.setText(dataLaporans.get(position).getImageEmail());
        holder.vtanggal.setText(dataLaporans.get(position).getImageTanggal());
        getImage(String.valueOf(dataLaporans.get(position).getImageDetail()), holder.foto);

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context.getApplicationContext(), DetailLaporanTam.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("EMAIL", dataLaporans.get(position).getImageEmail());
                detail.putExtra("TANGGAL", dataLaporans.get(position).getImageTanggal());
                detail.putExtra("ALAMAT", dataLaporans.get(position).getImageAlamat());
                detail.putExtra("DESKRIPSI", dataLaporans.get(position).getImageName());
                detail.putExtra("URL", dataLaporans.get(position).getImageDetail());
                context.startActivity(detail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataLaporans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vnama, valamat, vtanggal, vdeskripsi;
        Button btndetail;
        ImageView foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vnama = itemView.findViewById(R.id.tv_email);
            vtanggal = itemView.findViewById(R.id.tv_tanggal);
            valamat = itemView.findViewById(R.id.tv_alamat);
            vdeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            btndetail = itemView.findViewById(R.id.btn_detail);
            foto = itemView.findViewById(R.id.foto_laporan);
        }
    }

    private void getImage(String Taman, final ImageView foto) {
        reference.child(Taman).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //tampilkan gambar dengan glide
                Glide.with(context)
                        .load(uri)
                        .into(foto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}