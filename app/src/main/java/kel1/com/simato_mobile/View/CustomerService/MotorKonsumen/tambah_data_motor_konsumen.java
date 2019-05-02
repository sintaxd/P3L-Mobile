package kel1.com.simato_mobile.View.CustomerService.MotorKonsumen;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.API.ApiClient_MotorKonsumen;
import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_data_motor_konsumen extends AppCompatActivity {

    Spinner spinner_motor,spinner_konsumen;
    List<Model_Motor> spinnerMotorArray = new ArrayList<>();
    List<Model_Konsumen> spinnerKonsumenArray = new ArrayList<>();
    List<String> spinner_namaMotor = new ArrayList<>();
    List<String> spinner_namaKonsumen = new ArrayList<>();
    Integer tempIDKonsumen, tempIDMotor;
    TextInputEditText noPlat;
    String sNoPlat;
    Button btnBatal, btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_motor_konsumen);
        spinner_motor=findViewById(R.id.spinner_motor);
        spinner_konsumen=findViewById(R.id.spinner_konsumen);
        noPlat=findViewById(R.id.text_input_platMotorKonsumen);
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noPlat.getText().clear();
            }
        });
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRegister();
            }
        });
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Motor.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        //ngeload tipe motor dari database kedalam spinner
        ApiClient_Motor apiclientMotor =retrofit.create(ApiClient_Motor.class);
        Call<LD_Motor> callTipeMotor = apiclientMotor.show();
        callTipeMotor.enqueue(new Callback<LD_Motor>() {
            @Override
            public void onResponse(Call<LD_Motor> callTipeMotor, Response<LD_Motor> response) {

                spinnerMotorArray=response.body().getData();
                for(int i=0; i<spinnerMotorArray.size();i++){
                    //spinner_namaMotor.add(String.format("%03d",spinnerMotorArray.get(i).getId_motor())+"-"+spinnerMotorArray.get(i).getTipe_motor());
                    spinner_namaMotor.add(spinnerMotorArray.get(i).getTipe_motor());
                }
                ArrayAdapter<String> adapterTipeMotor = new ArrayAdapter<>(tambah_data_motor_konsumen.this, R.layout.spinner_motor_layout,R.id.txtTipeMotor, spinner_namaMotor);
                spinner_motor.setAdapter(adapterTipeMotor);
            }

            @Override
            public void onFailure(Call<LD_Motor> call, Throwable t) {
                Toast.makeText(tambah_data_motor_konsumen.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        ApiClient_Konsumen apiclientKonsumen =retrofit.create(ApiClient_Konsumen.class);
        Call<LD_Konsumen> callNamaKonsumen = apiclientKonsumen.show();
        callNamaKonsumen.enqueue(new Callback<LD_Konsumen>() {
            @Override
            public void onResponse(Call<LD_Konsumen> callNamaKonsumen, Response<LD_Konsumen> response) {

                spinnerKonsumenArray=response.body().getData();
                for(int i=0; i<spinnerKonsumenArray.size();i++){
                    spinner_namaKonsumen.add(spinnerKonsumenArray.get(i).getNama_konsumen());
                }
                ArrayAdapter<String> adapterNamaKonsumen = new ArrayAdapter<>(tambah_data_motor_konsumen.this, R.layout.spinner_konsumen_layout,R.id.txtNamaKonsumen, spinner_namaKonsumen);
                spinner_konsumen.setAdapter(adapterNamaKonsumen);
            }

            @Override
            public void onFailure(Call<LD_Konsumen> call, Throwable t) {
                Toast.makeText(tambah_data_motor_konsumen.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_motor_konsumen.class);
        startActivity(intent);
    }
    private void onClickRegister() {
        if (noPlat.getText().toString().isEmpty())
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
            ApiClient_MotorKonsumen apiClientMotorKonsumen= retrofit.create(ApiClient_MotorKonsumen.class);

            String[] selectedTipeMotor=spinner_motor.getSelectedItem().toString().split("-");
            String[] selectedNamaKonsumen=spinner_konsumen.getSelectedItem().toString().split("-");

            for(int i=0; i<spinnerMotorArray.size();i++){

                if(spinnerMotorArray.get(i).getTipe_motor().equals(selectedTipeMotor[0]))
                {
                    tempIDMotor=spinnerMotorArray.get(i).getId_motor();
                }
            }
            Log.d("Selected  Motor : ",selectedTipeMotor[0]);
            Log.d("Selected ID Motor : ",tempIDMotor.toString());
            for(int i=0; i<spinnerKonsumenArray.size();i++){

                if(spinnerKonsumenArray.get(i).getNama_konsumen().equals(selectedNamaKonsumen[0]))
                {
                    tempIDKonsumen=spinnerKonsumenArray.get(i).getId_konsumen();
                }
            }
            Log.d("Selected  Konsumen : ",selectedNamaKonsumen[0]);
            Log.d("Selected ID Konsumen : ",tempIDKonsumen.toString());
            sNoPlat = noPlat.getText().toString();

            Call<Model_MotorKonsumen> motorKonsumenDAOCall = apiClientMotorKonsumen.create(tempIDMotor,tempIDKonsumen,sNoPlat);
            motorKonsumenDAOCall.enqueue(new Callback<Model_MotorKonsumen>() {
                @Override
                public void onResponse(Call<Model_MotorKonsumen> call, Response<Model_MotorKonsumen> response) {
                    if (response.code() == 201) {
                        Toast.makeText(tambah_data_motor_konsumen.this, "Tambah Motor Konsumen Sukses!", Toast.LENGTH_SHORT).show();
                        startIntent();
                    } else {
                        Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("on respon : ",String.valueOf(response.code()));

                }
                @Override
                public void onFailure(Call<Model_MotorKonsumen> call, Throwable t) {
                    Toast.makeText(tambah_data_motor_konsumen.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
