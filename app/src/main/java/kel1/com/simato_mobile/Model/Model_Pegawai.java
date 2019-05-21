package kel1.com.simato_mobile.Model;

public class Model_Pegawai {
    Integer id_pegawai, id_role_fk, id_cabang_fk;
    String nama_pegawai, alamat_pegawai, noTelp_pegawai,username_pegawai,password_pegawai;
    Double gaji_pegawai;

    public Model_Pegawai(Integer id_pegawai, Integer id_role_fk, Integer id_cabang_fk, String nama_pegawai, String alamat_pegawai, String noTelp_pegawai, String username_pegawai, String password_pegawai, Double gaji_pegawai) {
        this.id_pegawai = id_pegawai;
        this.id_role_fk = id_role_fk;
        this.id_cabang_fk = id_cabang_fk;
        this.nama_pegawai = nama_pegawai;
        this.alamat_pegawai = alamat_pegawai;
        this.noTelp_pegawai = noTelp_pegawai;
        this.username_pegawai = username_pegawai;
        this.password_pegawai = password_pegawai;
        this.gaji_pegawai = gaji_pegawai;
    }

    public Integer getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(Integer id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public Integer getId_role_fk() {
        return id_role_fk;
    }

    public void setId_role_fk(Integer id_role_fk) {
        this.id_role_fk = id_role_fk;
    }

    public Integer getId_cabang_fk() {
        return id_cabang_fk;
    }

    public void setId_cabang_fk(Integer id_cabang_fk) {
        this.id_cabang_fk = id_cabang_fk;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getAlamat_pegawai() {
        return alamat_pegawai;
    }

    public void setAlamat_pegawai(String alamat_pegawai) {
        this.alamat_pegawai = alamat_pegawai;
    }

    public String getNoTelp_pegawai() {
        return noTelp_pegawai;
    }

    public void setNoTelp_pegawai(String noTelp_pegawai) {
        this.noTelp_pegawai = noTelp_pegawai;
    }

    public String getUsername_pegawai() {
        return username_pegawai;
    }

    public void setUsername_pegawai(String username_pegawai) {
        this.username_pegawai = username_pegawai;
    }

    public String getPassword_pegawai() {
        return password_pegawai;
    }

    public void setPassword_pegawai(String password_pegawai) {
        this.password_pegawai = password_pegawai;
    }

    public Double getGaji_pegawai() {
        return gaji_pegawai;
    }

    public void setGaji_pegawai(Double gaji_pegawai) {
        this.gaji_pegawai = gaji_pegawai;
    }
}
