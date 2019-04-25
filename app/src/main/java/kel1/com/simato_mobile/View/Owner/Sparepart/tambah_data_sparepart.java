package kel1.com.simato_mobile.View.Owner.Sparepart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

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
   // private CircleImageView mPicture;
    private ImageView mPicture2;
    private String picture;
    private Bitmap bitmap;
    private TextInputEditText nama_sparepart, merk_sparepart, kode_sparepart, tipe_sparepart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_sparepart);
        nama_sparepart = findViewById(R.id.text_input_namaSparepart);
        merk_sparepart = findViewById(R.id.text_input_merkSparepart);
        tipe_sparepart = findViewById(R.id.text_input_tipeSparepart);
        kode_sparepart = findViewById(R.id.text_input_kode_sparepart);

    //    mPicture = findViewById(R.id.picture);
        mPicture2 = findViewById(R.id.testImageView);
        mFabChoosePic = findViewById(R.id.fabChoosePic);

        btnSimpan = findViewById(R.id.button_Simpan);
        btnBatal = findViewById(R.id.button_Batal);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_sparepart.getText().clear();
                merk_sparepart.getText().clear();
                tipe_sparepart.getText().clear();
                kode_sparepart.getText().clear();
            }
        });

        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        Intent intent = getIntent();
        picture = intent.getStringExtra("picture");
        //setDataFromIntentExtra();
    }
    private void setDataFromIntentExtra() {

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.add);
            requestOptions.error(R.drawable.add);

            Glide.with(tambah_data_sparepart.this)
                    .load(picture)
                    .apply(requestOptions)
                    .into(mPicture2);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mPicture2.setImageBitmap(bitmap);


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
        if (nama_sparepart.getText().toString().isEmpty() ||
            merk_sparepart.getText().toString().isEmpty() ||
            tipe_sparepart.getText().toString().isEmpty() ||
            kode_sparepart.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        } else {

            String picture = null;
            if (bitmap == null) {
                picture = "";
            } else {
                picture = getStringImage(bitmap);
            }

            Retrofit.Builder builder = new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_Sparepart.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            ApiClient_Sparepart apiClient = retrofit.create(ApiClient_Sparepart.class);
            Call<LD_Sparepart> sparepartDAOCall = apiClient.create(nama_sparepart.getText().toString(),
                    merk_sparepart.getText().toString(),
                    tipe_sparepart.getText().toString(),
                    kode_sparepart.getText().toString(),
                    picture);
            sparepartDAOCall.enqueue(new Callback<LD_Sparepart>() {
                @Override
                public void onResponse(Call<LD_Sparepart> call, Response<LD_Sparepart> response) {
                    if (response.code() == 201) {
                        Toast.makeText(tambah_data_sparepart.this, "Tambah Sparepart berhasil!", Toast.LENGTH_SHORT).show();
                        startIntent();
                    } else {
                        Toast.makeText(getApplicationContext(),response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<LD_Sparepart> call, Throwable t) {
                    Toast.makeText(tambah_data_sparepart.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
