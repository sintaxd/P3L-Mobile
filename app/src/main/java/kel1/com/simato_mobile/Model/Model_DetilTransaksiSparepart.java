package kel1.com.simato_mobile.Model;

public class Model_DetilTransaksiSparepart {
Integer id_detilTransaksiSparepart, id_transaksi_fk, id_sparepartCabang_fk, id_konsumen_fk, jumlahBeli_sparepart;
Double subTotal_sparepart, harga_satuan;
String nama_sparepart, nama_konsumen;

    public Model_DetilTransaksiSparepart(Integer id_detilTransaksiSparepart, Integer id_transaksi_fk, Integer id_sparepartCabang_fk, Integer id_konsumen_fk, Integer jumlahBeli_sparepart, Double subTotal_sparepart, Double harga_satuan, String nama_sparepart, String nama_konsumen) {
        this.id_detilTransaksiSparepart = id_detilTransaksiSparepart;
        this.id_transaksi_fk = id_transaksi_fk;
        this.id_sparepartCabang_fk = id_sparepartCabang_fk;
        this.id_konsumen_fk = id_konsumen_fk;
        this.jumlahBeli_sparepart = jumlahBeli_sparepart;
        this.subTotal_sparepart = subTotal_sparepart;
        this.harga_satuan = harga_satuan;
        this.nama_sparepart = nama_sparepart;
        this.nama_konsumen = nama_konsumen;
    }

    public Model_DetilTransaksiSparepart(Integer jumlahBeli_sparepart, Double subTotal_sparepart, Double harga_satuan, String nama_sparepart) {
        this.jumlahBeli_sparepart = jumlahBeli_sparepart;
        this.subTotal_sparepart = subTotal_sparepart;
        this.harga_satuan = harga_satuan;
        this.nama_sparepart = nama_sparepart;
    }

    public Double getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(Double harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public Integer getId_detilTransaksiSparepart() {
        return id_detilTransaksiSparepart;
    }

    public void setId_detilTransaksiSparepart(Integer id_detilTransaksiSparepart) {
        this.id_detilTransaksiSparepart = id_detilTransaksiSparepart;
    }

    public Integer getId_transaksi_fk() {
        return id_transaksi_fk;
    }

    public void setId_transaksi_fk(Integer id_transaksi_fk) {
        this.id_transaksi_fk = id_transaksi_fk;
    }

    public Integer getId_sparepartCabang_fk() {
        return id_sparepartCabang_fk;
    }

    public void setId_sparepartCabang_fk(Integer id_sparepartCabang_fk) {
        this.id_sparepartCabang_fk = id_sparepartCabang_fk;
    }

    public Integer getId_konsumen_fk() {
        return id_konsumen_fk;
    }

    public void setId_konsumen_fk(Integer id_konsumen_fk) {
        this.id_konsumen_fk = id_konsumen_fk;
    }

    public Integer getJumlahBeli_sparepart() {
        return jumlahBeli_sparepart;
    }

    public void setJumlahBeli_sparepart(Integer jumlahBeli_sparepart) {
        this.jumlahBeli_sparepart = jumlahBeli_sparepart;
    }

    public Double getSubTotal_sparepart() {
        return subTotal_sparepart;
    }

    public void setSubTotal_sparepart(Double subTotal_sparepart) {
        this.subTotal_sparepart = subTotal_sparepart;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public String getNama_konsumen() {
        return nama_konsumen;
    }

    public void setNama_konsumen(String nama_konsumen) {
        this.nama_konsumen = nama_konsumen;
    }
}
