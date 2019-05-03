package kel1.com.simato_mobile.View.Owner.Sparepart;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Adapter.Adapter_Sparepart;
import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Supplier.edit_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_data_sparepart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_Sparepart adapterSparepart;
    private List<Model_Sparepart> mListSparepart = new ArrayList<>();
    Adapter_Sparepart.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahSparepart;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_sparepart);

        recyclerView = findViewById(R.id.recycler_view_sparepart);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSparepart);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listener = new Adapter_Sparepart.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_data_sparepart.class);
                intent.putExtra("nama_sparepart", mListSparepart.get(position).getNama_sparepart());
                intent.putExtra("kode_sparepart", mListSparepart.get(position).getKode_sparepart());
                intent.putExtra("merk_sparepart", mListSparepart.get(position).getMerk_sparepart());
                intent.putExtra("tipe_sparepart", mListSparepart.get(position).getTipe_sparepart());
                intent.putExtra("gambar_sparepart", mListSparepart.get(position).getGambar_sparepart());
                startActivity(intent);
            }
        };
        btn_tambahSparepart=(FloatingActionButton) findViewById(R.id.btn_tambahDataSparepart);
        btn_tambahSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_sparepart.this, tambah_data_sparepart.class);
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
        searchView.setQueryHint("Search Sparepart");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterSparepart.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterSparepart.getFilter().filter(newText);
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
            Intent i = new Intent(tampil_data_sparepart.this, owner_main_menu.class);
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
                .baseUrl(ApiClient_Sparepart.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Sparepart apiClientSparepart =retrofit.create(ApiClient_Sparepart.class);

        Call<LD_Sparepart> sparepartModelCall = apiClientSparepart.show();

        sparepartModelCall.enqueue(new Callback<LD_Sparepart>() {
            @Override
            public void onResponse (Call<LD_Sparepart> call, Response<LD_Sparepart> response) {
                mListSparepart = response.body().getData();
                Log.i(tampil_data_supplier.class.getSimpleName(), response.body().toString());
                adapterSparepart = new Adapter_Sparepart(mListSparepart,tampil_data_sparepart.this,listener);
                recyclerView.setAdapter(adapterSparepart);
                adapterSparepart.notifyDataSetChanged();
                Toast.makeText(tampil_data_sparepart.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_Sparepart> call, Throwable t) {
                Toast.makeText(tampil_data_sparepart.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewSparepart();
    }
}
