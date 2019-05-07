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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_SparepartBengkel;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartBengkel;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_SparepartBengkel;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_SparepartBengkel;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;
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

        Call<LD_SparepartBengkel> sparepartBengkelModelCall = apiClientSparepartBengkel.show();

        sparepartBengkelModelCall.enqueue(new Callback<LD_SparepartBengkel>() {
            @Override
            public void onResponse (Call<LD_SparepartBengkel> call, Response<LD_SparepartBengkel> response) {
                mListSparepartBengkel = response.body().getData();
                Log.i(tampil_data_sparepart_cabang.class.getSimpleName(), response.body().toString());
                adapterSparepartBengkel = new Adapter_SparepartBengkel(mListSparepartBengkel,tampil_sparepart_bengkel.this,listener);
                recyclerView.setAdapter(adapterSparepartBengkel);
                adapterSparepartBengkel.notifyDataSetChanged();
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
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewSparepart();
    }
}
