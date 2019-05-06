package kel1.com.simato_mobile.Model;

public class Model_JasaService {
    Integer id_jasaService;
    String nama_jasaService;
    Double harga_jasaService;

    public Model_JasaService(Integer id_jasaService, String nama_jasaService, Double harga_jasaService) {
        this.id_jasaService = id_jasaService;
        this.nama_jasaService = nama_jasaService;
        this.harga_jasaService = harga_jasaService;
    }

    public Integer getId_jasaService() {
        return id_jasaService;
    }

    public void setId_jasaService(Integer id_jasaService) {
        this.id_jasaService = id_jasaService;
    }

    public String getNama_jasaService() {
        return nama_jasaService;
    }

    public void setNama_jasaService(String nama_jasaService) {
        this.nama_jasaService = nama_jasaService;
    }

    public Double getHarga_jasaService() {
        return harga_jasaService;
    }

    public void setHarga_jasaService(Double harga_jasaService) {
        this.harga_jasaService = harga_jasaService;
    }
}
