package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_SparepartBengkel;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient_SparepartBengkel {

    String baseURL = "http://192.168.0.100:8000";
    //String baseURL = "http://simato.jasonfw.com/";


    // ----------------------- R E A D ----------------------- //

    // Show By Cabang
    @GET("api/sparepartCabang/showByCabang/{id}")
    Call<LD_SparepartBengkel> showByCabang(
            @Path("id") Integer id);

   // Show By Stok Sisa Asc
    @GET("api/sparepartCabang/sortByStokSisaAsc/{id}")
    Call<LD_SparepartBengkel> sortByStokSisaAsc(
            @Path("id") Integer id);

    // Show By Stok Sisa Desc
    @GET("api/sparepartCabang/sortByStokSisaDesc/{id}")
    Call<LD_SparepartBengkel> sortByStokSisaDesc(
            @Path("id") Integer id);

    // Show By Harga Jual Sisa Asc
    @GET("api/sparepartCabang/sortByHargaAsc/{id}")
    Call<LD_SparepartBengkel> sortByHargaAsc(
            @Path("id") Integer id);

    // Show By Harga Jual Desc
    @GET("api/sparepartCabang/sortByHargaDesc/{id}")
    Call<LD_SparepartBengkel> sortByHargaDesc(
            @Path("id") Integer id);


    // ----------------------- R E A D ----------------------- //

}
