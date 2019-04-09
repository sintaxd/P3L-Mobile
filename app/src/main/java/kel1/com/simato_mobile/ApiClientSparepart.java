package kel1.com.simato_mobile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiClientSparepart {
    //API CLIENT SPAREPART
    @GET("api/sparepart")
    Call<SparepartDAO> show();

    @POST("api/sparepart")
    @FormUrlEncoded
    Call<SparepartDAO> create(
            @Field("nama_sparepart")String nama_sparepart,
            @Field("merk_sparepart")String merk_sparepart,
            @Field("kode_sparepart")String kode_sparepart,
            @Field("tipe_sparepart")String tipe_sparepart);
}
