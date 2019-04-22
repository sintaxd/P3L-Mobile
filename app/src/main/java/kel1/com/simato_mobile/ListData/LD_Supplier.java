package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_Supplier;

public class LD_Supplier {
    @SerializedName("data")
    @Expose
    private List<Model_Supplier> suppliers = new ArrayList<>();

    public List<Model_Supplier> getData() {
        return suppliers;
    }

    public void setData(List<Model_Supplier> data) {
        this.suppliers = data;
    }
}
