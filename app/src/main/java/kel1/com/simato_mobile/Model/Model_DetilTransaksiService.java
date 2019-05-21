package kel1.com.simato_mobile.Model;

public class Model_DetilTransaksiService {
    Integer id_detilTransaksiService, id_transaksi_fk, id_jasaService_fk, id_motorKonsumen_fk;
    Double subTotal_service, harga_jasaService;
    String status_service, nama_jasaService;

    public Model_DetilTransaksiService(Integer id_detilTransaksiService, Integer id_transaksi_fk, Integer id_jasaService_fk, Integer id_motorKonsumen_fk, Double subTotal_service, Double harga_jasaService, String status_service, String nama_jasaService) {
        this.id_detilTransaksiService = id_detilTransaksiService;
        this.id_transaksi_fk = id_transaksi_fk;
        this.id_jasaService_fk = id_jasaService_fk;
        this.id_motorKonsumen_fk = id_motorKonsumen_fk;
        this.subTotal_service = subTotal_service;
        this.harga_jasaService = harga_jasaService;
        this.status_service = status_service;
        this.nama_jasaService = nama_jasaService;
    }

    public Model_DetilTransaksiService(Double subTotal_service, Integer id_motorKonsumen_fk, Integer id_jasaService_fk, String nama_jasaService, Double harga_jasaService) {
        this.id_jasaService_fk = id_jasaService_fk;
        this.id_motorKonsumen_fk = id_motorKonsumen_fk;
        this.harga_jasaService = harga_jasaService;
        this.subTotal_service = subTotal_service;
        this.nama_jasaService = nama_jasaService;
    }

    public Integer getId_detilTransaksiService() {
        return id_detilTransaksiService;
    }

    public void setId_detilTransaksiService(Integer id_detilTransaksiService) {
        this.id_detilTransaksiService = id_detilTransaksiService;
    }

    public Integer getId_transaksi_fk() {
        return id_transaksi_fk;
    }

    public void setId_transaksi_fk(Integer id_transaksi_fk) {
        this.id_transaksi_fk = id_transaksi_fk;
    }

    public Integer getId_jasaService_fk() {
        return id_jasaService_fk;
    }

    public void setId_jasaService_fk(Integer id_jasaService_fk) {
        this.id_jasaService_fk = id_jasaService_fk;
    }

    public Integer getId_motorKonsumen_fk() {
        return id_motorKonsumen_fk;
    }

    public void setId_motorKonsumen_fk(Integer id_motorKonsumen_fk) {
        this.id_motorKonsumen_fk = id_motorKonsumen_fk;
    }

    public Double getSubTotal_service() {
        return subTotal_service;
    }

    public void setSubTotal_service(Double subTotal_service) {
        this.subTotal_service = subTotal_service;
    }

    public Double getHarga_jasaService() {
        return harga_jasaService;
    }

    public void setHarga_jasaService(Double harga_jasaService) {
        this.harga_jasaService = harga_jasaService;
    }

    public String getStatus_service() {
        return status_service;
    }

    public void setStatus_service(String status_service) {
        this.status_service = status_service;
    }

    public String getNama_jasaService() {
        return nama_jasaService;
    }

    public void setNama_jasaService(String nama_jasaService) {
        this.nama_jasaService = nama_jasaService;
    }
}
