package kel1.com.simato_mobile.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiClient_Login {

    String baseURL = "http://10.200.125.179:8000/";
    //String baseURL = "http://simato.jasonfw.com/";
    // ----------------------- L O G I N ----------------------- //
    @POST("api/pegawai/mobileauthenticate")
    @FormUrlEncoded
    Call<ResponseBody> mobileauthenticate(
            @Field("username_pegawai")String username,
            @Field("password_pegawai")String password);

    // ----------------------- L O G I N ----------------------- //
}
