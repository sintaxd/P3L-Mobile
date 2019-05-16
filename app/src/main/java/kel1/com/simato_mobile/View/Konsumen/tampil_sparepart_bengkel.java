package kel1.com.simato_mobile.View.Konsumen;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_SparepartBengkel;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartBengkel;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_SparepartBengkel;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_SparepartBengkel;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tampil_sparepart_stok_kurang;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tampil_data_sparepart_cabang;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_sparepart_bengkel extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_SparepartBengkel adapterSparepartBengkel;
    private List<Model_SparepartBengkel> mListSparepartBengkel = new ArrayList<>();
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    private List<Model_SparepartCabang> mListSparepartCabang = new ArrayList<>();

    List<String> spinner_IDCabang = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();

    Spinner spinner_cabang, spinner_sortBy;
    Integer selectedIDCabang, selectedSort;

    Adapter_SparepartBengkel.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_sparepart_bengkel);

        recyclerView = findViewById(R.id.recycler_view_tampil_sparepart_bengkel);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSparepartBengkel);
        spinner_cabang = findViewById(R.id.spinner_cabang);
        spinner_sortBy = findViewById(R.id.spinner_sortBy);

        loadSpinnerNamaCabang();
        spinner_cabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama cabang saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDCabang = Integer.parseInt(spinner_IDCabang.get(position)); //Mendapatkan id dari dropdown yang dipilih

                setRecycleViewSparepart();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedIDCabang=1;
            }
        });
        spinner_sortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama cabang saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                Retrofit.Builder builder = new Retrofit
                        .Builder()
                        .baseUrl(ApiClient_SparepartBengkel.baseURL)
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                if(position==0)
                {
                   //stok asc
                   Log.d( "onItemSelected: ", String.valueOf(position));
                }
                else if(position==1)
                {
                    //stok desc
                }
                else if(position==2)
                {
                    //hargaJual asc
                }
                else if(position==3)
                {
                    //hargaJual desc
                }
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
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(tampil_sparepart_bengkel.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tampil_sparepart_bengkel.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_sparepart_bengkel, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Sparepart Bengkel");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterSparepartBengkel.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterSparepartBengkel.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(tampil_sparepart_bengkel.this,konsumen_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setRecycleViewSparepart() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartBengkel.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_SparepartBengkel apiClientSparepartBengkel =retrofit.create(ApiClient_SparepartBengkel.class);

        Call<LD_SparepartBengkel> sparepartBengkelModelCall = apiClientSparepartBengkel.showByCabang(selectedIDCabang);

        sparepartBengkelModelCall.enqueue(new Callback<LD_SparepartBengkel>() {
            @Override
            public void onResponse (Call<LD_SparepartBengkel> call, Response<LD_SparepartBengkel> response) {
                mListSparepartBengkel = response.body().getData();
                Log.i(tampil_data_sparepart_cabang.class.getSimpleName(), response.body().toString());
                adapterSparepartBengkel = new Adapter_SparepartBengkel(mListSparepartBengkel,tampil_sparepart_bengkel.this,listener);
                recyclerView.setAdapter(adapterSparepartBengkel);
                adapterSparepartBengkel.notifyDataSetChanged();
//                Collections.sort(mListSparepartBengkel, new Comparator<Model_SparepartBengkel>() {
//                    @Override
//                    public int compare(Model_SparepartBengkel Sp1, Model_SparepartBengkel Sp2) {
//                        return Sp1.getHargaJual_sparepart().compareTo(Sp2.getHargaJual_sparepart());
//                    }
//                });
                Log.d("on respon : ",String.valueOf(response.code()));
                Log.d("on respon msg : ",String.valueOf(response.message()));
                Toast.makeText(tampil_sparepart_bengkel.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartBengkel> call, Throwable t) {
                Toast.makeText(tampil_sparepart_bengkel.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
