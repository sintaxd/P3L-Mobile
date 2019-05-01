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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_sparepart extends AppCompatActivity {

    private Button btnBatal,btnSimpan,btnDelete;
    private TextInputEditText nama_spp,kode_spp,merk_spp,tipe_spp;
    private FloatingActionButton mFabChoosePic;
    private ImageView gambar_sparepart;
    private String kode,picture,nama_sparepart,merk_sparepart,tipe_sparepart;
    private Bitmap bitmap, ImageBitmap;
    private Intent i;
    int Image_Request_Code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_sparepart);

        gambar_sparepart = findViewById(R.id.imageView_gambarSparepart);
        nama_spp = findViewById(R.id.text_input_namaSparepart);
        merk_spp = findViewById(R.id.text_input_merkSparepart);
        tipe_spp = findViewById(R.id.text_input_tipeSparepart);

        mFabChoosePic = findViewById(R.id.fabChoosePic);

        btnSimpan = findViewById(R.id.button_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSparepart();
                startIntent();
            }
        });
        btnDelete = findViewById(R.id.button_Hapus);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteSparepart();
                startIntent();
            }
        });
        i = getIntent();
        nama_spp.setText(i.getStringExtra("nama_sparepart"));
        merk_spp.setText(i.getStringExtra("merk_sparepart"));
        tipe_spp.setText(i.getStringExtra("tipe_sparepart"));
        picture = i.getStringExtra("gambar_sparepart");

        kode = i.getStringExtra("kode_sparepart");
        nama_sparepart=i.getStringExtra("nama_sparepart");
        merk_sparepart=i.getStringExtra("merk_sparepart");
        tipe_sparepart= i.getStringExtra("tipe_sparepart");
        Picasso.get().load("http://simato.jasonfw.com/images/"+picture).fit().into(gambar_sparepart);

        btnBatal = findViewById(R.id.button_Batal);
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit_data_sparepart.this, tampil_data_sparepart.class);
                startActivity(i);
            }
        });

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
                gambar_sparepart.setImageBitmap(ImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void startIntent()
    {
        Intent intent= new Intent(getApplicationContext(), tampil_data_sparepart.class);
        startActivity(intent);
    }

    private void UpdateSparepart()
    {
        if(nama_spp.getText().toString().isEmpty() ||
                merk_spp.getText().toString().isEmpty() ||
                tipe_spp.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl(ApiClient_Sparepart.baseURL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();
            ApiClient_Sparepart apiClientSparepart =retrofit.create(ApiClient_Sparepart.class);

            if(ImageBitmap!=null)
            {
                Call<ResponseBody> sparepartDAOCall = apiClientSparepart.update("",nama_spp.getText().toString(),
                        merk_spp.getText().toString(),
                        tipe_spp.getText().toString(),kode);
                sparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 201) {
                            Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("on respon update : ",String.valueOf(response.code()));
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.d("on respon gagal up: ",String.valueOf(t.getLocalizedMessage()));
                    }
                });
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), data);
                MultipartBody.Part body = MultipartBody.Part.createFormData("gambar_sparepart", "image.jpg", requestFile);
                Call<ResponseBody> sparepartDAOImageCall= apiClientSparepart.updateImageMobile(body,kode);
                sparepartDAOImageCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 201) {
                            Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("on respon : ",String.valueOf(response.code()));
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.d("on respon gagal : ",String.valueOf(t.getLocalizedMessage()));
                    }
                });
            }
            else
            {
                Call<ResponseBody> sparepartDAOCall = apiClientSparepart.update("",nama_spp.getText().toString(),
                        merk_spp.getText().toString(),
                        tipe_spp.getText().toString(),kode);
                sparepartDAOCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.code() == 201) {
                            Toast.makeText(getApplicationContext(), "Success Update", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                        Log.d("on respon update : ",String.valueOf(response.code()));
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        Log.d("on respon gagal up: ",String.valueOf(t.getLocalizedMessage()));
                    }
                });
            }
            Log.d("Kode Sparepart : ",kode);
            Log.d("Nama Sparepart : ",nama_spp.getText().toString());
            Log.d("Merk Sparepart : ",merk_spp.getText().toString());
            Log.d("Tipe Sparepart : ",tipe_spp.getText().toString());

        }
    }
    private void DeleteSparepart() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(ApiClient_Sparepart.baseURL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Sparepart apiClientSparepart =retrofit.create(ApiClient_Sparepart.class);
        Call<ResponseBody> sparepartDAOCall = apiClientSparepart.delete(kode);
        sparepartDAOCall.enqueue(new Callback<ResponseBody>() {
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
