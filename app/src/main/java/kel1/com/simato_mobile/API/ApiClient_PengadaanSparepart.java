package kel1.com.simato_mobile.API;

import kel1.com.simato_mobile.ListData.LD_PengadaanSparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient_PengadaanSparepart {

    // ----------------------- R E A D ----------------------- //

    @GET("api/pengadaanSparepart")
    Call<LD_PengadaanSparepart> show();

    // ----------------------- R E A D ----------------------- //
}
