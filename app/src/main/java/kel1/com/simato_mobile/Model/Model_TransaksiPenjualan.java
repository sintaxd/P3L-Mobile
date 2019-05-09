package kel1.com.simato_mobile.Model;

public class Model_TransaksiPenjualan {
    Integer id_transaksi, id_cabang_fk;
    String kode_transaksi, tgl_transaksi, status_transaksi, nama_cabang;
    Double diskon,total_transaksi;

    public Model_TransaksiPenjualan(Integer id_transaksi, Integer id_cabang_fk, String kode_transaksi, String tgl_transaksi, String status_transaksi, Double diskon, Double total_transaksi, String nama_cabang) {
        this.id_transaksi = id_transaksi;
        this.id_cabang_fk = id_cabang_fk;
        this.kode_transaksi = kode_transaksi;
        this.tgl_transaksi = tgl_transaksi;
        this.status_transaksi = status_transaksi;
        this.diskon = diskon;
        this.total_transaksi = total_transaksi;
        this.nama_cabang = nama_cabang;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
    }

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getId_cabang_fk() {
        return id_cabang_fk;
    }

    public void setId_cabang_fk(Integer id_cabang_fk) {
        this.id_cabang_fk = id_cabang_fk;
    }

    public String getKode_transaksi() {
        return kode_transaksi;
    }

    public void setKode_transaksi(String kode_transaksi) {
        this.kode_transaksi = kode_transaksi;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(String tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public void setStatus_transaksi(String status_transaksi) {
        this.status_transaksi = status_transaksi;
    }

    public Double getDiskon() {
        return diskon;
    }

    public void setDiskon(Double diskon) {
        this.diskon = diskon;
    }

    public Double getTotal_transaksi() {
        return total_transaksi;
    }

    public void setTotal_transaksi(Double total_transaksi) {
        this.total_transaksi = total_transaksi;
    }
}
