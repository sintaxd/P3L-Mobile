package kel1.com.simato_mobile.View.Owner.Supplier;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_supplier extends AppCompatActivity {

    private Button btnBatal, btnSimpan, btnDelete;
    private TextInputEditText nama_supp, notelp_supp, alamat_supp, nama_sales, notelp_sales;
    private Integer id_supplier;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_supplier);

        btnBatal = (Button)findViewById(R.id.button_Batal);

        nama_supp = findViewById(R.id.text_input_namaSupplier);
        notelp_supp = findViewById(R.id.text_input_noTelpSupplier);
        alamat_supp = findViewById(R.id.text_input_alamatSupplier);
        nama_sales = findViewById(R.id.text_input_namaSales);
        notelp_sales = findViewById(R.id.text_input_noTelpSales);
        btnSimpan = findViewById(R.id.button_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSupplier();
                startIntent();
            }
        });
        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteSupplier();
                startIntent();
            }
        });
        i = getIntent();
        nama_supp.setText(i.getStringExtra("nama_supplier"));
        notelp_supp.setText(i.getStringExtra("noTelp_supplier"));
        alamat_supp.setText(i.getStringExtra("alamat_supplier"));
        nama_sales.setText(i.getStringExtra("nama_sales"));
        notelp_sales.setText(i.getStringExtra("noTelp_sales"));
        id_supplier=i.getIntExtra("id_supplier",-1);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit_data_supplier.this, tampil_data_supplier.class);
                startActivity(i);
            }
        });
    }

    public void startIntent()
    {
        Intent intent= new Intent(getApplicationContext(), tampil_data_supplier.class);
        startActivity(intent);
    }
    private void UpdateSupplier()
    {
        if(nama_supp.getText().toString().isEmpty() || alamat_supp.getText().toString().isEmpty() ||
                notelp_supp.getText().toString().isEmpty()|| nama_sales.getText().toString().isEmpty() ||
                notelp_sales.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl("http://simato.jasonfw.com/") //http://10.53.0.204:8000/ local
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();

            ApiClient_Supplier apiClientSupplier =retrofit.create(ApiClient_Supplier.class);
            Call<ResponseBody> supplierDAOCall= apiClientSupplier.update(nama_supp.getText().toString(),
                                                                            notelp_supp.getText().toString(),
                                                                            alamat_supp.getText().toString(),
                                                                            nama_sales.getText().toString(),
                                                                            notelp_sales.getText().toString(),
                                                                            id_supplier);
            supplierDAOCall.enqueue(new Callback<ResponseBody>() {
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
    private void DeleteSupplier() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl("http://simato.jasonfw.com/")  //http://10.53.0.204:8000/
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Supplier apiClientSupplier =retrofit.create(ApiClient_Supplier.class);

        Call<ResponseBody> supplierDAOCall = apiClientSupplier.delete(id_supplier);
        supplierDAOCall.enqueue(new Callback<ResponseBody>() {
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
