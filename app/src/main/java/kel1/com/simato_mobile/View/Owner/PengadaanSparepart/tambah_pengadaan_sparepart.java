package kel1.com.simato_mobile.View.Owner.PengadaanSparepart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import kel1.com.simato_mobile.ListData.LD_Supplier;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.MotorKonsumen.tambah_data_motor_konsumen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_pengadaan_sparepart extends AppCompatActivity {
    Spinner spinner_supplier, spinner_sparepart,spinner_cabang;
    List<Model_Cabang> spinnerCabangArray = new ArrayList<>();
    List<Model_Supplier> spinnerSupplierArray =  new ArrayList<>();
    List<Model_Sparepart> spinnerSparepartArray = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();
    List<String> spinner_namaSparepart = new ArrayList<>();
    List<String> spinner_namaSupplier = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengadaan_sparepart);
        spinner_supplier = findViewById(R.id.spinner_supplier);
        spinner_sparepart = findViewById(R.id.spinner_sparepart);
        spinner_cabang = findViewById(R.id.spinner_cabang);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
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
        //ngeload nama sparepart dari database kedalam spinner
        ApiClient_Sparepart apiclientSparepart =retrofit.create(ApiClient_Sparepart.class);
        Call<LD_Sparepart> callSparepart = apiclientSparepart.show();
        callSparepart.enqueue(new Callback<LD_Sparepart>() {
            @Override
            public void onResponse(Call<LD_Sparepart> callSparepart, Response<LD_Sparepart> response) {

                spinnerSparepartArray=response.body().getData();
                for(int i=0; i<spinnerSparepartArray.size();i++){
                    spinner_namaSparepart.add(spinnerSparepartArray.get(i).getNama_sparepart());
                }
                ArrayAdapter<String> adapterNamaSparepart = new ArrayAdapter<>(tambah_pengadaan_sparepart.this, R.layout.spinner_sparepart_layout,R.id.txtNamaSparepart, spinner_namaSparepart);
                spinner_sparepart.setAdapter(adapterNamaSparepart);
            }
            @Override
            public void onFailure(Call<LD_Sparepart> call, Throwable t) {
                Toast.makeText(tambah_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //ngeload nama cabang dari database kedalam spinner
        ApiClient_Cabang apiClientCabang =retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callTipeCabang = apiClientCabang.show();
        callTipeCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callTipeCabang, Response<LD_Cabang> response) {

                spinnerCabangArray=response.body().getData();
                for(int i=0; i<spinnerCabangArray.size();i++){
                    spinner_namaCabang.add(spinnerCabangArray.get(i).getNama_cabang());
                }
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(tambah_pengadaan_sparepart.this, R.layout.spinner_cabang_layout,R.id.txtNamaCabang , spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }

            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tambah_pengadaan_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
