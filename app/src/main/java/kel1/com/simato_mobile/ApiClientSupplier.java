package kel1.com.simato_mobile;

import com.google.gson.JsonObject;

import kel1.com.simato_mobile.model.SupplierModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClientSupplier {
    //  API CLIENT SUPPLIER //

    //  CREATE
    @POST("api/supplier")
    @FormUrlEncoded
    Call<SupplierModel> create(
            @Field("nama_supplier")String nama_supplier,
            @Field("noTelp_supplier")String noTelp_supplier,
            @Field("alamat_supplier")String alamat_supplier,
            @Field("nama_sales")String nama_sales,
            @Field("noTelp_sales")String noTelp_sales);

    //  READ
    @GET("api/supplier")
    Call<SupplierModel> show();

    //  UPDATE
    @PUT("api/supplier/{id_supplier}")
    @FormUrlEncoded
    Call<ResponseBody>update(
            @Field("nama_supplier")String nama_supplier,
            @Field("noTelp_supplier")String noTelp_supplier,
            @Field("alamat_supplier")String alamat_supplier,
            @Field("nama_sales")String nama_sales,
            @Field("noTelp_sales")String noTelp_sales,
            @Path("id_supplier") Integer id_supplier);

    //  DELETE
    @DELETE("api/supplier/{id}")
    Call<ResponseBody>delete(@Path("id") Integer id);

}
