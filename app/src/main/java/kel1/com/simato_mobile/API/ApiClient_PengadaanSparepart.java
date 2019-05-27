package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_PengadaanSparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient_PengadaanSparepart {

    String baseURL = "http://10.53.6.112:8000";
    //String baseURL = "http://simato.jasonfw.com/";
    // --------------------- C R E A T E --------------------- //

    @POST("api/pengadaanSparepart/createPengadaanSparepart")
    @FormUrlEncoded
    Call<ResponseBody> create(
            @Field("id_supplier_fk")Integer id_supplier_fk,
            @Field("id_cabang_fk")Integer id_cabang_fk,
            @Field("totalHarga_pengadaan")Double totalHarga_pengadaan);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/pengadaanSparepart")
    Call<LD_PengadaanSparepart> show();

    // ----------------------- R E A D ----------------------- //

    // --------------------- U P D A T E --------------------- //

    @PUT("api/pengadaanSparepart/{id_pengadaan}")
    @FormUrlEncoded
    Call<ResponseBody>update_mobile(
            @Field("id_supplier_fk")Integer id_supplier_fk,
            @Field("id_cabang_fk")Integer id_cabang_fk,
            @Field("totalHarga_pengadaan")Double totalHarga_pengadaan,
            @Path("id_pengadaan") Integer id_pengadaan);


    @PUT("api/pengadaanSparepart/verifikasi_pengadaan/{id_pengadaan}")
    Call<ResponseBody>verifikasi_pengadaan(
            @Path("id_pengadaan") Integer id_pengadaan);

    // --------------------- U P D A T E --------------------- //
    // --------------------- D E L E T E --------------------- //

    @DELETE("api/pengadaanSparepart/{id}")

    Call<ResponseBody>delete(@Path("id") Integer id);

    // --------------------- D E L E T E --------------------- //
}

