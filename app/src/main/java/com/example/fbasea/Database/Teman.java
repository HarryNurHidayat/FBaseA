package com.example.fbasea.Database;

import java.io.Serializable;

public class Teman implements Serializable {
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

    @Override
    public String toString() {
        return "Teman{" +
                "nama='" + nama + '\'' +
                ", telepon='" + telepon + '\'' +
                '}';
    }
}
