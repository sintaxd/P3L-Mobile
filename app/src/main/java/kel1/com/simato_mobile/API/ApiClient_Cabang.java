package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Cabang;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient_Cabang {

    String baseURL = "http://10.66.99.213:8000";
    //String baseURL = "http://simato.jasonfw.com/";

    // ----------------------- R E A D ----------------------- //

    @GET("api/cabang")
    Call<LD_Cabang> show();

    // ----------------------- R E A D ----------------------- //
}
