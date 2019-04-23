package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_Konsumen;

public class LD_Konsumen {
    @SerializedName("data")
    @Expose
    private List<Model_Konsumen> konsumens = new ArrayList<>();

    public List<Model_Konsumen> getData() {
        return konsumens;
    }

    public void setData(List<Model_Konsumen> data) {
        this.konsumens = data;
    }
}
