package kel1.com.simato_mobile.View.Owner.Supplier;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class tampil_data_supplier extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_Supplier adapterSupplier;
    private List<Model_Supplier> mListSupplier = new ArrayList<>();
    Adapter_Supplier.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_supplier);

        recyclerView = findViewById(R.id.recycler_view_supplier);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSupplier);

        listener = new Adapter_Supplier.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_data_supplier.class);
                intent.putExtra("id_supplier", mListSupplier.get(position).getId_supplier());
                intent.putExtra("nama_supplier", mListSupplier.get(position).getNama_supplier());
                intent.putExtra("alamat_supplier", mListSupplier.get(position).getAlamat_supplier());
                intent.putExtra("noTelp_supplier", mListSupplier.get(position).getNoTelp_supplier());
                intent.putExtra("nama_sales", mListSupplier.get(position).getNama_sales());
                intent.putExtra("noTelp_sales", mListSupplier.get(position).getNoTelp_sales());
                startActivity(intent);
            }
        };
        btn_tambahSupplier = findViewById(R.id.btn_tambahDataSupplier);
        btn_tambahSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_supplier.this, tambah_data_supplier.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_supplier, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Supplier");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterSupplier.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterSupplier.getFilter().filter(newText);
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
            Intent i = new Intent(tampil_data_supplier.this, owner_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setRecycleViewSupplier() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Supplier.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Supplier apiClientSupplier =retrofit.create(ApiClient_Supplier.class);

        Call<LD_Supplier> supplierModelCall = apiClientSupplier.show();

        supplierModelCall.enqueue(new Callback<LD_Supplier>() {
            @Override
            public void onResponse (Call<LD_Supplier> call, Response<LD_Supplier> response) {
                mListSupplier = response.body().getData();
                Log.i(tampil_data_supplier.class.getSimpleName(), response.body().toString());
                adapterSupplier = new Adapter_Supplier(mListSupplier,tampil_data_supplier.this,listener);
                recyclerView.setAdapter(adapterSupplier);
                adapterSupplier.notifyDataSetChanged();
                Toast.makeText(tampil_data_supplier.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_Supplier> call, Throwable t) {
                Toast.makeText(tampil_data_supplier.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewSupplier();
    }
}
