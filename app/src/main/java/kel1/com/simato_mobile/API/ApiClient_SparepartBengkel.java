package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_SparepartBengkel;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient_SparepartBengkel {

    String baseURL = "http://192.168.94.52:8000/";
    //String baseURL = "http://simato.jasonfw.com/";


    // ----------------------- R E A D ----------------------- //

    @GET("api/sparepartCabang")
    Call<LD_SparepartBengkel> show();

    @GET("api/sparepartCabang/showByCabang/{id}")
    Call<LD_SparepartBengkel> showByCabang(
            @Path("id") Integer id);

    // ----------------------- R E A D ----------------------- //

}
