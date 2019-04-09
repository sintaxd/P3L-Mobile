package kel1.com.simato_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.SupplierDAO;

public class SupplierModel {
    @SerializedName("data")
    @Expose
    private List<SupplierDAO> suppliers = new ArrayList<>();

    public List<SupplierDAO> getData() {
        return suppliers;
    }

    public void setData(List<SupplierDAO> data) {
        this.suppliers = data;
    }
}
