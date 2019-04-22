package kel1.com.simato_mobile.View.Owner.Sparepart;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_data_sparepart extends AppCompatActivity {

    private Button btnBatal,btnSimpan;
    private TextInputEditText nama_sparepart, merk_sparepart, kode_sparepart, tipe_sparepart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_sparepart);
        nama_sparepart = findViewById(R.id.text_input_namaSparepart);
        merk_sparepart = findViewById(R.id.text_input_merkSparepart);
        tipe_sparepart = findViewById(R.id.text_input_tipeSparepart);
        kode_sparepart = findViewById(R.id.text_input_kode_sparepart);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnBatal = (Button)findViewById(R.id.button_Batal);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_sparepart.getText().clear();
                merk_sparepart.getText().clear();
                tipe_sparepart.getText().clear();
                kode_sparepart.getText().clear();
            }
        });


    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_sparepart.class);
        startActivity(intent);
    }private void onClickRegister() {
        if (nama_sparepart.getText().toString().isEmpty() ||
            merk_sparepart.getText().toString().isEmpty() ||
            tipe_sparepart.getText().toString().isEmpty() ||
            kode_sparepart.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl("http://simato.jasonfw.com/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            ApiClient_Sparepart apiClient = retrofit.create(ApiClient_Sparepart.class);
            Call<LD_Sparepart> sparepartDAOCall = apiClient.create(nama_sparepart.getText().toString(),
                    merk_sparepart.getText().toString(),
                    tipe_sparepart.getText().toString(),
                    kode_sparepart.getText().toString());
            sparepartDAOCall.enqueue(new Callback<LD_Sparepart>() {
                @Override
                public void onResponse(Call<LD_Sparepart> call, Response<LD_Sparepart> response) {
                    Toast.makeText(tambah_data_sparepart.this, "Success", Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                @Override
                public void onFailure(Call<LD_Sparepart> call, Throwable t) {
                    Toast.makeText(tambah_data_sparepart.this, "Network connection failed ", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
