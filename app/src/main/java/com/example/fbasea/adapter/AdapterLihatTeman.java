package com.example.fbasea.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fbasea.Database.Teman;
import com.example.fbasea.MainActivity;
import com.example.fbasea.R;
import com.example.fbasea.TemanEdit;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterLihatTeman extends RecyclerView.Adapter<AdapterLihatTeman.ViewHolder> {
    private DatabaseReference database;
    private ArrayList<Teman> daftarTeman;
    private Context context;

    public AdapterLihatTeman(ArrayList<Teman> daftarTeman, Context context) {
        this.daftarTeman = daftarTeman;
        this.context = context;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView tvNama;
        ViewHolder(View itemView){
            super (itemView);
            tvNama=itemView.findViewById(R.id.tv_nama);
            database = FirebaseDatabase.getInstance().getReference();
        }
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teman,parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String kode, nama, telpon;
        kode = daftarTeman.get(position).getKode();
        nama = daftarTeman.get(position).getNama();
        telpon = daftarTeman.get(position).getTelepon();

        holder.tvNama.setText(nama);

        holder.tvNama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pm = new PopupMenu(v.getContext(), v);
                pm.inflate(R.menu.popup2);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Bundle bundle = new Bundle();
                                bundle.putString("Kunci1", kode);
                                bundle.putString("Kunci2", nama);
                                bundle.putString("Kunci3", telpon);

                                Intent intent = new Intent(v.getContext(), TemanEdit.class);
                                intent.putExtras(bundle);
                                v.getContext().startActivity(intent);

                                break;
                            case R.id.hapus:
                                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                                alertDlg.setTitle("Yakin Data " + nama + "akan dihapus?");
                                alertDlg.setMessage("Tekan 'Ya' untuk mengahapus").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DeleteData(kode);
                                        Toast.makeText(v.getContext(), "Data" + nama + "berhasil dihapus", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                                        v.getContext().startActivity(intent);
                                    }
                                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog aDlg = alertDlg.create();
                                aDlg.show();
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return daftarTeman.size();
    }

    public void DeleteData(String kode){
        if (database !=null){
            database.child("Teman").child(kode).removeValue();
        }
    }
}
