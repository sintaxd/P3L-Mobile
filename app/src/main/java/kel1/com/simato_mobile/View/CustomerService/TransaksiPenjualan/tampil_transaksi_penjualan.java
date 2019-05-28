package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.Adapter.Adapter_TransaksiPenjualan;
import kel1.com.simato_mobile.ListData.LD_TransaksiPenjualan;
import kel1.com.simato_mobile.Model.Model_TransaksiPenjualan;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.cs_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_transaksi_penjualan extends AppCompatActivity {
    Spinner spinner_status_transaksi;
    Integer status;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_TransaksiPenjualan adapterTransaksiPenjualan;
    private List<Model_TransaksiPenjualan> mListTransaksiPenjualan = new ArrayList<>();
    Adapter_TransaksiPenjualan.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_transaksi_penjualan);

        recyclerView = findViewById(R.id.recycler_view_transaksi_penjualan);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterTransaksiPenjualan);

        spinner_status_transaksi = findViewById(R.id.spinner_status_transaksi);
        spinner_status_transaksi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama sort By saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                if(position==0)
                {
                    setRecycleViewTransaksiPenjualan();
                }
                else if(position==1)
                {
                    //status transaksi belum lunas
                    status=0;
                    setRecycleViewTransaksiPenjualan_ByStatusTransaksi();
                }
                else if(position==2)
                {
                    //status transaksi sudah lunas
                    status=1;
                    setRecycleViewTransaksiPenjualan_ByStatusTransaksi();
                }
                Log.d( "Status :", String.valueOf(status));
                Log.d( "Position spinner sort :", String.valueOf(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView){

            }
        });

        listener = new Adapter_TransaksiPenjualan.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), edit_transaksi_penjualan_sparepart.class);
                intent.putExtra("id_transaksi", mListTransaksiPenjualan.get(position).getId_transaksi());
                intent.putExtra("nama_cabang", mListTransaksiPenjualan.get(position).getNama_cabang());
                intent.putExtra("kode_transaksi", mListTransaksiPenjualan.get(position).getKode_transaksi());
                intent.putExtra("diskon", mListTransaksiPenjualan.get(position).getDiskon());
                intent.putExtra("tanggal_transaksi", mListTransaksiPenjualan.get(position).getTgl_transaksi());
                intent.putExtra("total_transaksi", mListTransaksiPenjualan.get(position).getTotal_transaksi());
                intent.putExtra("status_transaksi", mListTransaksiPenjualan.get(position).getStatus_transaksi());

                startActivity(intent);
            }
        };
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_transaksi_penjualan, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Transaksi Penjualan");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                adapterTransaksiPenjualan.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapterTransaksiPenjualan.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(tampil_transaksi_penjualan.this, cs_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                mListTransaksiPenjualan.clear();
                mListTransaksiPenjualan= response.body().getData();
                Log.i(tampil_transaksi_penjualan.class.getSimpleName(), response.body().toString());
                adapterTransaksiPenjualan = new Adapter_TransaksiPenjualan(mListTransaksiPenjualan,tampil_transaksi_penjualan.this,listener);
                recyclerView.setAdapter(adapterTransaksiPenjualan);
                adapterTransaksiPenjualan.notifyDataSetChanged();
                Toast.makeText(tampil_transaksi_penjualan.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_TransaksiPenjualan> call, Throwable t) {
                Toast.makeText(tampil_transaksi_penjualan.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setRecycleViewTransaksiPenjualan_ByStatusTransaksi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_TransaksiPenjualan.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_TransaksiPenjualan apiClientTransaksiPenjualan =retrofit.create(ApiClient_TransaksiPenjualan.class);

        Call<LD_TransaksiPenjualan> transaksiPenjualanModelCall = apiClientTransaksiPenjualan.showByStatusTransaksi(status);

        transaksiPenjualanModelCall.enqueue(new Callback<LD_TransaksiPenjualan>() {
            @Override
            public void onResponse (Call<LD_TransaksiPenjualan> call, Response<LD_TransaksiPenjualan> response) {
                mListTransaksiPenjualan.clear();
                mListTransaksiPenjualan= response.body().getData();
                Log.i(tampil_transaksi_penjualan.class.getSimpleName(), response.body().toString());
                adapterTransaksiPenjualan = new Adapter_TransaksiPenjualan(mListTransaksiPenjualan,tampil_transaksi_penjualan.this,listener);
                recyclerView.setAdapter(adapterTransaksiPenjualan);
                adapterTransaksiPenjualan.notifyDataSetChanged();
                Toast.makeText(tampil_transaksi_penjualan.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_TransaksiPenjualan> call, Throwable t) {
                Toast.makeText(tampil_transaksi_penjualan.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewTransaksiPenjualan();
    }
}