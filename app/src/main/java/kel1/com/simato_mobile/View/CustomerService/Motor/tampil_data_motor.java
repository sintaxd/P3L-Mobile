package kel1.com.simato_mobile.View.CustomerService.Motor;

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
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.Adapter.Adapter_Konsumen;
import kel1.com.simato_mobile.Adapter.Adapter_Motor;
import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.edit_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.cs_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_data_motor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_Motor adapterMotor;
    private List<Model_Motor> mListMotor = new ArrayList<>();
    Adapter_Motor.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahMotor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_motor);

        recyclerView = findViewById(R.id.recycler_view_motor);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterMotor);

        listener = new Adapter_Motor.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
                Intent intent = new Intent(getApplicationContext(), edit_data_motor.class);
                intent.putExtra("id_motor", mListMotor.get(position).getId_motor());
                intent.putExtra("merk_motor", mListMotor.get(position).getMerk_motor());
                intent.putExtra("tipe_motor", mListMotor.get(position).getTipe_motor());
                startActivity(intent);
            }
        };
        btn_tambahMotor = (FloatingActionButton) findViewById(R.id.btn_tambahDataMotor);
        btn_tambahMotor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_motor.this, tambah_data_motor.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_motor, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.searchMenu);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Motor");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapterMotor.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapterMotor.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent i = new Intent(tampil_data_motor.this, cs_main_menu.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setRecycleViewMotor() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Motor.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Motor apiclientMotor =retrofit.create(ApiClient_Motor.class);

        Call<LD_Motor> motorModelCall = apiclientMotor.show();

        motorModelCall.enqueue(new Callback<LD_Motor>() {
            @Override
            public void onResponse (Call<LD_Motor> call, Response<LD_Motor> response) {
                mListMotor= response.body().getData();
                Log.i(tampil_data_konsumen.class.getSimpleName(), response.body().toString());
                adapterMotor = new Adapter_Motor(mListMotor,tampil_data_motor.this,listener);
                recyclerView.setAdapter(adapterMotor);
                adapterMotor.notifyDataSetChanged();
                Toast.makeText(tampil_data_motor.this,"Welcome", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_Motor> call, Throwable t) {
                Toast.makeText(tampil_data_motor.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        setRecycleViewMotor();
    }
}
