package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_Sparepart {

    String baseURL = "http://simato.jasonfw.com/";

    // --------------------- C R E A T E --------------------- //

    @POST("api/sparepart")
    @FormUrlEncoded
    Call<LD_Sparepart> create(
            @Field("nama_sparepart")String nama_sparepart,
            @Field("merk_sparepart")String merk_sparepart,
            @Field("kode_sparepart")String kode_sparepart,
            @Field("tipe_sparepart")String tipe_sparepart,
            @Field("gambar_sparepart")String gambar_sparepart);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/sparepart")
    Call<LD_Sparepart> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/sparepart/{kode}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("nama_sparepart")String nama_sparepart,
            @Field("merk_sparepart")String merk_sparepart,
            @Field("kode_sparepart")String kode_sparepart,
            @Field("tipe_sparepart")String tipe_sparepart,
            @Field("gambar_sparepart")String gambar_sparepart,
            @Field("kode_sparepart")String kode);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/sparepart/{kode_sparepart}")
    Call<ResponseBody>delete(@Path("kode_sparepart") String kode_sparepart);

    // --------------------- D E L E T E --------------------- //

}
