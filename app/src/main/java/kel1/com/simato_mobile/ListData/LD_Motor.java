package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_Motor;

public class LD_Motor {
    @SerializedName("data")
    @Expose
    private List<Model_Motor> motors = new ArrayList<>();

    public List<Model_Motor> getData() {
        return motors;
    }

    public void setData(List<Model_Motor> data) {
        this.motors = data;
    }
}
