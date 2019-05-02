package kel1.com.simato_mobile.View.CustomerService.MotorKonsumen;

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

import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.API.ApiClient_MotorKonsumen;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.Model.Model_Konsumen;
import kel1.com.simato_mobile.Model.Model_Motor;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.Motor.edit_data_motor;
import kel1.com.simato_mobile.View.CustomerService.Motor.tampil_data_motor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_motor_konsumen extends AppCompatActivity {


    List<Model_Motor> spinnerMotorArray = new ArrayList<>();
    List<Model_Konsumen> spinnerKonsumenArray = new ArrayList<>();
    List<String> spinner_namaMotor = new ArrayList<>();
    List<String> spinner_namaKonsumen = new ArrayList<>();
    Spinner spinner_motor,spinner_konsumen;
    private Button btnBatal, btnSimpan, btnDelete;
    private TextInputEditText nomor_plat;
    private Integer id_motorKonsumen, tempIDKonsumen, tempIDMotor,id_motor_fk,id_konsumen_fk;
    private Intent i;
    String sNoPlat,tipe,nama,plat_nomor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_motor_konsumen);
        nomor_plat = findViewById(R.id.text_input_platMotorKonsumen);
        spinner_motor=findViewById(R.id.spinner_motor);
        spinner_konsumen=findViewById(R.id.spinner_konsumen);
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMotorKonsumen();
                startIntent();
            }
        });
        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit_data_motor_konsumen.this, tampil_data_motor_konsumen.class);
                startActivity(i);
            }
        });
        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMotorKonsumen();
                startIntent();
            }
        });
        i = getIntent();
        nomor_plat.setText(i.getStringExtra("plat_motor"));
        id_motorKonsumen=i.getIntExtra("id_motorkonsumen",-1);
        tempIDKonsumen=i.getIntExtra("id_konsumen",-1);
        tempIDMotor=i.getIntExtra("id_motor",-1);
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
                    spinner_namaMotor.add(spinnerMotorArray.get(i).getTipe_motor());
                }
                ArrayAdapter<String> adapterTipeMotor = new ArrayAdapter<>(edit_data_motor_konsumen.this, R.layout.spinner_motor_layout,R.id.txtTipeMotor, spinner_namaMotor);

                for(int i=0; i<spinnerMotorArray.size();i++){
                    if(spinnerMotorArray.get(i).getId_motor().equals(tempIDMotor))
                    {
                        tipe=spinnerMotorArray.get(i).getTipe_motor();
                        Log.d("Selected  Motor : ",tipe);
                        spinner_motor.setSelected(!(tipe.isEmpty()));
                    }
                }
                spinner_motor.setAdapter(adapterTipeMotor);
            }

            @Override
            public void onFailure(Call<LD_Motor> call, Throwable t) {
                Toast.makeText(edit_data_motor_konsumen.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<String> adapterNamaKonsumen = new ArrayAdapter<>(edit_data_motor_konsumen.this, R.layout.spinner_konsumen_layout,R.id.txtNamaKonsumen, spinner_namaKonsumen);
                for(int i=0; i<spinnerKonsumenArray.size();i++){
                    if(spinnerKonsumenArray.get(i).getId_konsumen().equals(tempIDMotor))
                    {
                        nama=spinnerKonsumenArray.get(i).getNama_konsumen();
                        Log.d("Selected  Motor : ",nama);
                    }
                }
                spinner_konsumen.setAdapter(adapterNamaKonsumen);
            }

            @Override
            public void onFailure(Call<LD_Konsumen> call, Throwable t) {
                Toast.makeText(edit_data_motor_konsumen.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void startIntent()
    {
        Intent intent= new Intent(getApplicationContext(), tampil_data_motor_konsumen.class);
        startActivity(intent);
    }
    private void UpdateMotorKonsumen()
    {
        if(nomor_plat.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_Supplier.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();
            ApiClient_MotorKonsumen apiClientMotorKonsumen= retrofit.create(ApiClient_MotorKonsumen.class);

            String[] selectedTipeMotor=spinner_motor.getSelectedItem().toString().split("-");
            String[] selectedNamaKonsumen=spinner_konsumen.getSelectedItem().toString().split("-");
            for(int i=0; i<spinnerMotorArray.size();i++){

                if(spinnerMotorArray.get(i).getTipe_motor().equals(selectedTipeMotor[0]))
                {
                    id_motor_fk=spinnerMotorArray.get(i).getId_motor();
                }
            }
            Log.d("Selected  Motor : ",selectedTipeMotor[0]);
            Log.d("Selected ID Motor : ",id_motor_fk.toString());
            for(int i=0; i<spinnerKonsumenArray.size();i++){

                if(spinnerKonsumenArray.get(i).getNama_konsumen().equals(selectedNamaKonsumen[0]))
                {
                    id_konsumen_fk=spinnerKonsumenArray.get(i).getId_konsumen();
                }
            }
            Log.d("Selected  Konsumen : ",selectedNamaKonsumen[0]);
            Log.d("Selected ID Konsumen : ",id_konsumen_fk.toString());
            plat_nomor = nomor_plat.getText().toString();

            Call<ResponseBody> motorkonsumenDAOCall= apiClientMotorKonsumen.update(id_motor_fk,id_konsumen_fk,plat_nomor,id_motorKonsumen);
            motorkonsumenDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        Log.d("on respon : ",String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }
    }
    private void DeleteMotorKonsumen() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_MotorKonsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_MotorKonsumen apiClientMotorKonsumen =retrofit.create(ApiClient_MotorKonsumen.class);

        Call<ResponseBody> motorKonsumenDAOCall = apiClientMotorKonsumen.delete(id_motorKonsumen);
        motorKonsumenDAOCall.enqueue(new Callback<ResponseBody>() {
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