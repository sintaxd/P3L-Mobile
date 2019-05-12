package kel1.com.simato_mobile.Model;

public class Model_DetilPengadaanSparepart {
    Integer id_detilPengadaanSpareapart, id_pengadaan_fk, id_sparepartCabang_fk, satuan_pengadaan, totalBarang_datang;
    Double sub_total_sparepart, hargaBeli_sparepart;
    String nama_sparepart;

    public Model_DetilPengadaanSparepart(Integer id_detilPengadaanSpareapart, Integer id_pengadaan_fk, Integer id_sparepartCabang_fk, Integer satuan_pengadaan, Integer totalBarang_datang, Double sub_total_sparepart, Double hargaBeli_sparepart, String nama_sparepart) {
        this.id_detilPengadaanSpareapart = id_detilPengadaanSpareapart;
        this.id_pengadaan_fk = id_pengadaan_fk;
        this.id_sparepartCabang_fk = id_sparepartCabang_fk;
        this.satuan_pengadaan = satuan_pengadaan;
        this.totalBarang_datang = totalBarang_datang;
        this.sub_total_sparepart = sub_total_sparepart;
        this.hargaBeli_sparepart = hargaBeli_sparepart;
        this.nama_sparepart = nama_sparepart;
    }

    public Model_DetilPengadaanSparepart(Integer satuan_pengadaan, Double sub_total_sparepart, Double hargaBeli_sparepart, String nama_sparepart) {
        this.satuan_pengadaan = satuan_pengadaan;
        this.sub_total_sparepart = sub_total_sparepart;
        this.hargaBeli_sparepart = hargaBeli_sparepart;
        this.nama_sparepart = nama_sparepart;
    }

    public Double getHargaBeli_sparepart() {
        return hargaBeli_sparepart;
    }

    public void setHargaBeli_sparepart(Double hargaBeli_sparepart) {
        this.hargaBeli_sparepart = hargaBeli_sparepart;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public Integer getId_detilPengadaanSpareapart() {
        return id_detilPengadaanSpareapart;
    }

    public void setId_detilPengadaanSpareapart(Integer id_detilPengadaanSpareapart) {
        this.id_detilPengadaanSpareapart = id_detilPengadaanSpareapart;
    }

    public Integer getId_pengadaan_fk() {
        return id_pengadaan_fk;
    }

    public void setId_pengadaan_fk(Integer id_pengadaan_fk) {
        this.id_pengadaan_fk = id_pengadaan_fk;
    }

    public Integer getId_sparepartCabang_fk() {
        return id_sparepartCabang_fk;
    }

    public void setId_sparepartCabang_fk(Integer id_sparepartCabang_fk) {
        this.id_sparepartCabang_fk = id_sparepartCabang_fk;
    }

    public Integer getSatuan_pengadaan() {
        return satuan_pengadaan;
    }

    public void setSatuan_pengadaan(Integer satuan_pengadaan) {
        this.satuan_pengadaan = satuan_pengadaan;
    }

    public Integer getTotalBarang_datang() {
        return totalBarang_datang;
    }

    public void setTotalBarang_datang(Integer totalBarang_datang) {
        this.totalBarang_datang = totalBarang_datang;
    }

    public Double getSub_total_sparepart() {
        return sub_total_sparepart;
    }

    public void setSub_total_sparepart(Double sub_total_sparepart) {
        this.sub_total_sparepart = sub_total_sparepart;
    }
}
