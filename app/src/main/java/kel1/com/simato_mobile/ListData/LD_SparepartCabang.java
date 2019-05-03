package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_SparepartCabang;

public class LD_SparepartCabang {
    @SerializedName("data")
    @Expose
    private List<Model_SparepartCabang> sparepartcabangs = new ArrayList<>();

    public List<Model_SparepartCabang> getData() {
        return sparepartcabangs ;
    }

    public void setData(List<Model_SparepartCabang> data) {
        this.sparepartcabangs = data;
    }
}
