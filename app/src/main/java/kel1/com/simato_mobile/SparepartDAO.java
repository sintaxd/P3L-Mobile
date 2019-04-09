package kel1.com.simato_mobile;

public class SparepartDAO {
    String nama_sparepart, merk_sparepart, tipe_sparepart, kode_sparepart;

    public SparepartDAO(String nama_sparepart, String merk_sparepart, String tipe_sparepart, String kode_sparepart) {
        this.nama_sparepart = nama_sparepart;
        this.merk_sparepart = merk_sparepart;
        this.tipe_sparepart = tipe_sparepart;
        this.kode_sparepart = kode_sparepart;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getMerk_sparepart() {
        return merk_sparepart;
    }

    public void setMerk_sparepart(String merk_sparepart) {
        this.merk_sparepart = merk_sparepart;
    }

    public String getTipe_sparepart() {
        return tipe_sparepart;
    }

    public void setTipe_sparepart(String tipe_sparepart) {
        this.tipe_sparepart = tipe_sparepart;
    }

    public String getKode_sparepart() {
        return kode_sparepart;
    }

    public void setKode_sparepart(String kode_sparepart) {
        this.kode_sparepart = kode_sparepart;
    }
}
