package com.example.fbasea.Database;

import java.io.Serializable;

public class Teman implements Serializable {
    String kode;
    String nama;
    String telepon;

    public Teman() {
    }

    public Teman(String nama, String telepon) {
        this.nama = nama;
        this.telepon = telepon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }


    @Override
    public String toString() {
        return "Teman{" +
                "kode='" + kode + '\'' +
                ", nama='" + nama + '\'' +
                ", telepon='" + telepon + '\'' +
                '}';
    }
}
