package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_Sparepart;

public class LD_Sparepart {
    @SerializedName("data")
    @Expose
    private List<Model_Sparepart> spareparts = new ArrayList<>();

    public List<Model_Sparepart> getData() {
        return spareparts ;
    }

    public void setData(List<Model_Sparepart> data) {
        this.spareparts = data;
    }
}
