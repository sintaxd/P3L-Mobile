package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_PengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Adapter.Adapter_PengadaanSparepart;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.ListData.LD_PengadaanSparepart;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Sparepart.tampil_data_sparepart;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tambah_data_sparepart_cabang;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tampil_data_sparepart_cabang;
import kel1.com.simato_mobile.View.Owner.Supplier.edit_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tambah_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_pengadaan_sparepart extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_PengadaanSparepart adapterPengadaanSparepart;
    private List<Model_PengadaanSparepart> mListPengadaanSparepart = new ArrayList<>();
    Adapter_PengadaanSparepart.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahPengadaanSparepart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pengadaan_sparepart);

        recyclerView = findViewById(R.id.recycler_view_pengadaan_sparepart);
        recyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterPengadaanSparepart);

        btn_tambahPengadaanSparepart = (FloatingActionButton) findViewById(R.id.btn_tambahPengadaanSparepart);
        btn_tambahPengadaanSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_pengadaan_sparepart.this, tambah_pengadaan_sparepart.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_sparepart, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Pengadaan Sprepart");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterPengadaanSparepart.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

               adapterPengadaanSparepart.getFilter().filter(newText);
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
            Intent i = new Intent(tampil_pengadaan_sparepart.this, owner_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRecycleViewPengadaanSparepart() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_PengadaanSparepart.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_PengadaanSparepart apiClientPengadaanSparepart =retrofit.create(ApiClient_PengadaanSparepart.class);

        //sampe sini

        Call<LD_PengadaanSparepart> pengadaansparepartModelCall = apiClientPengadaanSparepart.show();

        pengadaansparepartModelCall.enqueue(new Callback<LD_PengadaanSparepart>() {
            @Override
            public void onResponse (Call<LD_PengadaanSparepart> call, Response<LD_PengadaanSparepart> response) {
                mListPengadaanSparepart = response.body().getData();
                Log.i(tampil_pengadaan_sparepart.class.getSimpleName(), response.body().toString());
                adapterPengadaanSparepart = new Adapter_PengadaanSparepart(mListPengadaanSparepart,tampil_pengadaan_sparepart.this,listener);
                recyclerView.setAdapter(adapterPengadaanSparepart);
                adapterPengadaanSparepart.notifyDataSetChanged();
                Toast.makeText(tampil_pengadaan_sparepart.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_PengadaanSparepart> call, Throwable t) {
                Toast.makeText(tampil_pengadaan_sparepart.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewPengadaanSparepart();
    }
}
