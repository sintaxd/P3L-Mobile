package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_SparepartBengkel;

public class LD_SparepartBengkel {
    @SerializedName("data")
    @Expose
    private List<Model_SparepartBengkel> sparepartbengkels = new ArrayList<>();

    public List<Model_SparepartBengkel> getData() {
        return sparepartbengkels ;
    }

    public void setData(List<Model_SparepartBengkel> data) {
        this.sparepartbengkels = data;
    }
}
