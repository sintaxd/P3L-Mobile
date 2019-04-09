package kel1.com.simato_mobile;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient {

    //API CLIENT SUPPLIER
    @GET("api/supplier")
    Call<SupplierDAO> show();

    @POST("api/supplier")
    @FormUrlEncoded
    Call<SupplierDAO> create(
            @Field("nama_supplier")String nama_supplier,
            @Field("noTelp_supplier")String noTelp_supplier,
            @Field("alamat_supplier")String alamat_supplier,
            @Field("nama_sales")String nama_sales,
            @Field("noTelp_sales")String noTelp_sales);


}
