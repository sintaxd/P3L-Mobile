package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_DetilPengadaanSparepart;
import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.Model.Model_DetilPengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiClient_DetilPengadaanSparepart {

    String baseURL = "http://192.168.94.40:8000";
    //String baseURL = "http://simato.jasonfw.com/";

    // --------------------- C R E A T E --------------------- //

    @POST("api/detilPengadaanSparepart/createDetilPengadaan")
    @FormUrlEncoded
    Call<ResponseBody> createDetilPengadaan(
            @Field("id_pengadaan_fk") Integer id_pengadaan_fk,
            @Field("id_sparepartCabang_fk") Integer id_sparepartCabang_fk,
            @Field("satuan_pengadaan")Integer satuan_pengadaan,
            @Field("sub_total_sparepart")Double sub_total_sparepart);

    // --------------------- C R E A T E --------------------- //

    // ----------------------- R E A D ----------------------- //

    @GET("api/detilPengadaanSparepart")
    Call<LD_DetilPengadaanSparepart> show();

    @GET("api/detilPengadaanSparepart/showByIdPengadaan/{id}")
    Call<LD_DetilPengadaanSparepart> showByIdPengadaan(
            @Path("id") Integer id);
    // ----------------------- R E A D ----------------------- //
}
