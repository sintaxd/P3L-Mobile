package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Pegawai;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient_Pegawai {

    String baseURL = "http://192.168.94.52:8000";
    //String baseURL = "http://simato.jasonfw.com/";

    // ----------------------- R E A D ----------------------- //

    @GET("api/pegawai")
    Call<LD_Pegawai> show();

    // ----------------------- R E A D ----------------------- //

}
