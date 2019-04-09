package kel1.com.simato_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class tampil_data_supplier extends AppCompatActivity {

    private static String[] SUGGESTION = new String[]{
            "Apple", "Amazon", "Microsoft", "Alphabet", "Google", "Samsung", "Asus",
            "HP", "Intel", "Qualcom", "AMD", "Oracle", "Facebook", "Spotify"
    };

    private MaterialSearchView materialSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton btn_tambahSupplier;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_supplier);

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

        final ListView listView = findViewById(R.id.listView);

        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                final ArrayAdapter arrayAdapter = new ArrayAdapter(tampil_data_supplier.this, android.R.layout.simple_list_item_1, SUGGESTION);
                listView.setAdapter(arrayAdapter);
            }
        });

        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayAdapter arrayAdapter = new ArrayAdapter(tampil_data_supplier.this, android.R.layout.simple_list_item_1, SUGGESTION);

                if(arrayAdapter.getCount() >0)
                arrayAdapter.clear();

                if (newText != null && !newText.isEmpty()) {
                    for (String s : SUGGESTION) {
                        if (s.toLowerCase().contains(newText))
                            arrayAdapter.add(s);
                    }
                } else {
                    arrayAdapter.addAll(SUGGESTION);
                }

                listView.setAdapter(arrayAdapter);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_supplier, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        materialSearchView.setMenuItem(menuItem);
        return super.onCreateOptionsMenu(menu);
    }

}