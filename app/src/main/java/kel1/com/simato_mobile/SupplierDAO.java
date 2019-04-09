package kel1.com.simato_mobile;

public class SupplierDAO {
    String nama_supplier, alamat_supplier, noTelp_supplier, nama_sales, noTelp_sales;

    public SupplierDAO(String nama_supplier, String alamat_supplier, String noTelp_supplier, String nama_sales, String noTelp_sales) {
        this.nama_supplier = nama_supplier;
        this.alamat_supplier = alamat_supplier;
        this.noTelp_supplier = noTelp_supplier;
        this.nama_sales = nama_sales;
        this.noTelp_sales = noTelp_sales;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public String getAlamat_supplier() {
        return alamat_supplier;
    }

    public void setAlamat_supplier(String alamat_supplier) {
        this.alamat_supplier = alamat_supplier;
    }

    public String getNoTelp_supplier() {
        return noTelp_supplier;
    }

    public void setNoTelp_supplier(String noTelp_supplier) {
        this.noTelp_supplier = noTelp_supplier;
    }

    public String getNama_sales() {
        return nama_sales;
    }

    public void setNama_sales(String nama_sales) {
        this.nama_sales = nama_sales;
    }

    public String getNoTelp_sales() {
        return noTelp_sales;
    }

    public void setNoTelp_sales(String noTelp_sales) {
        this.noTelp_sales = noTelp_sales;
    }
}
