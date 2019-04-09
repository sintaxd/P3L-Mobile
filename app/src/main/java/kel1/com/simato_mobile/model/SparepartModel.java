package kel1.com.simato_mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.SparepartDAO;

public class SparepartModel {
    @SerializedName("data")
    @Expose
    private List<SparepartDAO> spareparts = new ArrayList<>();

    public List<SparepartDAO> getData() {
        return spareparts ;
    }

    public void setData(List<SparepartDAO> data) {
        this.spareparts = data;
    }
}
