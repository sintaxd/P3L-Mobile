package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Konsumen;
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

public interface ApiClient_Konsumen {

    String baseURL = "http://192.168.1.25:8000";
    //String baseURL = "http://simato.jasonfw.com/";

    // --------------------- C R E A T E --------------------- //

    @POST("api/konsumen")
    @FormUrlEncoded
    Call<Model_Konsumen> create(
            @Field("nama_konsumen")String nama_konsumen,
            @Field("noTelp_konsumen")String noTelp_konsumen,
            @Field("alamat_konsumen")String alamat_konsumen);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/konsumen")
    Call<LD_Konsumen> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/konsumen/{id_konsumen}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("nama_konsumen")String nama_konsumen,
            @Field("noTelp_konsumen")String noTelp_konsumen,
            @Field("alamat_konsumen")String alamat_konsumen,
            @Path("id_konsumen") Integer id_konsumen);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/konsumen/{id_konsumen}")
    Call<ResponseBody>delete(@Path("id_konsumen") Integer id_konsumen);

    // --------------------- D E L E T E --------------------- //
}
