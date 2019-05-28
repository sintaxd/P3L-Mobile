package kel1.com.simato_mobile.View.Owner.HistorySparepart;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_PengadaanSparepart;
import kel1.com.simato_mobile.Adapter.Adapter_PengadaanSparepart;
import kel1.com.simato_mobile.ListData.LD_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.edit_pengadaan_sparepart;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tampil_pengadaan_sparepart;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_history_sparepart_masuk extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_PengadaanSparepart adapterHistoriSparepartMasuk;
    private List<Model_PengadaanSparepart> mListPengadaanSparepart = new ArrayList<>();
    private List<Model_PengadaanSparepart> mListPengadaanSparepartSelesai = new ArrayList<>();
    Adapter_PengadaanSparepart.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_history_sparepart_masuk);

        recyclerView = findViewById(R.id.recycler_view_history_sparepart_masuk);
        recyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterHistoriSparepartMasuk);
        listener = new Adapter_PengadaanSparepart.RecyclerViewClickListener(){
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_pengadaan_sparepart.class);
                intent.putExtra("id_pengadaan", mListPengadaanSparepartSelesai.get(position).getId_pengadaan());
                intent.putExtra("id_supplier", mListPengadaanSparepartSelesai.get(position).getId_supplier_fk());
                intent.putExtra("id_cabang", mListPengadaanSparepartSelesai.get(position).getId_cabang_fk());
                intent.putExtra("id_sparepartCabang_fk", mListPengadaanSparepartSelesai.get(position).getId_sparepartCabang_fk());
                intent.putExtra("tgl_pengadaan", mListPengadaanSparepartSelesai.get(position).getTgl_pengadaan());
                intent.putExtra("total_harga", mListPengadaanSparepartSelesai.get(position).getTotalHarga_pengadaan());
                intent.putExtra("status_cetak", mListPengadaanSparepartSelesai.get(position).getStatusCetak_pengadaan());
                intent.putExtra("status_pengadaan", mListPengadaanSparepartSelesai.get(position).getStatus_pengadaan());
                intent.putExtra("editable","no");

                Log.d( "Parsed ID Pengadaan : ",mListPengadaanSparepartSelesai.get(position).getId_pengadaan().toString());

                startActivity(intent);
            }
        };
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
        searchView.setQueryHint("Search History Sparepart Masuk");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterHistoriSparepartMasuk.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterHistoriSparepartMasuk.getFilter().filter(newText);
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
            Intent i = new Intent(tampil_history_sparepart_masuk.this, owner_main_menu.class);
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


        Call<LD_PengadaanSparepart> pengadaansparepartModelCall = apiClientPengadaanSparepart.show();

        pengadaansparepartModelCall.enqueue(new Callback<LD_PengadaanSparepart>() {
            @Override
            public void onResponse (Call<LD_PengadaanSparepart> call, Response<LD_PengadaanSparepart> response) {
                mListPengadaanSparepart = response.body().getData();
                mListPengadaanSparepartSelesai.clear();
                for(int i = 0 ; i < mListPengadaanSparepart.size() ; i++)
                {
                    if(mListPengadaanSparepart.get(i).getStatus_pengadaan().equalsIgnoreCase("Sudah Selesai"))
                    {
                        mListPengadaanSparepartSelesai.add(new Model_PengadaanSparepart(mListPengadaanSparepart.get(i).getId_pengadaan(),
                                mListPengadaanSparepart.get(i).getId_supplier_fk(),
                                mListPengadaanSparepart.get(i).getId_cabang_fk(),
                                mListPengadaanSparepart.get(i).getId_sparepartCabang_fk(),
                                mListPengadaanSparepart.get(i).getSatuan_pengadaan(),
                                mListPengadaanSparepart.get(i).getTotalBarang_datang(),
                                mListPengadaanSparepart.get(i).getTgl_pengadaan(),
                                mListPengadaanSparepart.get(i).getTgl_barangDatang(),
                                mListPengadaanSparepart.get(i).getStatus_pengadaan(),
                                mListPengadaanSparepart.get(i).getStatusCetak_pengadaan(),
                                mListPengadaanSparepart.get(i).getNama_supplier(),
                                mListPengadaanSparepart.get(i).getTotalHarga_pengadaan()));
                    }
                }
                Log.i(tampil_pengadaan_sparepart.class.getSimpleName(), response.body().toString());
                adapterHistoriSparepartMasuk = new Adapter_PengadaanSparepart(mListPengadaanSparepartSelesai,tampil_history_sparepart_masuk.this,listener);
                recyclerView.setAdapter(adapterHistoriSparepartMasuk);
                adapterHistoriSparepartMasuk.notifyDataSetChanged();
                Toast.makeText(tampil_history_sparepart_masuk.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_PengadaanSparepart> call, Throwable t) {
                Toast.makeText(tampil_history_sparepart_masuk.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("kegagalan: ",t.getMessage());
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewPengadaanSparepart();
    }
}
