package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tampil_data_sparepart_cabang;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_sparepart_stok_kurang extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_SparepartCabang adapterSparepartCabang;
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    private List<Model_SparepartCabang> mListSparepartCabang = new ArrayList<>();

    List<String> spinner_IDCabang = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();

    Adapter_SparepartCabang.RecyclerViewClickListener listener;
    Spinner spinner_cabang;
    Integer selectedIDCabang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_sparepart_stok_kurang);

        recyclerView = findViewById(R.id.recycler_view_tampil_sparepart_stok_kurang);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSparepartCabang);

        spinner_cabang = findViewById(R.id.spinner_cabang);

        loadSpinnerNamaCabang();
        spinner_cabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama cabang saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDCabang = Integer.parseInt(spinner_IDCabang.get(position)); //Mendapatkan id dari dropdown yang dipilih
                setRecycleViewSparepartStokKurang();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedIDCabang=1;
            }
        });

    }
    void loadSpinnerNamaCabang(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Cabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload nama cabang dari database kedalam spinner
        ApiClient_Cabang apiclientCabang = retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callCabang = apiclientCabang.show();
        callCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callCabang, Response<LD_Cabang> response) {

                spinnerNamaCabangArray = response.body().getData();
                //  spinnerIDCabangArray = response.body().getData();
                for (int i = 0; i < spinnerNamaCabangArray.size(); i++) {
                    spinner_namaCabang.add(spinnerNamaCabangArray.get(i).getNama_cabang());
                    spinner_IDCabang.add(spinnerNamaCabangArray.get(i).getId_cabang().toString());
                }
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(tampil_sparepart_stok_kurang.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tampil_sparepart_stok_kurang.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }
    public void kelola_pengadaan_sparepart(View view) {
        Intent i= new Intent(tampil_sparepart_stok_kurang.this, tampil_pengadaan_sparepart.class);
        startActivity(i);
    }
    public void setRecycleViewSparepartStokKurang() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_SparepartCabang apiClientSparepartCabang =retrofit.create(ApiClient_SparepartCabang.class);
        Log.d("ID Cabang : ", selectedIDCabang.toString());
        Call<LD_SparepartCabang> sparepartCabangModelCall = apiClientSparepartCabang.showStokKurangByCabang(selectedIDCabang);

        sparepartCabangModelCall.enqueue(new Callback<LD_SparepartCabang>() {
            @Override
            public void onResponse (Call<LD_SparepartCabang> call, Response<LD_SparepartCabang> response) {
                mListSparepartCabang = response.body().getData();
                Log.i(tampil_data_sparepart_cabang.class.getSimpleName(), response.body().toString());
                adapterSparepartCabang = new Adapter_SparepartCabang(mListSparepartCabang,tampil_sparepart_stok_kurang.this);
                recyclerView.setAdapter(adapterSparepartCabang);
                adapterSparepartCabang.notifyDataSetChanged();
                Log.d("on respon : ",String.valueOf(response.code()));
                Log.d("on respon msg : ",String.valueOf(response.message()));
                Toast.makeText(tampil_sparepart_stok_kurang.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartCabang> call, Throwable t) {
                Toast.makeText(tampil_sparepart_stok_kurang.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setRecycleViewSparepartStokKurang();
//    }
}
