package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Supplier;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_Supplier {

    String baseURL = "http://10.66.99.213:8000";
    //String baseURL = "http://simato.jasonfw.com/";
    // --------------------- C R E A T E --------------------- //

        @POST("api/supplier")
        @FormUrlEncoded
        Call<Model_Supplier> create(
                @Field("nama_supplier")String nama_supplier,
                @Field("noTelp_supplier")String noTelp_supplier,
                @Field("alamat_supplier")String alamat_supplier,
                @Field("nama_sales")String nama_sales,
                @Field("noTelp_sales")String noTelp_sales);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

        @GET("api/supplier")
        Call<LD_Supplier> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

        @PUT("api/supplier/{id_supplier}")
        @FormUrlEncoded
        Call<ResponseBody>update(
                @Field("nama_supplier")String nama_supplier,
                @Field("noTelp_supplier")String noTelp_supplier,
                @Field("alamat_supplier")String alamat_supplier,
                @Field("nama_sales")String nama_sales,
                @Field("noTelp_sales")String noTelp_sales,
                @Path("id_supplier") Integer id_supplier);

    // --------------------- U P D A T E --------------------- //

    // --------------------- D E L E T E --------------------- //

        @DELETE("api/supplier/{id}")
        Call<ResponseBody>delete(@Path("id") Integer id);

    // --------------------- D E L E T E --------------------- //

}
