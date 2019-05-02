package kel1.com.simato_mobile.ListData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.Model.Model_MotorKonsumen;

public class LD_MotorKonsumen {

    @SerializedName("data")
    @Expose
    private List<Model_MotorKonsumen> motorkonsumens = new ArrayList<>();

    public List<Model_MotorKonsumen> getData() {
        return motorkonsumens;
    }

    public void setData(List<Model_MotorKonsumen> data) {
        this.motorkonsumens = data;
    }
}
