package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;

public class LD_PengadaanSparepart {
    @SerializedName("data")
    @Expose
    private List<Model_PengadaanSparepart> pengadaanspareparts = new ArrayList<>();

    public List<Model_PengadaanSparepart> getData() {
        return pengadaanspareparts;
    }

    public void setData(List<Model_PengadaanSparepart> data) {
        this.pengadaanspareparts = data;
    }
}
