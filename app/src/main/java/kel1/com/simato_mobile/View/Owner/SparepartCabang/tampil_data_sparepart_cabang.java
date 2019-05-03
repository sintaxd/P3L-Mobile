package kel1.com.simato_mobile.View.Owner.SparepartCabang;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.Adapter.Adapter_Sparepart;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Sparepart.tampil_data_sparepart;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_data_sparepart_cabang extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_SparepartCabang adapterSparepartCabang;
    private List<Model_SparepartCabang> mListSparepartCabang = new ArrayList<>();
    Adapter_SparepartCabang.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahSparepartCabang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_sparepart_cabang);

        recyclerView = findViewById(R.id.recycler_view_sparepart_cabang);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSparepartCabang);

        listener = new Adapter_SparepartCabang.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_data_sparepart_cabang.class);
                intent.putExtra("id_cabang_fk", mListSparepartCabang.get(position).getId_cabang_fk());
                intent.putExtra("kode_sparepart_fk", mListSparepartCabang.get(position).getKode_sparepart_fk());
                intent.putExtra("id_sparepartCabang", mListSparepartCabang.get(position).getId_sparepartCabang());
                intent.putExtra("hargaBeli_sparepart", mListSparepartCabang.get(position).getHargaBeli_sparepart());
                intent.putExtra("hargaJual_sparepart", mListSparepartCabang.get(position).getHargaJual_sparepart());
                intent.putExtra("letak_sparepart", mListSparepartCabang.get(position).getLetak_sparepart());
                intent.putExtra("stokMin_sparepart", mListSparepartCabang.get(position).getStokMin_sparepart());
                intent.putExtra("stokSisa_sparepart", mListSparepartCabang.get(position).getStokSisa_sparepart());
                startActivity(intent);
            }
        };
        btn_tambahSparepartCabang = (FloatingActionButton) findViewById(R.id.btn_tambahDataSparepartCabang);
        btn_tambahSparepartCabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_sparepart_cabang.this, tambah_data_sparepart_cabang.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_sparepart_cabang, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Sparepart Cabang");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterSparepartCabang.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterSparepartCabang.getFilter().filter(newText);
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
            Intent i = new Intent(tampil_data_sparepart_cabang.this, owner_main_menu.class);
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
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_SparepartCabang apiClientSparepartCabang =retrofit.create(ApiClient_SparepartCabang.class);

        Call<LD_SparepartCabang> sparepartCabangModelCall = apiClientSparepartCabang.show();

        sparepartCabangModelCall.enqueue(new Callback<LD_SparepartCabang>() {
            @Override
            public void onResponse (Call<LD_SparepartCabang> call, Response<LD_SparepartCabang> response) {
                mListSparepartCabang = response.body().getData();
                Log.i(tampil_data_sparepart_cabang.class.getSimpleName(), response.body().toString());
                adapterSparepartCabang = new Adapter_SparepartCabang(mListSparepartCabang,tampil_data_sparepart_cabang.this,listener);
                recyclerView.setAdapter(adapterSparepartCabang);
                adapterSparepartCabang.notifyDataSetChanged();
                Log.d("on respon : ",String.valueOf(response.code()));
                Log.d("on respon msg : ",String.valueOf(response.message()));
                Toast.makeText(tampil_data_sparepart_cabang.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartCabang> call, Throwable t) {
                Toast.makeText(tampil_data_sparepart_cabang.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewSparepart();
    }
}

