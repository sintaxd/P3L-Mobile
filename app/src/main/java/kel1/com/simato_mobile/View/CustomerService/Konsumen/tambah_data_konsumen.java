package kel1.com.simato_mobile.View.CustomerService.Konsumen;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Supplier.tambah_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_data_konsumen extends AppCompatActivity {

    private Button btnBatal, btnSimpan;
    private TextInputEditText nama_konsumen,noTelp_konsumen, alamat_konsumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_konsumen);

        nama_konsumen = findViewById(R.id.text_input_namaKonsumen);
        noTelp_konsumen = findViewById(R.id.text_input_noTelpKonsumen);
        alamat_konsumen = findViewById(R.id.text_input_alamatKonsumen);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_konsumen.getText().clear();
                noTelp_konsumen.getText().clear();
                alamat_konsumen.getText().clear();
            }
        });
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });

    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_konsumen.class);
        startActivity(intent);
    }
    private void onClickRegister() {
        if (nama_konsumen.getText().toString().isEmpty() ||
                noTelp_konsumen.getText().toString().isEmpty() ||
                alamat_konsumen.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Semua Field harus diisi!", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl("http://simato.jasonfw.com/")  //http://10.53.0.204:8000/
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            ApiClient_Konsumen apiClientKonsumen = retrofit.create(ApiClient_Konsumen.class);
            Call<Model_Konsumen> konsumenDAOCall = apiClientKonsumen.create(nama_konsumen.getText().toString(),
                    noTelp_konsumen.getText().toString(),
                    alamat_konsumen.getText().toString());
            konsumenDAOCall.enqueue(new Callback<Model_Konsumen>() {
                @Override
                public void onResponse(Call<Model_Konsumen> call, Response<Model_Konsumen> response) {
                    Toast.makeText(tambah_data_konsumen.this, "Tambah Konsumen Sukses!", Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                @Override
                public void onFailure(Call<Model_Konsumen> call, Throwable t) {
                    Toast.makeText(tambah_data_konsumen.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
