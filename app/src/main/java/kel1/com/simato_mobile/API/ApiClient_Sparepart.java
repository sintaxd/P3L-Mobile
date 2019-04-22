package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_Sparepart;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClient_Sparepart {
    //API CLIENT SPAREPART
    @GET("api/sparepart")
    Call<LD_Sparepart> show();

    @POST("api/sparepart")
    @FormUrlEncoded
    Call<LD_Sparepart> create(
            @Field("nama_sparepart")String nama_sparepart,
            @Field("merk_sparepart")String merk_sparepart,
            @Field("kode_sparepart")String kode_sparepart,
            @Field("tipe_sparepart")String tipe_sparepart);
}
