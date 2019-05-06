package kel1.com.simato_mobile.Model;

public class Model_MotorKonsumen {
    private Integer id_motorKonsumen, id_motor_fk, id_konsumen_fk;
    String plat_motorKonsumen, nama_konsumen, tipe_motor;

    public Model_MotorKonsumen(Integer id_motorKonsumen, Integer id_motor_fk, Integer id_konsumen_fk, String plat_motorKonsumen, String nama_konsumen, String tipe_motor) {
        this.id_motorKonsumen = id_motorKonsumen;
        this.id_motor_fk = id_motor_fk;
        this.id_konsumen_fk = id_konsumen_fk;
        this.plat_motorKonsumen = plat_motorKonsumen;
        this.nama_konsumen = nama_konsumen;
        this.tipe_motor = tipe_motor;
    }

    public Integer getId_motorKonsumen() {
        return id_motorKonsumen;
    }

    public void setId_motorKonsumen(Integer id_motorKonsumen) {
        this.id_motorKonsumen = id_motorKonsumen;
    }

    public Integer getId_motor_fk() {
        return id_motor_fk;
    }

    public void setId_motor_fk(Integer id_motor_fk) {
        this.id_motor_fk = id_motor_fk;
    }

    public Integer getId_konsumen_fk() {
        return id_konsumen_fk;
    }

    public void setId_konsumen_fk(Integer id_konsumen_fk) {
        this.id_konsumen_fk = id_konsumen_fk;
    }

    public String getPlat_motorKonsumen() {
        return plat_motorKonsumen;
    }

    public void setPlat_motorKonsumen(String plat_motorKonsumen) {
        this.plat_motorKonsumen = plat_motorKonsumen;
    }

    public String getNama_konsumen() {
        return nama_konsumen;
    }

    public void setNama_konsumen(String nama_konsumen) {
        this.nama_konsumen = nama_konsumen;
    }

    public String getTipe_motor() {
        return tipe_motor;
    }

    public void setTipe_motor(String tipe_motor) {
        this.tipe_motor = tipe_motor;
    }
}
