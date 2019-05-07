package kel1.com.simato_mobile.Model;

public class Model_SparepartBengkel {
    Integer id_cabang_fk,stokSisa_sparepart,id_sparepartCabang;
    Double hargaJual_sparepart;
    String kode_sparepart_fk,nama_sparepart,nama_cabang, merk_sparepart, tipe_sparepart, gambar_sparepart;

    public Model_SparepartBengkel(Integer id_cabang_fk, Integer stokSisa_sparepart, Integer id_sparepartCabang, Double hargaJual_sparepart, String kode_sparepart_fk, String nama_sparepart, String nama_cabang, String merk_sparepart, String tipe_sparepart, String gambar_sparepart) {
        this.id_cabang_fk = id_cabang_fk;
        this.stokSisa_sparepart = stokSisa_sparepart;
        this.id_sparepartCabang = id_sparepartCabang;
        this.hargaJual_sparepart = hargaJual_sparepart;
        this.kode_sparepart_fk = kode_sparepart_fk;
        this.nama_sparepart = nama_sparepart;
        this.nama_cabang = nama_cabang;
        this.merk_sparepart = merk_sparepart;
        this.tipe_sparepart = tipe_sparepart;
        this.gambar_sparepart = gambar_sparepart;
    }

    public String getGambar_sparepart() {
        return gambar_sparepart;
    }

    public void setGambar_sparepart(String gambar_sparepart) {
        this.gambar_sparepart = gambar_sparepart;
    }

    public Integer getId_cabang_fk() {
        return id_cabang_fk;
    }

    public void setId_cabang_fk(Integer id_cabang_fk) {
        this.id_cabang_fk = id_cabang_fk;
    }

    public Integer getstokSisa_sparepart() {
        return stokSisa_sparepart;
    }

    public void setstokSisa_sparepart(Integer stokSisa_sparepart) {
        this.stokSisa_sparepart = stokSisa_sparepart;
    }

    public Integer getId_sparepartCabang() {
        return id_sparepartCabang;
    }

    public void setId_sparepartCabang(Integer id_sparepartCabang) {
        this.id_sparepartCabang = id_sparepartCabang;
    }

    public Double getHargaJual_sparepart() {
        return hargaJual_sparepart;
    }

    public void setHargaJual_sparepart(Double hargaJual_sparepart) {
        this.hargaJual_sparepart = hargaJual_sparepart;
    }

    public String getKode_sparepart_fk() {
        return kode_sparepart_fk;
    }

    public void setKode_sparepart_fk(String kode_sparepart_fk) {
        this.kode_sparepart_fk = kode_sparepart_fk;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
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
}
