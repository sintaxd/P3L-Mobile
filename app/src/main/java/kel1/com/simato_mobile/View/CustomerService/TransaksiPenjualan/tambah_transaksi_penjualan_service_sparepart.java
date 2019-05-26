package kel1.com.simato_mobile.View.CustomerService.TransaksiPenjualan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TextInputEditText;
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

import kel1.com.simato_mobile.API.ApiClient_Cabang;
import kel1.com.simato_mobile.API.ApiClient_DetilTransaksi;
import kel1.com.simato_mobile.API.ApiClient_JasaService;
import kel1.com.simato_mobile.API.ApiClient_Konsumen;
import kel1.com.simato_mobile.API.ApiClient_Motor;
import kel1.com.simato_mobile.API.ApiClient_MotorKonsumen;
import kel1.com.simato_mobile.API.ApiClient_Pegawai;
import kel1.com.simato_mobile.API.ApiClient_SparepartCabang;
import kel1.com.simato_mobile.API.ApiClient_TransaksiPenjualan;
import kel1.com.simato_mobile.Adapter.Adapter_DetilTransaksiService;
import kel1.com.simato_mobile.Adapter.Adapter_DetilTransaksiSparepart;
import kel1.com.simato_mobile.ListData.LD_Cabang;
import kel1.com.simato_mobile.ListData.LD_JasaService;
import kel1.com.simato_mobile.ListData.LD_MotorKonsumen;
import kel1.com.simato_mobile.ListData.LD_Pegawai;
import kel1.com.simato_mobile.ListData.LD_SparepartCabang;
import kel1.com.simato_mobile.Model.Model_Cabang;
import kel1.com.simato_mobile.Model.Model_DetilTransaksiService;
import kel1.com.simato_mobile.Model.Model_DetilTransaksiSparepart;
import kel1.com.simato_mobile.Model.Model_JasaService;
import kel1.com.simato_mobile.Model.Model_MotorKonsumen;
import kel1.com.simato_mobile.Model.Model_Pegawai;
import kel1.com.simato_mobile.Model.Model_SparepartCabang;
import kel1.com.simato_mobile.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_transaksi_penjualan_service_sparepart extends AppCompatActivity {


    Spinner spinner_nama_jasa_service, spinner_cabang, spinner_montir, spinner_plat_konsumen, spinner_sparepartcabang;

    List<Model_JasaService> spinnerJasaServiceArray = new ArrayList<>();
    List<Model_Cabang> spinnerNamaCabangArray = new ArrayList<>();
    List<Model_Pegawai> spinnerNamaPegawaiArray = new ArrayList<>();
    List<Model_MotorKonsumen> spinnerNamaMotorKonsumenArray = new ArrayList<>();
    List<Model_SparepartCabang> spinnerNamaSparepartCabangArray = new ArrayList<>();

    List<String> spinner_IDJasaService = new ArrayList<>();
    List<String> spinner_namaJasaService = new ArrayList<>();
    List<String> spinner_hargaJasaService = new ArrayList<>();

    List<String> spinner_namaSparepartCabang = new ArrayList<>();
    List<String> spinner_IDSparepartCabang = new ArrayList<>();
    List<String> spinner_hargaSparepart = new ArrayList<>();

    List<String> spinner_IDCabang = new ArrayList<>();
    List<String> spinner_namaCabang = new ArrayList<>();

    List<String> spinner_IDPegawai = new ArrayList<>();
    List<String> spinner_namaPegawai = new ArrayList<>();

    List<String> spinner_IDMotorKonsumen = new ArrayList<>();
    List<String> spinner_platMotorKonsumen = new ArrayList<>();
    private List<Model_DetilTransaksiService> detilTransaksiServicetList = new ArrayList<Model_DetilTransaksiService>();
    private List<Model_DetilTransaksiSparepart> detilTransaksiSparepartList = new ArrayList<Model_DetilTransaksiSparepart>();

    Integer tempIDJasaService, selectedIDCabang, selectedIDMontir, selectedIDJasaService, selectedIDMotorKonsumen;
    String selectedNamaJasaService;
    Button btnBatal, btnSimpan;
    Double GrandTotal=0.0, selectedHargaJasaService;
    RecyclerView rview_service, rview_sparepart;
    private Adapter_DetilTransaksiService adapter_service;
    private Adapter_DetilTransaksiSparepart adapter_sparepart;

    private RecyclerView.LayoutManager layout_service, layout_sparepart;

    TextView setTanggal, totalHarga_fix, namaKonsumen_fix;
    ImageView addDetilTransaksiService;
    Integer tempID, tempIDKonsumen;
    String selectedIDSparepartCabang,  selectedHargaSparepartCabang, selectedNamaSparepartCabang;
    ImageView addDetilTransaksiSparepart, imgSearch;
    private TextInputEditText input_satuan;

    String date_now, temp_nama;
    TextInputEditText nama_konsumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi_penjualan_service_sparepart);
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
        spinner_sparepartcabang = findViewById(R.id.spinner_sparepart_cabang);

        input_satuan = findViewById(R.id.text_input_satuan);
        totalHarga_fix = findViewById(R.id.textView_totalHargaFix);
        setTanggal = findViewById(R.id.textView_tanggalFix);
        imgSearch = findViewById(R.id.img_Search);
        nama_konsumen = findViewById(R.id.text_input_namaKonsumen);
        namaKonsumen_fix = findViewById(R.id.textView_namaKonsumen);

        rview_service = findViewById(R.id.recycler_view_detil_transaksi_service);
        layout_service = new LinearLayoutManager(getApplicationContext());
        rview_service.setLayoutManager(layout_service);

        rview_sparepart = findViewById(R.id.recycler_view_detil_transaksi_sparepart);
        layout_sparepart = new LinearLayoutManager(getApplicationContext());
        rview_sparepart.setLayoutManager(layout_sparepart);

        imgSearch = findViewById(R.id.img_Search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addKonsumenFunction();
            }
        });
        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddTransaksiServiceSparepart();
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
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, "miaaw", Toast.LENGTH_SHORT).show();
            }
        });
        addDetilTransaksiSparepart = findViewById(R.id.addDetilTransaksiSparepart);
        addDetilTransaksiSparepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDetilTransaksiSparepartFunction();
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, "miaaw", Toast.LENGTH_SHORT).show();
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
                loadSpinnerNamaSparepartCabang();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("updateTotal"));
    }
    public void addKonsumenFunction(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Konsumen.baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        ApiClient_Konsumen apiClientKonsumen = retrofit.create(ApiClient_Konsumen.class);
        Call<ResponseBody> konsumenDAOCall = apiClientKonsumen.showByName(nama_konsumen.getText().toString());
        konsumenDAOCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {

                    try {
                        JSONObject jsonresponse = new JSONObject(response.body().string());
                        Log.d("onResponse: ",response.body().toString());
                        String temp_nama = jsonresponse.getJSONObject("data").getString("nama_konsumen");
                        tempIDKonsumen = Integer.parseInt(jsonresponse.getJSONObject("data").getString("id_konsumen"));
                        Log.d("Nama: ",temp_nama);
                        Log.d("ID Konsumen: ",tempIDKonsumen.toString());
                        namaKonsumen_fix.setText(temp_nama);
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

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (IOException i)
                    {
                        i.printStackTrace();
                    }
                    Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, "Data Konsumen ditemukan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                }
                Log.d("on respon : ",String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
    public void addDetilTransaksiSparepartFunction(){
        Double sub_total_sparepart;
        sub_total_sparepart=Double.parseDouble(input_satuan.getText().toString())*Double.parseDouble(selectedHargaSparepartCabang);

        detilTransaksiSparepartList.add(new Model_DetilTransaksiSparepart(
                Integer.parseInt(selectedIDSparepartCabang),
                tempIDKonsumen,
                Integer.parseInt(input_satuan.getText().toString()),
                sub_total_sparepart,
                Double.parseDouble(selectedHargaSparepartCabang),
                selectedNamaSparepartCabang));

        adapter_sparepart = new Adapter_DetilTransaksiSparepart(detilTransaksiSparepartList);
        rview_sparepart.setAdapter(adapter_sparepart);
        GrandTotal=GrandTotal+sub_total_sparepart;
        totalHarga_fix.setText(GrandTotal.toString());
        input_satuan.getText().clear();
    }
    private void addDetilTransaksiServiceFunction(){
        Double sub_total_service;
        sub_total_service=selectedHargaJasaService;
        detilTransaksiServicetList.add(new Model_DetilTransaksiService(sub_total_service, selectedIDMotorKonsumen,selectedIDJasaService, selectedNamaJasaService,selectedHargaJasaService));
        Log.d("addDetil: ",detilTransaksiServicetList.toString());
        adapter_service = new Adapter_DetilTransaksiService(detilTransaksiServicetList);
        rview_service.setAdapter(adapter_service);
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
                ArrayAdapter<String> adapterJasaService = new ArrayAdapter<>(tambah_transaksi_penjualan_service_sparepart.this, R.layout.spinner_jasa_service_layout,R.id.txtNamaJasaService, spinner_namaJasaService);
                spinner_nama_jasa_service.setAdapter(adapterJasaService);
            }

            @Override
            public void onFailure(Call<LD_JasaService> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<String> adapterNamaPegawai = new ArrayAdapter<>(tambah_transaksi_penjualan_service_sparepart.this, R.layout.spinner_jasa_service_layout, R.id.txtNamaJasaService, spinner_namaPegawai);
                spinner_montir.setAdapter(adapterNamaPegawai);
            }

            @Override
            public void onFailure(Call<LD_Pegawai> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                ArrayAdapter<String> adapterNamaCabang = new ArrayAdapter<>(tambah_transaksi_penjualan_service_sparepart.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_namaCabang);
                spinner_cabang.setAdapter(adapterNamaCabang);
            }
            @Override
            public void onFailure(Call<LD_Cabang> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        Log.d("ID Konsumen plat: ",tempIDKonsumen.toString());
        Call<LD_MotorKonsumen> callMotorKonsumen = apiclientMotorKonsumen.showById(tempIDKonsumen);
        callMotorKonsumen.enqueue(new Callback<LD_MotorKonsumen>() {
            @Override
            public void onResponse(Call<LD_MotorKonsumen> callMotorKonsumen, Response<LD_MotorKonsumen> response) {

                spinnerNamaMotorKonsumenArray = response.body().getData();
                spinner_platMotorKonsumen.clear();
                for (int i = 0; i < spinnerNamaMotorKonsumenArray.size(); i++) {
                    spinner_platMotorKonsumen.add(spinnerNamaMotorKonsumenArray.get(i).getPlat_motorKonsumen());
                    spinner_IDMotorKonsumen.add(spinnerNamaMotorKonsumenArray.get(i).getId_motorKonsumen().toString());
                    Log.d("Plat: ",spinner_platMotorKonsumen.toString());
                }
                ArrayAdapter<String> adapterPlatMotorKonsumen = new ArrayAdapter<>(tambah_transaksi_penjualan_service_sparepart.this, R.layout.spinner_cabang_layout, R.id.txtNamaCabang, spinner_platMotorKonsumen);
                spinner_plat_konsumen.setAdapter(adapterPlatMotorKonsumen);
            }
            @Override
            public void onFailure(Call<LD_MotorKonsumen> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure: ",t.getLocalizedMessage());
            }
        });
    }
    void loadSpinnerNamaSparepartCabang()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_SparepartCabang.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //ngeload nama sparepart cabang dari database kedalam spinner
        ApiClient_SparepartCabang apiClientSparepartCabang = retrofit.create(ApiClient_SparepartCabang.class);
        Call<LD_SparepartCabang> callSparepartCabang = apiClientSparepartCabang.showByCabang(selectedIDCabang);

        callSparepartCabang.enqueue(new Callback<LD_SparepartCabang>() {
            @Override
            public void onResponse(Call<LD_SparepartCabang> callSparepartCabang, Response<LD_SparepartCabang> response) {
                spinner_namaSparepartCabang.clear();
                spinnerNamaSparepartCabangArray =response.body().getData();
                for (int i = 0; i < spinnerNamaSparepartCabangArray.size(); i++) {
                    spinner_namaSparepartCabang.add(spinnerNamaSparepartCabangArray.get(i).getNama_sparepart());
                    spinner_IDSparepartCabang.add(spinnerNamaSparepartCabangArray.get(i).getId_sparepartCabang().toString());
                    spinner_hargaSparepart.add(spinnerNamaSparepartCabangArray.get(i).getHargaBeli_sparepart().toString());
                }
                ArrayAdapter<String> adapterNamaSparepartCabang = new ArrayAdapter<>(tambah_transaksi_penjualan_service_sparepart.this, R.layout.spinner_sparepart_layout, R.id.txtNamaSparepart, spinner_namaSparepartCabang);
                spinner_sparepartcabang.setAdapter(adapterNamaSparepartCabang);
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, response.message(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<LD_SparepartCabang> call, Throwable t) {
                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        spinner_sparepartcabang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //Listener dropdown tipe sparepart saat dipilih
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedIDSparepartCabang = spinner_IDSparepartCabang.get(position); //Mendapatkan id dari dropdown yang dipilih
                selectedHargaSparepartCabang = spinner_hargaSparepart.get(position);
                selectedNamaSparepartCabang = spinner_namaSparepartCabang.get(position);
                Log.d("ID SparepartCabang : ", selectedIDSparepartCabang);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_transaksi_penjualan.class);
        startActivity(intent);
    }
    private void onClickAddTransaksiServiceSparepart() {
        if (detilTransaksiServicetList.isEmpty())
        {
            Toast.makeText(this, "Tambahkan detil jasa service!", Toast.LENGTH_SHORT).show();
        }
        else if (detilTransaksiSparepartList.isEmpty())
        {
            Toast.makeText(this, "Tambahkan detil sparepart!", Toast.LENGTH_SHORT).show();
        }
        else
            {
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

            Call<ResponseBody> transaksiservicesparepartDAOCall = apiClientTransaksiPenjualan.create("SV", selectedIDCabang, GrandTotal,selectedIDMontir,3);
                transaksiservicesparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response.body().string());
                            String idTransaksi = jsonresponse.getJSONObject("data").getString("id_transaksi");
                            Log.d("ID Transaksi: ", idTransaksi);

                            //memasukkan data dari list local ke tabel detilTransaksiService
                            for (int x = 0; x < detilTransaksiServicetList.size(); x++) {
                                Gson gson = new GsonBuilder()
                                        .setLenient()
                                        .create();
                                Retrofit.Builder builder = new Retrofit.
                                        Builder().baseUrl(ApiClient_DetilTransaksi.baseURL).
                                        addConverterFactory(GsonConverterFactory.create(gson));
                                Retrofit retrofit = builder.build();
                                ApiClient_DetilTransaksi apiClientDetilTransaksi = retrofit.create(ApiClient_DetilTransaksi.class);
                                Log.d("ID Jasa Service: ", detilTransaksiServicetList.get(x).getId_jasaService_fk().toString());
                                Log.d("ID Motor Konsumen : ", detilTransaksiServicetList.get(x).getId_motorKonsumen_fk().toString());
                                Log.d("Get Sub Total: ", detilTransaksiServicetList.get(x).getSubTotal_service().toString());

                                Call<ResponseBody> detilpengadaansparepartDAOCall = apiClientDetilTransaksi.createDetilTransaksiService(
                                        Integer.parseInt(idTransaksi),
                                        detilTransaksiServicetList.get(x).getId_jasaService_fk(),
                                        detilTransaksiServicetList.get(x).getId_motorKonsumen_fk(),
                                        detilTransaksiServicetList.get(x).getSubTotal_service());

                                detilpengadaansparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.code() == 201) {
                                            Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, "Tambah Transaksi Penjualan Service Sparepart berhasil!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                //memasukkan data dari list local ke tabel detilTransaksiSparepart
                                for (int y = 0; y < detilTransaksiSparepartList.size(); y++) {

                                    Log.d("ID Sparepart Cabang: ", detilTransaksiSparepartList.get(x).getId_sparepartCabang_fk().toString());
                                    Log.d("ID Konsumen : ", detilTransaksiSparepartList.get(x).getId_konsumen_fk().toString());
                                    Log.d("Jumlah Beli Spp : ", detilTransaksiSparepartList.get(x).getJumlahBeli_sparepart().toString());
                                    Log.d("Get Sub Total: ", detilTransaksiSparepartList.get(x).getSubTotal_sparepart().toString());

                                    Call<ResponseBody> detiltransaksisparepartDAOCall = apiClientDetilTransaksi.createDetilTransaksiSparepart(
                                            Integer.parseInt(idTransaksi),
                                            detilTransaksiSparepartList.get(x).getId_sparepartCabang_fk(),
                                            detilTransaksiSparepartList.get(x).getId_konsumen_fk(),
                                            detilTransaksiSparepartList.get(x).getJumlahBeli_sparepart(),
                                            detilTransaksiSparepartList.get(x).getSubTotal_sparepart());

                                    detiltransaksisparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.code() == 201) {
                                                Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this, "Tambah Transaksi Penjualan Sparepart berhasil!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
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
                    Toast.makeText(tambah_transaksi_penjualan_service_sparepart.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
