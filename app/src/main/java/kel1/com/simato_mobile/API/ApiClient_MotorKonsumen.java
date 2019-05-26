package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.ListData.LD_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_MotorKonsumen {

    String baseURL = "http://192.168.94.40:8000";
    //String baseURL = "http://simato.jasonfw.com/";
    // --------------------- C R E A T E --------------------- //

    @POST("api/motorKonsumen")
    @FormUrlEncoded
    Call<Model_MotorKonsumen> create(
            @Field("id_motor_fk")Integer id_motor_fk,
            @Field("id_konsumen_fk")Integer id_konsumen_fk,
            @Field("plat_motorKonsumen")String plat_motorKonsumen);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/motorKonsumen")
    Call<LD_MotorKonsumen> show();

    @GET("api/motorKonsumen/{id_konsumen_fk}")
    Call<LD_MotorKonsumen> showById(
            @Path("id_konsumen_fk")Integer id_konsumen_fk);


    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/motorKonsumen/{id_motorKonsumen}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("id_motor_fk")Integer id_motor_fk,
            @Field("id_konsumen_fk")Integer id_konsumen_fk,
            @Field("plat_motorKonsumen")String plat_motorKonsumen,
            @Path("id_motorKonsumen")Integer id_motorKonsumen);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

    @DELETE("api/motorKonsumen/{id_motorKonsumen}")
    Call<ResponseBody>delete(@Path("id_motorKonsumen") Integer id_motorKonsumen);

    // --------------------- D E L E T E --------------------- //
}
