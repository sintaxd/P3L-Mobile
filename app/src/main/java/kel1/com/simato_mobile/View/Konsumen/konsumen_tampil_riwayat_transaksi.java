package kel1.com.simato_mobile.View.Konsumen;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.Adapter.Adapter_RiwayatTransaksi;
import kel1.com.simato_mobile.Adapter.Adapter_TransaksiPenjualan;
import kel1.com.simato_mobile.ListData.LD_TransaksiPenjualan;
import kel1.com.simato_mobile.Model.Model_TransaksiPenjualan;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tambah_transaksi_penjualan_sparepart;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tampil_transaksi_penjualan;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class konsumen_tampil_riwayat_transaksi extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_RiwayatTransaksi adapterRiwayatTransaksi;
    private List<Model_TransaksiPenjualan> mListTransaksiPenjualan = new ArrayList<>();
    Adapter_TransaksiPenjualan.RecyclerViewClickListener listener;
    TextInputEditText nama_motor;
    ImageView imgSearch;
    Integer tempIDKonsumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konsumen_tampil_riwayat_transaksi);

        nama_motor = findViewById(R.id.text_input_platMotor);
        imgSearch = findViewById(R.id.img_Search);

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKonsumenFunction();
                setRecycleViewTransaksiPenjualan();

            }
        });

        recyclerView = findViewById(R.id.recycler_view_tampil_riwayat_transaksi);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterRiwayatTransaksi);
    }
    public void addKonsumenFunction(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Konsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        ApiClient_Konsumen apiClientKonsumen = retrofit.create(ApiClient_Konsumen.class);
        Call<ResponseBody> konsumenDAOCall = apiClientKonsumen.showByName(nama_motor.getText().toString());
        konsumenDAOCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {

                    try {
                        JSONObject jsonresponse = new JSONObject(response.body().string());
                        Log.d("onResponse: ",response.body().toString());
                        String temp_nama = jsonresponse.getJSONObject("data").getString("nama_konsumen");
                        tempIDKonsumen = Integer.parseInt(jsonresponse.getJSONObject("data").getString("id_konsumen"));
                        Log.d("Nama: ",temp_nama);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (IOException i)
                    {
                        i.printStackTrace();
                    }
                    Toast.makeText(konsumen_tampil_riwayat_transaksi.this, "Data Konsumen ditemukan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                }
                Log.d("on respon : ",String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(konsumen_tampil_riwayat_transaksi.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setRecycleViewTransaksiPenjualan() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_TransaksiPenjualan.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_TransaksiPenjualan apiClientTransaksiPenjualan =retrofit.create(ApiClient_TransaksiPenjualan.class);

        Call<LD_TransaksiPenjualan> transaksiPenjualanModelCall = apiClientTransaksiPenjualan.show();

        transaksiPenjualanModelCall.enqueue(new Callback<LD_TransaksiPenjualan>() {
            @Override
            public void onResponse (Call<LD_TransaksiPenjualan> call, Response<LD_TransaksiPenjualan> response) {
                mListTransaksiPenjualan= response.body().getData();
                Log.i(tampil_transaksi_penjualan.class.getSimpleName(), response.body().toString());
                adapterRiwayatTransaksi = new Adapter_RiwayatTransaksi(mListTransaksiPenjualan,konsumen_tampil_riwayat_transaksi.this);
                recyclerView.setAdapter(adapterRiwayatTransaksi);
                adapterRiwayatTransaksi.notifyDataSetChanged();
                Toast.makeText(konsumen_tampil_riwayat_transaksi.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_TransaksiPenjualan> call, Throwable t) {
                Toast.makeText(konsumen_tampil_riwayat_transaksi.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
