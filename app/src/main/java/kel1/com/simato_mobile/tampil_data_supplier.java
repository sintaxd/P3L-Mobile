package kel1.com.simato_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.model.SupplierModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_data_supplier extends AppCompatActivity {

    private static String[] SUGGESTION = new String[]{
            "Apple", "Amazon", "Microsoft", "Alphabet", "Google", "Samsung", "Asus",
            "HP", "Intel", "Qualcom", "AMD", "Oracle", "Facebook", "Spotify"
    };
    private List<SupplierDAO> mListSupplier = new ArrayList<>();
    private RecyclerView recyclerView;
    public SupplierAdapter supplierAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MaterialSearchView materialSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton btn_tambahSupplier;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_supplier);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_supplier);
        supplierAdapter = new SupplierAdapter(this,mListSupplier);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(supplierAdapter);
        setRecycleView();
        btn_tambahSupplier = (FloatingActionButton) findViewById(R.id.btn_tambahDataSupplier);
        btn_tambahSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_data_supplier.this, tambah_data_supplier.class);
                startActivity(i);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        materialSearchView = findViewById(R.id.searchView);
        materialSearchView.setSuggestions(SUGGESTION);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view_supplier);

//        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                final ArrayAdapter arrayAdapter = new ArrayAdapter(tampil_data_supplier.this, android.R.layout.simple_list_item_1, SUGGESTION);
//                recyclerView.setAdapter(arrayAdapter);
//            }
//        });

//        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                final ArrayAdapter arrayAdapter = new ArrayAdapter(tampil_data_supplier.this, android.R.layout.simple_list_item_1, SUGGESTION);
//
//                if(arrayAdapter.getCount() >0)
//                arrayAdapter.clear();
//
//                if (newText != null && !newText.isEmpty()) {
//                    for (String s : SUGGESTION) {
//                        if (s.toLowerCase().contains(newText))
//                            arrayAdapter.add(s);
//                    }
//                } else {
//                    arrayAdapter.addAll(SUGGESTION);
//                }
//
//                listView.setAdapter(arrayAdapter);
//                return false;
//            }
//        });
    }

    public void setRecycleView() {
       // mListSupplier.clear();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl("https://simato.jasonfw.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient apiClient=retrofit.create(ApiClient.class);

        Call<SupplierDAO> supplierDAOCall = apiClient.show();

//        supplierDAOCall.enqueue(new Callback<SupplierModel>() {
//            @Override
//            public void onResponse (Call<SupplierModel> call, Response<SupplierModel> response) {
//                supplierAdapter.notifyDataSetChanged();
//                supplierAdapter = new SupplierAdapter(tampil_data_supplier.this,response.body().getData());
//                recyclerView.setAdapter(supplierAdapter);
//                Toast.makeText(tampil_data_supplier.this,"Welcome", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Call<SupplierModel> call, Throwable t) {
//                Toast.makeText(tampil_data_supplier.this, "Network Connection Error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_supplier, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        materialSearchView.setMenuItem(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

}