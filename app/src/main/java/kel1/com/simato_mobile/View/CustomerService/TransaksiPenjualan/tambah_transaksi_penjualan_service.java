package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.DeflaterOutputStream;

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_DetilPengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_DetilTransaksi;
import kel1.com.simato_mobile.API.ApiClient_JasaService;
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.API.ApiClient_MotorKonsumen;
import kel1.com.simato_mobile.API.ApiClient_Pegawai;
import kel1.com.simato_mobile.API.ApiClient_PengadaanSparepart;
import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.Adapter.Adapter_DetilTransaksiService;
import kel1.com.simato_mobile.Adapter.Adapter_DetilTransaksiSparepart;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_JasaService;
import kel1.com.simato_mobile.ListData.LD_Konsumen;
import kel1.com.simato_mobile.ListData.LD_Motor;
import kel1.com.simato_mobile.ListData.LD_MotorKonsumen;
import kel1.com.simato_mobile.ListData.LD_Pegawai;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_DetilTransaksiService;
import kel1.com.simato_mobile.Model.Model_JasaService;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_Pegawai;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.CustomerService.MotorKonsumen.tambah_data_motor_konsumen;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tambah_pengadaan_sparepart;
import kel1.com.simato_mobile.View.Owner.PengadaanSparepart.tampil_pengadaan_sparepart;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_transaksi_penjualan_service extends AppCompatActivity {

    TextView setTanggal, totalHarga_fix;
    ImageView addDetilTransaksiService;
    Spinner spinner_nama_jasa_service, spinner_cabang, spinner_montir, spinner_plat_konsumen;

    List<Model_JasaService> spinnerJasaServiceArray = new ArrayList<>();
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    List<Model_Pegawai> spinnerNamaPegawaiArray = new ArrayList<>();
    List<Model_MotorKonsumen> spinnerNamaMotorKonsumenArray = new ArrayList<>();

    List<String> spinner_IDJasaService = new ArrayList<>();
    List<String> spinner_namaJasaService = new ArrayList<>();
    List<String> spinner_hargaJasaService = new ArrayList<>();

    List<String> spinner_IDCabang = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();

    List<String> spinner_IDPegawai = new ArrayList<>();
    List<String> spinner_namaPegawai = new ArrayList<>();

    List<String> spinner_IDMotorKonsumen = new ArrayList<>();
    List<String> spinner_platMotorKonsumen = new ArrayList<>();
    private List<Model_DetilTransaksiService> detilTransaksiServicetList = new ArrayList<Model_DetilTransaksiService>();

    Integer tempIDJasaService, selectedIDCabang, selectedIDMontir, selectedIDJasaService, selectedIDMotorKonsumen;
    String selectedNamaJasaService;
    Button btnBatal, btnSimpan;
    Double GrandTotal=0.0, selectedHargaJasaService;
    RecyclerView rview;
    private Adapter_DetilTransaksiService adapter;
    private RecyclerView.LayoutManager layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi_penjualan_service);

        setTanggal = findViewById(R.id.textView_tanggalFix);
        //create a date string.
        String date_now = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault()).format(new Date());
        //set it as current date.
        setTanggal.setText(date_now);
        totalHarga_fix = findViewById(R.id.textView_totalHargaFix);
        spinner_cabang = findViewById(R.id.spinner_cabang);
        spinner_nama_jasa_service = findViewById(R.id.spinner_nama_jasa_service);
        spinner_montir = findViewById(R.id.spinner_montir);
        spinner_plat_konsumen = findViewById(R.id.spinner_plat_konsumen);

        rview = findViewById(R.id.recycler_view_detil_transaksi_service);
        layout = new LinearLayoutManager(getApplicationContext());
        rview.setLayoutManager(layout);

        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddTransaksi();
            }
        });

        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent();
            }
        });

        addDetilTransaksiService = findViewById(R.id.addDetilTransaksiService);
        addDetilTransaksiService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetilTransaksiServiceFunction();
                Toast.makeText(tambah_transaksi_penjualan_service.this, "miaaw", Toast.LENGTH_SHORT).show();
            }
        });
        loadSpinnerNamaCabang();
        spinner_cabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama cabang saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDCabang = Integer.parseInt(spinner_IDCabang.get(position)); //Mendapatkan id dari dropdown yang dipilih
                Log.d("ID Cabang: ",selectedIDCabang.toString());
                loadSpinnerNamaMontir();
                spinner_montir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama montir saat dipilih
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        selectedIDMontir = Integer.parseInt(spinner_IDPegawai.get(position)); //Mendapatkan id dari dropdown yang dipilih
                        Log.d("ID Cabang: ",selectedIDCabang.toString());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        loadSpinnerPlatMotor();
        spinner_plat_konsumen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown plat konsumen saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDMotorKonsumen = Integer.parseInt(spinner_IDMotorKonsumen.get(position)); //Mendapatkan id dari dropdown yang dipilih
                Log.d("ID Motor Konsumen: ",selectedIDMotorKonsumen.toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        loadSpinnerNamaJasaService();
        spinner_nama_jasa_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown nama jasa service saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDJasaService = Integer.parseInt(spinner_IDJasaService.get(position)); //Mendapatkan id dari dropdown yang dipilih
                selectedHargaJasaService = Double.parseDouble(spinner_hargaJasaService.get(position));
                selectedNamaJasaService=spinner_namaJasaService.get(position);
                Log.d("ID Jasa Service: ",selectedIDJasaService.toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("updateTotal"));
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String subtotal = intent.getStringExtra("sub_total");
            Double subTotalFix = Double.parseDouble(subtotal);
            GrandTotal=GrandTotal-subTotalFix;
            totalHarga_fix.setText(GrandTotal.toString());
        }
    };
    private void addDetilTransaksiServiceFunction(){
        Double sub_total_service;
        sub_total_service=selectedHargaJasaService;
        detilTransaksiServicetList.add(new Model_DetilTransaksiService(sub_total_service, selectedIDMotorKonsumen,selectedIDJasaService, selectedNamaJasaService,selectedHargaJasaService));
        adapter = new Adapter_DetilTransaksiService(detilTransaksiServicetList);
        rview.setAdapter(adapter);
        GrandTotal=GrandTotal+sub_total_service;
        totalHarga_fix.setText(GrandTotal.toString());
    }
    void loadSpinnerNamaJasaService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Motor.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        //ngeload jasa service dari database kedalam spinner
        ApiClient_JasaService apiclientJasaService =retrofit.create(ApiClient_JasaService.class);
        Call<LD_JasaService> callNamaJasaService = apiclientJasaService.show();
        callNamaJasaService.enqueue(new Callback<LD_JasaService>() {
            @Override
            public void onResponse(Call<LD_JasaService> callNamaJasaService, Response<LD_JasaService> response) {

                spinnerJasaServiceArray=response.body().getData();
                for(int i=0; i<spinnerJasaServiceArray.size();i++){
                    spinner_namaJasaService.add(spinnerJasaServiceArray.get(i).getNama_jasaService());
                    spinner_IDJasaService.add(spinnerJasaServiceArray.get(i).getId_jasaService().toString());
                    spinner_hargaJasaService.add(spinnerJasaServiceArray.get(i).getHarga_jasaService().toString());
                }
                ArrayAdapter<String> adapterJasaService = new ArrayAdapter<>(tambah_transaksi_penjualan_service.this, R.layout.spinner_jasa_service_layout,R.id.txtNamaJasaService, spinner_namaJasaService);
                spinner_nama_jasa_service.setAdapter(adapterJasaService);
            }

            @Override
            public void onFailure(Call<LD_JasaService> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void loadSpinnerNamaMontir() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Pegawai.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload nama montir dari database kedalam spinner
        ApiClient_Pegawai apiclientPegawai = retrofit.create(ApiClient_Pegawai.class);
        Call<LD_Pegawai> callNamaPegawai = apiclientPegawai.show();
        callNamaPegawai.enqueue(new Callback<LD_Pegawai>() {
            @Override
            public void onResponse(Call<LD_Pegawai> callNamaJasaService, Response<LD_Pegawai> response) {

                spinnerNamaPegawaiArray = response.body().getData();
                for (int i = 0; i < spinnerNamaPegawaiArray.size(); i++) {
                    if(spinnerNamaPegawaiArray.get(i).getId_role_fk()==4 && spinnerNamaPegawaiArray.get(i).getId_cabang_fk()==selectedIDCabang) {
                        spinner_namaPegawai.clear();
                        spinner_IDPegawai.clear();
                        spinner_namaPegawai.add(spinnerNamaPegawaiArray.get(i).getNama_pegawai());
                        spinner_IDPegawai.add(spinnerNamaPegawaiArray.get(i).getId_pegawai().toString());
                    }
                }
                ArrayAdapter<String> adapterNamaPegawai = new ArrayAdapter<>(tambah_transaksi_penjualan_service.this, R.layout.spinner_jasa_service_layout, R.id.txtNamaJasaService, spinner_namaPegawai);
                spinner_montir.setAdapter(adapterNamaPegawai);
            }

            @Override
            public void onFailure(Call<LD_Pegawai> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    void loadSpinnerNamaCabang(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Cabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload nama cabang dari database kedalam spinner
        ApiClient_Cabang apiclientCabang = retrofit.create(ApiClient_Cabang.class);
        Call<LD_Cabang> callCabang = apiclientCabang.show();
        callCabang.enqueue(new Callback<LD_Cabang>() {
            @Override
            public void onResponse(Call<LD_Cabang> callCabang, Response<LD_Cabang> response) {

                spinnerNamaCabangArray = response.body().getData();
                for (int i = 0; i < spinnerNamaCabangArray.size(); i++) {
                    spinner_namaCabang.add(spinnerNamaCabangArray.get(i).getNama_cabang());
                    spinner_IDCabang.add(spinnerNamaCabangArray.get(i).getId_cabang().toString());
                }
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(tambah_transaksi_penjualan_service.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }
    void loadSpinnerPlatMotor(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_MotorKonsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //ngeload plat motor konsumen dari database kedalam spinner
        ApiClient_MotorKonsumen apiclientMotorKonsumen = retrofit.create(ApiClient_MotorKonsumen.class);
        Call<LD_MotorKonsumen> callMotorKonsumen = apiclientMotorKonsumen.show();
        callMotorKonsumen.enqueue(new Callback<LD_MotorKonsumen>() {
            @Override
            public void onResponse(Call<LD_MotorKonsumen> callMotorKonsumen, Response<LD_MotorKonsumen> response) {

                spinnerNamaMotorKonsumenArray = response.body().getData();
                for (int i = 0; i < spinnerNamaMotorKonsumenArray.size(); i++) {
                    spinner_platMotorKonsumen.add(spinnerNamaMotorKonsumenArray.get(i).getPlat_motorKonsumen());
                    spinner_IDMotorKonsumen.add(spinnerNamaMotorKonsumenArray.get(i).getId_motorKonsumen().toString());
                    Log.d("Plat: ",spinner_platMotorKonsumen.toString());
                }
                ArrayAdapter<String> adapterPlatMotorKonsumen = new ArrayAdapter<>(tambah_transaksi_penjualan_service.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_platMotorKonsumen);
                spinner_plat_konsumen.setAdapter(adapterPlatMotorKonsumen);
            }
            @Override
            public void onFailure(Call<LD_MotorKonsumen> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_transaksi_penjualan.class);
        startActivity(intent);
    }
    private void onClickAddTransaksi() {
        if (detilTransaksiServicetList.isEmpty())
        {
            Toast.makeText(this, "Tambahkan detil jasa service!", Toast.LENGTH_SHORT).show();
        }
        else {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_TransaksiPenjualan.baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson));
            Retrofit retrofit = builder.build();
            ApiClient_TransaksiPenjualan apiClientTransaksiPenjualan = retrofit.create(ApiClient_TransaksiPenjualan.class);

            Log.d( "ID Cbg : ",selectedIDCabang.toString() );
            Log.d( "GrandTotal: ",GrandTotal.toString() );

            Call<ResponseBody> transaksiserviceDAOCall = apiClientTransaksiPenjualan.create("SS", selectedIDCabang, GrandTotal,selectedIDMontir,3);
            transaksiserviceDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response.body().string());
                            String idTransaksi = jsonresponse.getJSONObject("data").getString("id_transaksi");
                            Log.d( "ID Transaksi: ",idTransaksi);

                            //memasukkan data dari list local ke tabel detilTransaksiService
                            for(int x=0;x<detilTransaksiServicetList.size();x++) {
                                Gson gson = new GsonBuilder()
                                        .setLenient()
                                        .create();
                                Retrofit.Builder builder=new Retrofit.
                                        Builder().baseUrl(ApiClient_DetilTransaksi.baseURL).
                                        addConverterFactory(GsonConverterFactory.create(gson));
                                Retrofit retrofit=builder.build();
                                ApiClient_DetilTransaksi apiClientDetilTransaksi = retrofit.create(ApiClient_DetilTransaksi.class);
                                Log.d( "ID Jasa Service: ",detilTransaksiServicetList.get(x).getId_jasaService_fk().toString());
                                Log.d( "ID Motor Konsumen : ",detilTransaksiServicetList.get(x).getId_motorKonsumen_fk().toString() );
                                Log.d( "Get Sub Total: ",detilTransaksiServicetList.get(x).getSubTotal_service().toString() );

                                Call<ResponseBody> detilpengadaansparepartDAOCall = apiClientDetilTransaksi.createDetilTransaksiService(
                                        Integer.parseInt(idTransaksi),
                                        detilTransaksiServicetList.get(x).getId_jasaService_fk(),
                                        detilTransaksiServicetList.get(x).getId_motorKonsumen_fk(),
                                        detilTransaksiServicetList.get(x).getSubTotal_service());

                                detilpengadaansparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.code() == 201) {
                                            Toast.makeText(tambah_transaksi_penjualan_service.this, "Tambah Transaksi Penjualan Service berhasil!", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("on respon : ",String.valueOf(response.code()));
                    startIntent();
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(tambah_transaksi_penjualan_service.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
