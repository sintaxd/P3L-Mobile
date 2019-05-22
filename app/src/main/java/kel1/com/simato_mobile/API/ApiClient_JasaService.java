package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_JasaService;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient_JasaService {

    String baseURL = "http://192.168.0.184:8000";
    //String baseURL = "http://simato.jasonfw.com/";
    // ----------------------- R E A D ----------------------- //

    @GET("api/jasaService")
    Call<LD_JasaService> show();

    // ----------------------- R E A D ----------------------- //
}
