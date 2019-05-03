package kel1.com.simato_mobile.Model;

public class Model_Cabang {
    Integer id_cabang;
    String nama_cabang, alamat_cabang, noTelp_cabang;

    public Model_Cabang(Integer id_cabang, String nama_cabang, String alamat_cabang, String noTelp_cabang) {
        this.id_cabang = id_cabang;
        this.nama_cabang = nama_cabang;
        this.alamat_cabang = alamat_cabang;
        this.noTelp_cabang = noTelp_cabang;
    }

    public Integer getId_cabang() {
        return id_cabang;
    }

    public void setId_cabang(Integer id_cabang) {
        this.id_cabang = id_cabang;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
    }

    public String getAlamat_cabang() {
        return alamat_cabang;
    }

    public void setAlamat_cabang(String alamat_cabang) {
        this.alamat_cabang = alamat_cabang;
    }

    public String getNoTelp_cabang() {
        return noTelp_cabang;
    }

    public void setNoTelp_cabang(String noTelp_cabang) {
        this.noTelp_cabang = noTelp_cabang;
    }
}
