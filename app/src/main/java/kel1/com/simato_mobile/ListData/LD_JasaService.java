package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_JasaService;

public class LD_JasaService {

    @SerializedName("data")
    @Expose
    private List<Model_JasaService> jasaservices = new ArrayList<>();

    public List<Model_JasaService> getData() {
        return jasaservices;
    }

    public void setData(List<Model_JasaService> data) {
        this.jasaservices = data;
    }
}
