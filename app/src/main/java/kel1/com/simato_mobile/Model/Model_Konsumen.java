package kel1.com.simato_mobile.Model;

public class Model_Konsumen {
    private Integer id_konsumen;
    String nama_konsumen,noTelp_konsumen, alamat_konsumen;

    public Model_Konsumen(Integer id_konsumen, String nama_konsumen, String noTelp_konsumen, String alamat_konsumen) {
        this.id_konsumen = id_konsumen;
        this.nama_konsumen = nama_konsumen;
        this.noTelp_konsumen = noTelp_konsumen;
        this.alamat_konsumen = alamat_konsumen;
    }

    public Integer getId_konsumen() {
        return id_konsumen;
    }

    public void setId_konsumen(Integer id_konsumen) {
        this.id_konsumen = id_konsumen;
    }

    public String getNama_konsumen() {
        return nama_konsumen;
    }

    public void setNama_konsumen(String nama_konsumen) {
        this.nama_konsumen = nama_konsumen;
    }

    public String getNoTelp_konsumen() {
        return noTelp_konsumen;
    }

    public void setNoTelp_konsumen(String noTelp_konsumen) {
        this.noTelp_konsumen = noTelp_konsumen;
    }

    public String getAlamat_konsumen() {
        return alamat_konsumen;
    }

    public void setAlamat_konsumen(String alamat_konsumen) {
        this.alamat_konsumen = alamat_konsumen;
    }
}
