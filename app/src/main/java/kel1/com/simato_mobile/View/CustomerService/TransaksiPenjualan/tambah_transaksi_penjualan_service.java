package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kel1.com.simato_mobile.API.ApiClient_JasaService;
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.ListData.LD_JasaService;
import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.Model.Model_JasaService;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.MotorKonsumen.tambah_data_motor_konsumen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_transaksi_penjualan_service extends AppCompatActivity {

    TextView setTanggal;
    Spinner spinner_nama_jasa_service;
    List<Model_JasaService> spinnerJasaServiceArray = new ArrayList<>();
    List<String> spinner_namaJasaService = new ArrayList<>();
    Integer tempIDJasaService;
    Button btnBatal, btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi_penjualan_service);

        setTanggal = findViewById(R.id.textView_tanggalFix);
        //create a date string.
        String date_now = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(new Date());
        //set it as current date.
        setTanggal.setText(date_now);

        spinner_nama_jasa_service = findViewById(R.id.spinner_nama_jasa_service);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Motor.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        //ngeload tipe motor dari database kedalam spinner
        ApiClient_JasaService apiclientJasaService =retrofit.create(ApiClient_JasaService.class);
        Call<LD_JasaService> callNamaJasaService = apiclientJasaService.show();
        callNamaJasaService.enqueue(new Callback<LD_JasaService>() {
            @Override
            public void onResponse(Call<LD_JasaService> callNamaJasaService, Response<LD_JasaService> response) {

                spinnerJasaServiceArray=response.body().getData();
                for(int i=0; i<spinnerJasaServiceArray.size();i++){
                    spinner_namaJasaService.add(spinnerJasaServiceArray.get(i).getNama_jasaService());
                }
                ArrayAdapter<String> adapterTipeMotor = new ArrayAdapter<>(tambah_transaksi_penjualan_service.this, R.layout.spinner_jasa_service_layout,R.id.txtNamaJasaService, spinner_namaJasaService);
                spinner_nama_jasa_service.setAdapter(adapterTipeMotor);
            }

            @Override
            public void onFailure(Call<LD_JasaService> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
