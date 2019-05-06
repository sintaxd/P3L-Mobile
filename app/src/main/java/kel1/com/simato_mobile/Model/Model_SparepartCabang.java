package kel1.com.simato_mobile.Model;

public class Model_SparepartCabang {
    Integer id_cabang_fk,stokMin_sparepart,stokSisa_sparepart,id_sparepartCabang;
    Double hargaBeli_sparepart,hargaJual_sparepart;
    String kode_sparepart_fk,letak_sparepart, nama_sparepart,nama_cabang;

    public Model_SparepartCabang(Integer id_cabang_fk, Integer stokMin_sparepart, Integer stokSisa_sparepart, Integer id_sparepartCabang, Double hargaBeli_sparepart, Double hargaJual_sparepart, String kode_sparepart_fk, String letak_sparepart, String nama_sparepart, String nama_cabang) {
        this.id_cabang_fk = id_cabang_fk;
        this.stokMin_sparepart = stokMin_sparepart;
        this.stokSisa_sparepart = stokSisa_sparepart;
        this.id_sparepartCabang = id_sparepartCabang;
        this.hargaBeli_sparepart = hargaBeli_sparepart;
        this.hargaJual_sparepart = hargaJual_sparepart;
        this.kode_sparepart_fk = kode_sparepart_fk;
        this.letak_sparepart = letak_sparepart;
        this.nama_sparepart = nama_sparepart;
        this.nama_cabang = nama_cabang;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
    }

    public String getNama_sparepart() {
        return nama_sparepart;
    }

    public void setNama_sparepart(String nama_sparepart) {
        this.nama_sparepart = nama_sparepart;
    }

    public Integer getId_sparepartCabang() {
        return id_sparepartCabang;
    }

    public void setId_sparepartCabang(Integer id_sparepartCabang) {
        this.id_sparepartCabang = id_sparepartCabang;
    }

    public Integer getId_cabang_fk() {
        return id_cabang_fk;
    }

    public void setId_cabang_fk(Integer id_cabang_fk) {
        this.id_cabang_fk = id_cabang_fk;
    }

    public Integer getStokMin_sparepart() {
        return stokMin_sparepart;
    }

    public void setStokMin_sparepart(Integer stokMin_sparepart) {
        this.stokMin_sparepart = stokMin_sparepart;
    }

    public Integer getStokSisa_sparepart() {
        return stokSisa_sparepart;
    }

    public void setStokSisa_sparepart(Integer stokSisa_sparepart) {
        this.stokSisa_sparepart = stokSisa_sparepart;
    }

    public Double getHargaBeli_sparepart() {
        return hargaBeli_sparepart;
    }

    public void setHargaBeli_sparepart(Double hargaBeli_sparepart) {
        this.hargaBeli_sparepart = hargaBeli_sparepart;
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

    public String getLetak_sparepart() {
        return letak_sparepart;
    }

    public void setLetak_sparepart(String letak_sparepart) {
        this.letak_sparepart = letak_sparepart;
    }
}
