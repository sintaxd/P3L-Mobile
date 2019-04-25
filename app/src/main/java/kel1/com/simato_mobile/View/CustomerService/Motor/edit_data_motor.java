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
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.edit_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_motor extends AppCompatActivity {
    private Button btnBatal, btnSimpan, btnDelete;
    private TextInputEditText merk_motor,tipe_motor;
    private Integer id_motor;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_motor);

        merk_motor = findViewById(R.id.text_input_merkMotor);
        tipe_motor = findViewById(R.id.text_input_tipeMotor);

        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMotor();
                startIntent();
            }
        });
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit_data_motor.this, tampil_data_motor.class);
                startActivity(i);
            }
        });
        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMotor();
                startIntent();
            }
        });
        i = getIntent();
        merk_motor.setText(i.getStringExtra("merk_motor"));
        tipe_motor.setText(i.getStringExtra("tipe_motor"));
        id_motor=i.getIntExtra("id_motor",-1);
    }
    public void startIntent()
    {
        Intent intent= new Intent(getApplicationContext(), tampil_data_motor.class);
        startActivity(intent);
    }
    private void UpdateMotor()
    {
        if(merk_motor.getText().toString().isEmpty() || tipe_motor.getText().toString().isEmpty() )
        {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_Motor.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();

            ApiClient_Motor apiClientMotor =retrofit.create(ApiClient_Motor.class);
            Call<ResponseBody> motorDAOCall= apiClientMotor.update(merk_motor.getText().toString(),
                    tipe_motor.getText().toString(),
                    id_motor);
            motorDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed Update", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    private void DeleteMotor() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Motor.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Motor apiClientMotor =retrofit.create(ApiClient_Motor.class);

        Call<ResponseBody> motorDAOCall = apiClientMotor.delete(id_motor);
        motorDAOCall.enqueue(new Callback<ResponseBody>() {
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

