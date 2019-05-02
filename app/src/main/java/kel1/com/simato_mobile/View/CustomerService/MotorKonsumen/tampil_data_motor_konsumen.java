package kel1.com.simato_mobile.View.CustomerService.MotorKonsumen;

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

import kel1.com.simato_mobile.API.ApiClient_MotorKonsumen;
import kel1.com.simato_mobile.Adapter.Adapter_MotorKonsumen;
import kel1.com.simato_mobile.ListData.LD_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.cs_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_data_motor_konsumen extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_MotorKonsumen adapterMotorKonsumen;
    private List<Model_MotorKonsumen> mListMotorKonsumen = new ArrayList<>();
    Adapter_MotorKonsumen.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahMotorKonsumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_motor_konsumen);

        recyclerView = findViewById(R.id.recycler_view_motor_konsumen);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterMotorKonsumen);

        listener = new Adapter_MotorKonsumen.RecyclerViewClickListener(){
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_data_motor_konsumen.class);
                intent.putExtra("id_motor", mListMotorKonsumen.get(position).getId_motor_fk());
                intent.putExtra("id_motorkonsumen", mListMotorKonsumen.get(position).getId_motorKonsumen());
                intent.putExtra("id_konsumen", mListMotorKonsumen.get(position).getId_konsumen_fk());
                intent.putExtra("plat_motor", mListMotorKonsumen.get(position).getPlat_motorKonsumen());
                startActivity(intent);
            }
        };
        btn_tambahMotorKonsumen = findViewById(R.id.btn_tambahDataMotorKonsumen);
        btn_tambahMotorKonsumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_motor_konsumen.this, tambah_data_motor_konsumen.class);
                startActivity(i);
            }
        });
    }@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_motor_konsumen, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Motor Konsumen");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterMotorKonsumen.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterMotorKonsumen.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(tampil_data_motor_konsumen.this, cs_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setRecycleViewMotorKonsumen() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_MotorKonsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_MotorKonsumen apiClientMotorKonsumen =retrofit.create(ApiClient_MotorKonsumen.class);

        Call<LD_MotorKonsumen> motorKonsumenModelCall = apiClientMotorKonsumen.show();

        motorKonsumenModelCall.enqueue(new Callback<LD_MotorKonsumen>() {
            @Override
            public void onResponse (Call<LD_MotorKonsumen> call, Response<LD_MotorKonsumen> response) {
                mListMotorKonsumen= response.body().getData();
                Log.i(tampil_data_konsumen.class.getSimpleName(), response.body().toString());
                adapterMotorKonsumen = new Adapter_MotorKonsumen(mListMotorKonsumen,tampil_data_motor_konsumen.this,listener);
                recyclerView.setAdapter(adapterMotorKonsumen);
                adapterMotorKonsumen.notifyDataSetChanged();
                Toast.makeText(tampil_data_motor_konsumen.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_MotorKonsumen> call, Throwable t) {
                Toast.makeText(tampil_data_motor_konsumen.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewMotorKonsumen();
    }
}
