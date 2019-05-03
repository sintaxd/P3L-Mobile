package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_Cabang;

public class LD_Cabang {
    @SerializedName("data")
    @Expose
    private List<Model_Cabang> cabangs = new ArrayList<>();

    public List<Model_Cabang> getData() {
        return cabangs;
    }

    public void setData(List<Model_Cabang> data) {
        this.cabangs = data;
    }
}
