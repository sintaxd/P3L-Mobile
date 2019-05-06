package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Sparepart;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiClient_Sparepart {

    String baseURL = "http://192.168.1.25:8000";
    //String baseURL = "http://simato.jasonfw.com/";

    // --------------------- C R E A T E --------------------- //

    @POST("api/sparepart/store")
    @Multipart
    Call<ResponseBody> create(
            @Part MultipartBody.Part gambar_sparepart,
            @Part("kode_sparepart") RequestBody kode_sparepart,
            @Part("nama_sparepart") RequestBody nama_sparepart,
            @Part("merk_sparepart") RequestBody merk_sparepart,
            @Part("tipe_sparepart") RequestBody tipe_sparepart);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/sparepart")
    Call<LD_Sparepart> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @POST("api/sparepart/{kode}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("gambar_sparepart") String gambar_sparepart,
            @Field("nama_sparepart")String nama_sparepart,
            @Field("merk_sparepart")String merk_sparepart,
            @Field("tipe_sparepart")String tipe_sparepart,
            @Path("kode")String kode);

    @POST("api/sparepart/updateImageMobile/{kode}")
    @Multipart
    Call<ResponseBody> updateImageMobile(
            @Part MultipartBody.Part gambar_sparepart,
            @Path("kode")String kode);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/sparepart/{kode_sparepart}")
    Call<ResponseBody>delete(@Path("kode_sparepart") String kode_sparepart);

    // --------------------- D E L E T E --------------------- //

}
