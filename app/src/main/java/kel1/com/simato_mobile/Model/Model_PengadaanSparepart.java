package kel1.com.simato_mobile.Model;

import java.util.Date;

public class Model_PengadaanSparepart {
    Integer id_supplier_fk,id_sparepartCabang_fk,satuan_pengadaan,totalBarang_datang;
    Date tgl_pengadaan, tgl_barangDatang;
    String status_pengadaan, statusCetak_pengadaan, nama_supplier;
    Double totalHarga_pengadaan;

    public Model_PengadaanSparepart(Integer id_supplier_fk, Integer id_sparepartCabang_fk, Integer satuan_pengadaan, Integer totalBarang_datang, Date tgl_pengadaan, Date tgl_barangDatang, String status_pengadaan, String statusCetak_pengadaan, String nama_supplier, Double totalHarga_pengadaan) {
        this.id_supplier_fk = id_supplier_fk;
        this.id_sparepartCabang_fk = id_sparepartCabang_fk;
        this.satuan_pengadaan = satuan_pengadaan;
        this.totalBarang_datang = totalBarang_datang;
        this.tgl_pengadaan = tgl_pengadaan;
        this.tgl_barangDatang = tgl_barangDatang;
        this.status_pengadaan = status_pengadaan;
        this.statusCetak_pengadaan = statusCetak_pengadaan;
        this.nama_supplier = nama_supplier;
        this.totalHarga_pengadaan = totalHarga_pengadaan;
    }

    public Integer getId_supplier_fk() {
        return id_supplier_fk;
    }

    public void setId_supplier_fk(Integer id_supplier_fk) {
        this.id_supplier_fk = id_supplier_fk;
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

    public Date getTgl_pengadaan() {
        return tgl_pengadaan;
    }

    public void setTgl_pengadaan(Date tgl_pengadaan) {
        this.tgl_pengadaan = tgl_pengadaan;
    }

    public Date getTgl_barangDatang() {
        return tgl_barangDatang;
    }

    public void setTgl_barangDatang(Date tgl_barangDatang) {
        this.tgl_barangDatang = tgl_barangDatang;
    }

    public String getStatus_pengadaan() {
        return status_pengadaan;
    }

    public void setStatus_pengadaan(String status_pengadaan) {
        this.status_pengadaan = status_pengadaan;
    }

    public String getStatusCetak_pengadaan() {
        return statusCetak_pengadaan;
    }

    public void setStatusCetak_pengadaan(String statusCetak_pengadaan) {
        this.statusCetak_pengadaan = statusCetak_pengadaan;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public Double getTotalHarga_pengadaan() {
        return totalHarga_pengadaan;
    }

    public void setTotalHarga_pengadaan(Double totalHarga_pengadaan) {
        this.totalHarga_pengadaan = totalHarga_pengadaan;
    }
}
