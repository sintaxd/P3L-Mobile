package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_TransaksiPenjualan;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_TransaksiPenjualan {

    String baseURL = "http://192.168.94.74:8000";
    //String baseURL = "http://simato.jasonfw.com/";

    // --------------------- C R E A T E --------------------- //

    @POST("api/transaksiPenjualanSparepart")
    @FormUrlEncoded
    Call<ResponseBody> create(
            @Field ("tipe_transaksi") String tipe_transaksi,
            @Field("id_cabang_fk")Integer id_supplier_fk,
            @Field("total_transaksi")Double total_transaksi,
            @Field("id_montir") Integer id_montir,
            @Field("id_cs") Integer id_cs);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/transaksiPenjualan")
    Call<LD_TransaksiPenjualan> show();

    @GET("api/transaksiPenjualan/showByStatusTransaksi/{status_transaksi}")
    Call<LD_TransaksiPenjualan> showByStatusTransaksi(
            @Path("status_transaksi") Integer status_transaksi
    );


    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/transaksiPenjualan/update_sinta/{id}")
    @FormUrlEncoded
    Call<ResponseBody>update_sinta(
            @Field("diskon")Double diskon,
            @Field("total_transaksi")Double total_transaksi,
            @Path("id") Integer id);

    @PUT("api/transaksiPenjualan/update_status_transaksi_sinta/{id}")
    Call<ResponseBody>update_status_transaksi_sinta(
            @Path("id") Integer id);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/deleteTransaksiPenjualan/{id}")

    Call<ResponseBody>delete(
            @Path("id") Integer id);

    // --------------------- D E L E T E --------------------- //
}
