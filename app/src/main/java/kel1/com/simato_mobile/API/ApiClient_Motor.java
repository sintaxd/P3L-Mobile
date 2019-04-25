package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.Model.Model_Motor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_Motor {

    String baseURL = "http://simato.jasonfw.com/";
    // --------------------- C R E A T E --------------------- //

    @POST("api/motor")
    @FormUrlEncoded
    Call<Model_Motor> create(
            @Field("merk_motor")String merk_motor,
            @Field("tipe_motor")String tipe_motor);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/motor")
    Call<LD_Motor> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/motor/{id_motor}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("merk_motor")String merk_motor,
            @Field("tipe_motor")String tipe_motor,
            @Path("id_motor") Integer id_motor);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/motor/{id_motor}")
    Call<ResponseBody>delete(@Path("id_motor") Integer id_motor);

    // --------------------- D E L E T E --------------------- //
}
