package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_TransaksiPenjualan;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_TransaksiPenjualan {

    String baseURL = "http://192.168.94.52:8000";
    //String baseURL = "http://simato.jasonfw.com/";


    // ----------------------- R E A D ----------------------- //

    @GET("api/transaksiPenjualan")
    Call<LD_TransaksiPenjualan> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT(" api/transaksiPenjualan/update_sinta/{id}")
    @FormUrlEncoded
    Call<ResponseBody>update_sinta(
            @Field("diskon")Double diskon,
            @Field("total_transaksi")Double total_transaksi,
            @Field("status_transaksi")String status_transaksi,
            @Path("id") Integer id);

    // --------------------- U P D A T E --------------------- //
}
