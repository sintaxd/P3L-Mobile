package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_DetilPengadaanSparepart;
public class LD_DetilPengadaanSparepart {
    @SerializedName("data")
    @Expose
    private List<Model_DetilPengadaanSparepart> detilpengadaanspareparts = new ArrayList<>();

    public List<Model_DetilPengadaanSparepart> getData() {
        return detilpengadaanspareparts;
    }

    public void setData(List<Model_DetilPengadaanSparepart> data) {
        this.detilpengadaanspareparts = data;
    }
}
