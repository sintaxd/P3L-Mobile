package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_TransaksiPenjualan;

public class LD_TransaksiPenjualan {
    @SerializedName("data")
    @Expose
    private List<Model_TransaksiPenjualan> transaksipenjualan = new ArrayList<>();

    public List<Model_TransaksiPenjualan> getData() {
        return transaksipenjualan;
    }

    public void setData(List<Model_TransaksiPenjualan> data) {
        this.transaksipenjualan = data;
    }
}
