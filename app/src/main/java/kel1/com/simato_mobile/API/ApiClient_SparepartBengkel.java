package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_SparepartBengkel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient_SparepartBengkel {

    String baseURL = "http://192.168.94.52:8000/";
    //String baseURL = "http://simato.jasonfw.com/";


    // ----------------------- R E A D ----------------------- //

    @GET("api/sparepartCabang")
    Call<LD_SparepartBengkel> show();

    // ----------------------- R E A D ----------------------- //

}
