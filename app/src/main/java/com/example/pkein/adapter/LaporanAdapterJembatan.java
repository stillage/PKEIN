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

import com.example.pkein.DetailLaporanJem;
import com.example.pkein.R;
import com.bumptech.glide.Glide;
import com.example.pkein.model.DataLaporanJem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LaporanAdapterJembatan extends RecyclerView.Adapter<LaporanAdapterJembatan.MyViewHolder> {
    private Context context;
    private ArrayList<DataLaporanJem> dataLaporansjem;

    private DatabaseReference ref;
    private StorageReference reference;

    public LaporanAdapterJembatan(Context cont, ArrayList<DataLaporanJem> data) {
        context = cont;
        dataLaporansjem = data;
        ref = FirebaseDatabase.getInstance().getReference().child("Jembatan");
        reference = FirebaseStorage.getInstance().getReference("Jembatan");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan_jembatan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.vnama.setText(dataLaporansjem.get(position).getImageEmail());
        holder.vtanggal.setText(dataLaporansjem.get(position).getImageTanggal());
        getImage(String.valueOf(dataLaporansjem.get(position).getImageDetail()), holder.foto);

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context.getApplicationContext(), DetailLaporanJem.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("EMAIL", dataLaporansjem.get(position).getImageEmail());
                detail.putExtra("TANGGAL", dataLaporansjem.get(position).getImageTanggal());
                detail.putExtra("ALAMAT", dataLaporansjem.get(position).getImageAlamat());
                detail.putExtra("DESKRIPSI", dataLaporansjem.get(position).getImageName());
                detail.putExtra("URL", dataLaporansjem.get(position).getImageDetail());
                context.startActivity(detail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataLaporansjem.size();
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

    private void getImage(String Jembatan, final ImageView foto) {
        reference.child(Jembatan).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
