package kel1.com.simato_mobile.View.Owner.Sparepart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.ListData.LD_Sparepart;
import kel1.com.simato_mobile.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class tambah_data_sparepart extends AppCompatActivity {

    private Button btnBatal,btnSimpan;
    private FloatingActionButton mFabChoosePic;
    private ImageView image_gambar_sparepart;
    private Bitmap bitmap, ImageBitmap;
    int Image_Request_Code = 1;

    private TextInputEditText nama_spp,kode_spp,merk_spp,tipe_spp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_sparepart);
        nama_spp = findViewById(R.id.text_input_namaSparepart);
        merk_spp = findViewById(R.id.text_input_merkSparepart);
        tipe_spp = findViewById(R.id.text_input_tipeSparepart);
        kode_spp = findViewById(R.id.text_input_kodeSparepart);
        image_gambar_sparepart = findViewById(R.id.imageView_gambarSparepart);

        btnSimpan = findViewById(R.id.button_Simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });

        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_spp.getText().clear();
                merk_spp.getText().clear();
                tipe_spp.getText().clear();
                kode_spp.getText().clear();
            }
        });

        mFabChoosePic = findViewById(R.id.fabChoosePic);
        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
    }
    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                ImageBitmap=bitmap;
                image_gambar_sparepart.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void startIntent() {
        Intent intent = new Intent(getApplicationContext(), tampil_data_sparepart.class);
        startActivity(intent);
    }
    private void onClickRegister() {
        if (nama_spp.getText().toString().isEmpty() ||
            merk_spp.getText().toString().isEmpty() ||
            tipe_spp.getText().toString().isEmpty() ||
            kode_spp.getText().toString().isEmpty() || ImageBitmap==null)
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        } else
            {
                Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Sparepart.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();
                ApiClient_Sparepart apiClient = retrofit.create(ApiClient_Sparepart.class);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), data);

                MultipartBody.Part gambar_sparepart = MultipartBody.Part.createFormData("image_gambar_sparepart", "image.jpg", requestFile);

                RequestBody kode_sparepart = RequestBody.create(MediaType.parse("multipart/form-data"), kode_spp.getText().toString());
                RequestBody nama_sparepart = RequestBody.create(MediaType.parse("multipart/form-data"), nama_spp.getText().toString());
                RequestBody merk_sparepart = RequestBody.create(MediaType.parse("multipart/form-data"), merk_spp.getText().toString());
                RequestBody tipe_sparepart = RequestBody.create(MediaType.parse("multipart/form-data"), tipe_spp.getText().toString());

                Log.d("Kode Sparepart : ",kode_spp.getText().toString());
                Log.d("Nama Sparepart : ",nama_spp.getText().toString());
                Log.d("Merk Sparepart : ",merk_spp.getText().toString());
                Log.d("Tipe Sparepart : ",tipe_spp.getText().toString());

                Call<ResponseBody> sparepartDAOCall = apiClient.create(gambar_sparepart, kode_sparepart, nama_sparepart, merk_sparepart, tipe_sparepart);
            sparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        Toast.makeText(tambah_data_sparepart.this, "Tambah Sparepart berhasil!", Toast.LENGTH_SHORT).show();
                        startIntent();
                    } else {
                        Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                    }
                    Log.d("on respon : ",String.valueOf(response.code()));
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(tambah_data_sparepart.this, "ea gagal ea", Toast.LENGTH_SHORT).show();
                    Log.d("on respon gagal : ",String.valueOf(t.getLocalizedMessage()));
                }
            });
        }
    }
}
