package kel1.com.simato_mobile.View.Owner.SparepartCabang;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_Sparepart;
import kel1.com.simato_mobile.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_sparepart_cabang extends AppCompatActivity {

    Spinner spinner_cabang, spinner_sparepart,spinner_letak_penempatan, spinner_ruang_penempatan;
    List<Model_Cabang> spinnerCabangArray =  new ArrayList<>();
    List<Model_Sparepart> spinnerSparepartArray = new ArrayList<>();
    List<String> spinner_namaSparepart = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();
    Integer tempIDCabang, id_sparepartCabang,temp_min, temp_sis;
    String letak_sparepart_fix,tempKodeSparepart, letak, ruang;
    TextInputEditText harga_beli,harga_jual, stok_minimal, stok_sisa;
    Button btnBatal, btnSimpan, btnDelete;
    Double temp_beli, temp_jual;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_sparepart_cabang);

        spinner_cabang = findViewById(R.id.spinner_cabang);
        spinner_sparepart = findViewById(R.id.spinner_sparepart);
        spinner_letak_penempatan = findViewById(R.id.spinner_letakPenempatan);
        spinner_ruang_penempatan  = findViewById(R.id.spinner_ruangPenempatan);

        harga_beli = findViewById(R.id.text_input_hargaBeli);
        harga_jual = findViewById(R.id.text_input_hargaJual);
        stok_minimal = findViewById(R.id.text_input_stokMinimal);
        stok_sisa = findViewById(R.id.text_input_stokSisa);

        btnBatal = (Button)findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit_data_sparepart_cabang.this, tampil_data_sparepart_cabang.class);
                startActivity(i);
            }
        });
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSparepartCabang();
                startIntent();
            }
        });
        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteSparepartCabang();
                startIntent();
            }
        });
        i = getIntent();
        tempIDCabang =i.getIntExtra("id_cabang_fk",-1);
        tempKodeSparepart=i.getStringExtra("kode_sparepart_fk");
        id_sparepartCabang=i.getIntExtra("id_sparepartCabang",-1);
        temp_beli=i.getDoubleExtra("hargaBeli_sparepart",-1);
        harga_beli.setText(String.valueOf(temp_beli));
        temp_jual=i.getDoubleExtra("hargaJual_sparepart",-1);
        harga_jual.setText(String.valueOf(temp_jual));
        letak_sparepart_fix=i.getStringExtra("letak_sparepart");
        temp_min=i.getIntExtra("stokMin_sparepart",-1);
        stok_minimal.setText(String.valueOf(temp_min));
        temp_sis=i.getIntExtra("stokSisa_sparepart",-1);
        stok_sisa.setText(String.valueOf(temp_sis));
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        //ngeload nama cabang dari database kedalam spinner
        ApiClient_Cabang apiclientCabang =retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callCabang = apiclientCabang.show();
        callCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callCabang, Response<LD_Cabang> response) {

                spinnerCabangArray=response.body().getData();
                for(int i=0; i<spinnerCabangArray.size();i++){
                    spinner_namaCabang.add(spinnerCabangArray.get(i).getNama_cabang());
                }
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(edit_data_sparepart_cabang.this, R.layout.spinner_cabang_layout,R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }

            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(edit_data_sparepart_cabang.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<String> adapterNamaSparepart = new ArrayAdapter<>(edit_data_sparepart_cabang.this, R.layout.spinner_sparepart_layout,R.id.txtNamaSparepart, spinner_namaSparepart);
                spinner_sparepart.setAdapter(adapterNamaSparepart);
            }
            @Override
            public void onFailure(Call<LD_Sparepart> call, Throwable t) {
                Toast.makeText(edit_data_sparepart_cabang.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_sparepart_cabang.class);
        startActivity(intent);
    }

    private void UpdateSparepartCabang()
    {
        if(harga_beli.getText().toString().isEmpty() ||
                harga_jual.getText().toString().isEmpty() ||
                stok_minimal.getText().toString().isEmpty() ||
                stok_sisa.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Semua Field harus diisi", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_SparepartCabang.baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            ApiClient_SparepartCabang apiClientSparepartCabang= retrofit.create(ApiClient_SparepartCabang.class);

            String[] selectedNamaCabang=spinner_cabang.getSelectedItem().toString().split("-");
            String[] selectedNamaSparepart=spinner_sparepart.getSelectedItem().toString().split("-");
            String[] selectedLetakPenempatan=spinner_letak_penempatan.getSelectedItem().toString().split("-");
            String[] selectedRuang=spinner_ruang_penempatan.getSelectedItem().toString().split("-");

            for(int i=0; i<spinnerCabangArray.size();i++){

                if(spinnerCabangArray.get(i).getNama_cabang().equals(selectedNamaCabang[0]))
                {
                    tempIDCabang=spinnerCabangArray.get(i).getId_cabang();
                }
            }
            Log.d("Selected  Cabang : ",selectedNamaCabang[0]);
            Log.d("Selected ID Cabang : ",tempIDCabang.toString());

            for(int i=0; i<spinnerSparepartArray.size();i++){

                if(spinnerSparepartArray.get(i).getNama_sparepart().equals(selectedNamaSparepart[0]))
                {
                    tempKodeSparepart=spinnerSparepartArray.get(i).getKode_sparepart();
                }
            }
            Log.d("Selected  Sparepart : ",selectedNamaSparepart[0]);
            Log.d("Kode Spp : ",tempKodeSparepart);
            letak_sparepart_fix = selectedLetakPenempatan[0]+"-"+selectedRuang[0]+"-"+"001";


            Call<ResponseBody> sparepartCabangDAOCall= apiClientSparepartCabang
                    .update(tempIDCabang,tempKodeSparepart,
                            Double.parseDouble(harga_beli.getText().toString()),
                            Double.parseDouble(harga_jual.getText().toString()),
                            letak_sparepart_fix,
                            Integer.parseInt(stok_minimal.getText().toString()),
                            Integer.parseInt(stok_sisa.getText().toString()), id_sparepartCabang);
            sparepartCabangDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        Log.d("Update gagal bcs: ",response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    private void DeleteSparepartCabang() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_SparepartCabang apiClientSparepartCabang =retrofit.create(ApiClient_SparepartCabang.class);

        Call<ResponseBody> sparepartCabangDAOCall = apiClientSparepartCabang.delete(id_sparepartCabang);
        sparepartCabangDAOCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Toast.makeText(getApplicationContext(), "Success Delete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed Delete", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
