package kel1.com.simato_mobile.View.CustomerService.Konsumen;

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

import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Adapter.Adapter_Konsumen;
import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.cs_main_menu;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_data_konsumen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_Konsumen adapterKonsumen;
    private List<Model_Konsumen> mListKonsumen = new ArrayList<>();
    Adapter_Konsumen.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahKonsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_konsumen);

        recyclerView = findViewById(R.id.recycler_view_konsumen);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterKonsumen);

        listener = new Adapter_Konsumen.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_data_konsumen.class);
                intent.putExtra("id_konsumen", mListKonsumen.get(position).getId_konsumen());
                intent.putExtra("nama_konsumen", mListKonsumen.get(position).getNama_konsumen());
                intent.putExtra("alamat_konsumen", mListKonsumen.get(position).getAlamat_konsumen());
                intent.putExtra("noTelp_konsumen", mListKonsumen.get(position).getNoTelp_konsumen());
                startActivity(intent);
            }
        };
        btn_tambahKonsumen = (FloatingActionButton) findViewById(R.id.btn_tambahDataKonsumen);
        btn_tambahKonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_konsumen.this, tambah_data_konsumen.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_konsumen, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Konsumen");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterKonsumen.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterKonsumen.getFilter().filter(newText);
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
            Intent i = new Intent(tampil_data_konsumen.this, cs_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setRecycleViewKonsumen() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Konsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Konsumen apiclientKonsumen =retrofit.create(ApiClient_Konsumen.class);

        Call<LD_Konsumen> konsumenModelCall = apiclientKonsumen.show();

        konsumenModelCall.enqueue(new Callback<LD_Konsumen>() {
            @Override
            public void onResponse (Call<LD_Konsumen> call, Response<LD_Konsumen> response) {
                mListKonsumen= response.body().getData();
                Log.i(tampil_data_konsumen.class.getSimpleName(), response.body().toString());
                adapterKonsumen = new Adapter_Konsumen(mListKonsumen,tampil_data_konsumen.this,listener);
                recyclerView.setAdapter(adapterKonsumen);
                adapterKonsumen.notifyDataSetChanged();
                Toast.makeText(tampil_data_konsumen.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_Konsumen> call, Throwable t) {
                Toast.makeText(tampil_data_konsumen.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewKonsumen();
    }
}
