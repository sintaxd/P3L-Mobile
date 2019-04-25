package kel1.com.simato_mobile.Model;

public class Model_Motor {
    private Integer id_motor;
    String merk_motor,tipe_motor;

    public Model_Motor(Integer id_motor, String merk_motor, String tipe_motor) {
        this.id_motor = id_motor;
        this.merk_motor = merk_motor;
        this.tipe_motor = tipe_motor;
    }

    public Integer getId_motor() {
        return id_motor;
    }

    public void setId_motor(Integer id_motor) {
        this.id_motor = id_motor;
    }

    public String getMerk_motor() {
        return merk_motor;
    }

    public void setMerk_motor(String merk_motor) {
        this.merk_motor = merk_motor;
    }

    public String getTipe_motor() {
        return tipe_motor;
    }

    public void setTipe_motor(String tipe_motor) {
        this.tipe_motor = tipe_motor;
    }
}
