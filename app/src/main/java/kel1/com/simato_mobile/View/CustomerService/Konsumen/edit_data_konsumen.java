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
import kel1.com.simato_mobile.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_konsumen extends AppCompatActivity {

    private Button btnBatal, btnSimpan, btnDelete;
    private TextInputEditText nama_konsumen, noTelp_konsumen, alamat_konsumen;
    private Integer id_konsumen;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_konsumen);

        nama_konsumen = findViewById(R.id.text_input_namaKonsumen);
        noTelp_konsumen = findViewById(R.id.text_input_noTelpKonsumen);
        alamat_konsumen = findViewById(R.id.text_input_alamatKonsumen);

        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateKonsumen();
                startIntent();
            }
        });
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit_data_konsumen.this, tampil_data_konsumen.class);
                startActivity(i);
            }
        });
        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteKonsumen();
                startIntent();
            }
        });
        i = getIntent();
        nama_konsumen.setText(i.getStringExtra("nama_konsumen"));
        noTelp_konsumen.setText(i.getStringExtra("noTelp_konsumen"));
        alamat_konsumen.setText(i.getStringExtra("alamat_konsumen"));
        id_konsumen=i.getIntExtra("id_konsumen",-1);
    }
    public void startIntent()
    {
        Intent intent= new Intent(getApplicationContext(), tampil_data_konsumen.class);
        startActivity(intent);
    }
    private void UpdateKonsumen()
    {
        if(nama_konsumen.getText().toString().isEmpty() || noTelp_konsumen.getText().toString().isEmpty() ||
                alamat_konsumen.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_Konsumen.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();

            ApiClient_Konsumen apiClientKonsumen =retrofit.create(ApiClient_Konsumen.class);
            Call<ResponseBody> konsumenDAOCall= apiClientKonsumen.update(nama_konsumen.getText().toString(),
                    noTelp_konsumen.getText().toString(),
                    alamat_konsumen.getText().toString(),
                    id_konsumen);
            konsumenDAOCall.enqueue(new Callback<ResponseBody>() {
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
    private void DeleteKonsumen() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Konsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Konsumen apiClientKonsumen =retrofit.create(ApiClient_Konsumen.class);

        Call<ResponseBody> konsumenDAOCall = apiClientKonsumen.delete(id_konsumen);
        konsumenDAOCall.enqueue(new Callback<ResponseBody>() {
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

