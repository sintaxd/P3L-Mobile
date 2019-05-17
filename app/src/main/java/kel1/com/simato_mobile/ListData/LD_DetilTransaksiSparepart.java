package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_DetilTransaksiSparepart;
public class LD_DetilTransaksiSparepart {

    @SerializedName("data")
    @Expose
    private List<Model_DetilTransaksiSparepart> detiltransaksispareparts = new ArrayList<>();

    public List<Model_DetilTransaksiSparepart> getData() {
        return detiltransaksispareparts;
    }

    public void setData(List<Model_DetilTransaksiSparepart> data) {
        this.detiltransaksispareparts = data;
    }
}
