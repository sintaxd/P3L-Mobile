package kel1.com.simato_mobile.View.CustomerService.Motor;

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
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tambah_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_data_motor extends AppCompatActivity {
    private Button btnBatal, btnSimpan;
    private TextInputEditText merk_motor,tipe_motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_motor);

        merk_motor = findViewById(R.id.text_input_merkMotor);
        tipe_motor = findViewById(R.id.text_input_tipeMotor);
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                merk_motor.getText().clear();
                tipe_motor.getText().clear();
            }
        });

        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
    }private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_motor.class);
        startActivity(intent);
    }
    private void onClickRegister() {
        if (merk_motor.getText().toString().isEmpty() ||
                tipe_motor.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Semua Field harus diisi!", Toast.LENGTH_SHORT).show();
        } else {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_Motor.baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            ApiClient_Motor apiClientMotor= retrofit.create(ApiClient_Motor.class);
            Call<Model_Motor> motorDAOCall = apiClientMotor.create(merk_motor.getText().toString(),
                    tipe_motor.getText().toString());
            motorDAOCall.enqueue(new Callback<Model_Motor>() {
                @Override
                public void onResponse(Call<Model_Motor> call, Response<Model_Motor> response) {
                    Toast.makeText(tambah_data_motor.this, "Tambah Motor Sukses!", Toast.LENGTH_SHORT).show();
                    startIntent();
                }
                @Override
                public void onFailure(Call<Model_Motor> call, Throwable t) {
                    Toast.makeText(tambah_data_motor.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
