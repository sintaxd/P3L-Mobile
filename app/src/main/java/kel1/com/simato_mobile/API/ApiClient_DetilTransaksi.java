package kel1.com.simato_mobile.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClient_DetilTransaksi {


    String baseURL = "http://192.168.94.74:8000";
    //String baseURL = "http://simato.jasonfw.com/";


    // --------------------- C R E A T E --------------------- //

    @POST("api/createDetilTransaksiService")
    @FormUrlEncoded
    Call<ResponseBody> createDetilTransaksiService(
            @Field("id_transaksi_fk") Integer id_transaksi_fk,
            @Field("id_jasaService_fk") Integer id_jasaService_fk,
            @Field("id_motorKonsumen_fk")Integer id_motorKonsumen_fk,
            @Field("subTotal_service")Double subTotal_service);

    @POST("api/createDetilTransaksiSparepart")
    @FormUrlEncoded
    Call<ResponseBody> createDetilTransaksiSparepart(
            @Field("id_transaksi_fk") Integer id_transaksi_fk,
            @Field("id_sparepartCabang_fk") Integer id_sparepartCabang_fk,
            @Field("id_konsumen_fk")Integer id_konsumen_fk,
            @Field("jumlahBeli_sparepart")Integer jumlahBeli_sparepart,
            @Field("subTotal_sparepart")Double subTotal_sparepart);

    // --------------------- C R E A T E --------------------- //
}
