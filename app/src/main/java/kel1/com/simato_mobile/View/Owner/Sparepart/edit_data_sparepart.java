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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import kel1.com.simato_mobile.API.ApiClient_Sparepart;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.R;
import kel1.com.simato_mobile.View.Owner.Supplier.edit_data_supplier;
import kel1.com.simato_mobile.View.Owner.Supplier.tampil_data_supplier;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit_data_sparepart extends AppCompatActivity {

    private Button btnBatal,btnSimpan,btnDelete;
    private TextInputEditText nama_sparepart, merk_sparepart, kode_sparepart, tipe_sparepart;
    private FloatingActionButton mFabChoosePic;
    //private CircleImageView mPicture;
    private ImageView mPicture2;
    private String kode,picture;
    private Bitmap bitmap;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_sparepart);

        btnBatal = findViewById(R.id.button_Batal);

        mPicture2 = findViewById(R.id.testImageView);
        nama_sparepart = findViewById(R.id.text_input_namaSparepart);
        merk_sparepart = findViewById(R.id.text_input_merkSparepart);
        kode_sparepart = findViewById(R.id.text_input_kodeSparepart);
        tipe_sparepart = findViewById(R.id.text_input_tipeSparepart);

       // mPicture = findViewById(R.id.picture);
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
        nama_sparepart.setText(i.getStringExtra("nama_sparepart"));
        merk_sparepart.setText(i.getStringExtra("merk_sparepart"));
        kode_sparepart.setText(i.getStringExtra("kode_sparepart"));
        tipe_sparepart.setText(i.getStringExtra("tipe_sparepart"));

        //kode sparepart belum
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

        Glide.with(edit_data_sparepart.this)
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
    public void startIntent()
    {
        Intent intent= new Intent(getApplicationContext(), tampil_data_sparepart.class);
        startActivity(intent);
    }
    private void UpdateSparepart()
    {
        if(nama_sparepart.getText().toString().isEmpty() || merk_sparepart.getText().toString().isEmpty() ||
                kode_sparepart.getText().toString().isEmpty()|| tipe_sparepart.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String picture = null;
            if (bitmap == null) {
                picture = "";
            } else {
                picture = getStringImage(bitmap);
            }

            Retrofit.Builder builder=new Retrofit
                    .Builder()
                    .baseUrl("http://simato.jasonfw.com/") //http://10.53.0.204:8000/ local
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit=builder.build();
            ApiClient_Sparepart apiClientSparepart =retrofit.create(ApiClient_Sparepart.class);

            kode=kode_sparepart.getText().toString();

            Call<ResponseBody> supplierDAOCall= apiClientSparepart.update(nama_sparepart.getText().toString(),
                    merk_sparepart.getText().toString(),
                    tipe_sparepart.getText().toString(),
                    kode_sparepart.getText().toString(),
                    picture,
                    kode);
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
    private void DeleteSparepart() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl("http://simato.jasonfw.com/")  //http://10.53.0.204:8000/
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();
        ApiClient_Sparepart apiClientSparepart =retrofit.create(ApiClient_Sparepart.class);
        kode=kode_sparepart.getText().toString();
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
