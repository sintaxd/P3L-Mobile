package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_SparepartBengkel;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_SparepartCabang {

 String baseURL = "http://192.168.94.52:8000";
 //String baseURL = "http://simato.jasonfw.com/";
    // --------------------- C R E A T E --------------------- //

    @POST("api/sparepartCabang")
    @FormUrlEncoded
    Call<Model_SparepartCabang> create(
            @Field("id_cabang_fk")Integer id_cabang_fk,
            @Field("kode_sparepart_fk")String kode_sparepart_fk,
            @Field("hargaBeli_sparepart")Double hargaBeli_sparepart,
            @Field("hargaJual_sparepart")Double hargaJual_sparepart,
            @Field("letak_sparepart")String letak_sparepart,
            @Field("stokMin_sparepart")Integer stokMin_sparepart,
            @Field("stokSisa_sparepart")Integer stokSisa_sparepart);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/sparepartCabang")
    Call<LD_SparepartCabang> show();

    @GET("api/sparepartCabang/showByCabang/{id}")
    Call<LD_SparepartCabang> showByCabang(
            @Path("id") Integer id);

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/sparepartCabang/{id}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("id_cabang_fk")Integer id_cabang_fk,
            @Field("kode_sparepart_fk")String kode_sparepart_fk,
            @Field("hargaBeli_sparepart")Double hargaBeli_sparepart,
            @Field("hargaJual_sparepart")Double hargaJual_sparepart,
            @Field("letak_sparepart")String letak_sparepart,
            @Field("stokMin_sparepart")Integer stokMin_sparepart,
            @Field("stokSisa_sparepart")Integer stokSisa_sparepart,
            @Path("id")Integer id);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/sparepartCabang/{id}")
    Call<ResponseBody>delete(@Path("id") Integer id);

    // --------------------- D E L E T E --------------------- //
}
