package com.example.fbasea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.fbasea.Database.Teman;
import com.example.fbasea.adapter.AdapterLihatTeman;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatTeman extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Teman> dataTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_teman);

        //inisialisasi untuk RecyclerView beserta komponennya
        rvView = (RecyclerView) findViewById(R.id.rv_utama);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        //inisialisasi dan membaca firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //mengambil data dari realtime database dalam firebase
        databaseReference.child("Teman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // saat ada data baru, masukkan datanya ke arraylist
                dataTeman = new ArrayList<>();
                for (DataSnapshot daftarDS:snapshot.getChildren()){

                    // saat ada data baru, masukkan datanya ke arraylist
                    Teman tmn = daftarDS.getValue(Teman.class);
                    tmn.setKode(daftarDS.getKey());

                    // teman.setNama (snapshot.getkey());
                    // menambahkan object barang yang sudah di mapping ke dalam arraylist

                    dataTeman.add(tmn);
                }

                //menambahkan object barang yang sudah dimapping ke dalam arraylist
                adapter = new AdapterLihatTeman(dataTeman, LihatTeman.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // kode in akan dipanggil ketika ada error dan pengambilan data gagal dan memprint errornya ke logcat
                System.out.println(error.getDetails()+""+error.getMessage());

            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, LihatTeman.class);
    }
}
