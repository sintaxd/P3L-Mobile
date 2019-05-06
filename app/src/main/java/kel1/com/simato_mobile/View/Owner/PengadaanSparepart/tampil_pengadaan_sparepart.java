package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Adapter.Adapter_PengadaanSparepart;
import kel1.com.simato_mobile.Adapter.Adapter_SparepartCabang;
import kel1.com.simato_mobile.Adapter.Adapter_Supplier;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_PengadaanSparepart;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tambah_data_sparepart_cabang;
import kel1.com.simato_mobile.View.Owner.SparepartCabang.tampil_data_sparepart_cabang;
import kel1.com.simato_mobile.View.Owner.Supplier.edit_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tambah_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tampil_pengadaan_sparepart extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public Adapter_PengadaanSparepart adapterPengadaanSparepart;
    private List<Model_PengadaanSparepart> mListPengadaanSparepart = new ArrayList<>();
    //Adapter_PengadaanSparepart.RecyclerViewClickListener listener;
    FloatingActionButton btn_tambahPengadaanSparepart;
    public Adapter_SparepartCabang adapterSparepartCabang;
    private List<Model_SparepartCabang> mListSparepartCabang = new ArrayList<>();
    Adapter_SparepartCabang.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pengadaan_sparepart);

        recyclerView = findViewById(R.id.recycler_view_pengadaan_sparepart);
        recyclerView.setLayoutManager(layoutManager);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterSparepartCabang);

        btn_tambahPengadaanSparepart = (FloatingActionButton) findViewById(R.id.btn_tambahPengadaanSparepart);
        btn_tambahPengadaanSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tampil_pengadaan_sparepart.this, tambah_pengadaan_sparepart.class);
                startActivity(i);
            }
        });
    }
}
