package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.MotorKonsumen.tambah_data_motor_konsumen;
import kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan.tambah_transaksi_penjualan_sparepart;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_pengadaan_sparepart extends AppCompatActivity {
    Spinner spinner_supplier, spinner_sparepartcabang,spinner_cabang;
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    List<Model_Supplier> spinnerSupplierArray =  new ArrayList<>();
    List<Model_SparepartCabang> spinnerNamaSparepartCabangArray = new ArrayList<>();
    List<Model_SparepartCabang> spinnerIDSparepartCabangArray = new ArrayList<>();
    List<String> spinner_namaSparepartCabang = new ArrayList<>();
    List<String> spinner_IDCabang = new ArrayList<>();
    List<String> spinner_IDSparepartCabang = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();
    List<String> spinner_namaSupplier = new ArrayList<>();
    Integer selectedIDCabang;
    String selectedIDSparepartCabang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengadaan_sparepart);
        spinner_supplier = findViewById(R.id.spinner_supplier);
        spinner_sparepartcabang = findViewById(R.id.spinner_sparepartcabang);
        spinner_cabang = findViewById(R.id.spinner_cabang);
        loadSpinnerNamaCabang();
        spinner_cabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama cabang saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDCabang = Integer.parseInt(spinner_IDCabang.get(position)); //Mendapatkan id dari dropdown yang dipilih
                loadSpinnerNamaSparepartCabang();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                selectedIDCabang=1;
                loadSpinnerNamaSparepartCabang();
            }
        });
        loadSpinnerNamaSupplier();
    }
    void loadSpinnerNamaSupplier(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Supplier.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        //ngeload nama supplier dari database kedalam spinner
        ApiClient_Supplier apiclientSupplier =retrofit.create(ApiClient_Supplier.class);
        Call<LD_Supplier> callSupplier = apiclientSupplier.show();
        callSupplier.enqueue(new Callback<LD_Supplier>() {
            @Override
            public void onResponse(Call<LD_Supplier> callSparepart, Response<LD_Supplier> response) {

                spinnerSupplierArray=response.body().getData();
                for(int i=0; i<spinnerSupplierArray.size();i++){
                    spinner_namaSupplier.add(spinnerSupplierArray.get(i).getNama_supplier());
                }
                ArrayAdapter<String> adapterNamaSupplier = new ArrayAdapter<>(tambah_pengadaan_sparepart.this, R.layout.spinner_supplier_layout,R.id.txtNamaSupplier, spinner_namaSupplier);
                spinner_supplier.setAdapter(adapterNamaSupplier);
            }
            @Override
            public void onFailure(Call<LD_Supplier> call, Throwable t) {
                Toast.makeText(tambah_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void loadSpinnerNamaCabang(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Cabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload nama cabang dari database kedalam spinner
        ApiClient_Cabang apiclientCabang = retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callCabang = apiclientCabang.show();
        callCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callCabang, Response<LD_Cabang> response) {

                spinnerNamaCabangArray = response.body().getData();
                //  spinnerIDCabangArray = response.body().getData();
                for (int i = 0; i < spinnerNamaCabangArray.size(); i++) {
                    spinner_namaCabang.add(spinnerNamaCabangArray.get(i).getNama_cabang());
                    spinner_IDCabang.add(spinnerNamaCabangArray.get(i).getId_cabang().toString());
                }
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(tambah_pengadaan_sparepart.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tambah_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }

    void loadSpinnerNamaSparepartCabang()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //ngeload nama sparepart cabang dari database kedalam spinner
        ApiClient_SparepartCabang apiClientSparepartCabang = retrofit.create(ApiClient_SparepartCabang.class);
        Call<LD_SparepartCabang> callSparepartCabang = apiClientSparepartCabang.showByCabang(selectedIDCabang);

        callSparepartCabang.enqueue(new Callback<LD_SparepartCabang>() {
            @Override
            public void onResponse(Call<LD_SparepartCabang> callSparepartCabang, Response<LD_SparepartCabang> response) {
                spinner_namaSparepartCabang.clear();
                spinnerNamaSparepartCabangArray =response.body().getData();
                for (int i = 0; i < spinnerNamaSparepartCabangArray.size(); i++) {
                    spinner_namaSparepartCabang.add(spinnerNamaSparepartCabangArray.get(i).getNama_sparepart());
                    spinner_IDSparepartCabang.add(spinnerNamaSparepartCabangArray.get(i).getId_sparepartCabang().toString());
                }
                ArrayAdapter<String> adapterNamaSparepartCabang = new ArrayAdapter<>(tambah_pengadaan_sparepart.this, R.layout.spinner_sparepart_layout, R.id.txtNamaSparepart, spinner_namaSparepartCabang);
                spinner_sparepartcabang.setAdapter(adapterNamaSparepartCabang);
                Toast.makeText(tambah_pengadaan_sparepart.this, response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartCabang> call, Throwable t) {
                Toast.makeText(tambah_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        spinner_sparepartcabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown tipe sparepart saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDSparepartCabang = spinner_IDSparepartCabang.get(position); //Mendapatkan id dari dropdown yang dipilih
                Log.d("ID SparepartCabang : ", selectedIDSparepartCabang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

}
