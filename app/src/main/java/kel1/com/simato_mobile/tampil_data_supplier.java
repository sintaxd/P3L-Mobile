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
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class tampil_data_supplier extends AppCompatActivity {

    private MaterialSearchView materialSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FloatingActionButton btn_tambahSupplier;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_data_supplier);


        btn_tambahSupplier=(FloatingActionButton) findViewById(R.id.btn_tambahDataSupplier);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_supplier, menu);
            MenuItem menuItem = menu.findItem(R.id.searchMenu);
            materialSearchView.setMenuItem(menuItem);
        return super.onCreateOptionsMenu(menu);
    }
}
