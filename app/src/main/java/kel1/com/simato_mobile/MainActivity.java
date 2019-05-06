package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kel1.com.simato_mobile.API.ApiClient_Login;
import kel1.com.simato_mobile.API.ApiClient_Supplier;
import kel1.com.simato_mobile.Model.Model_Supplier;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tambah_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.Konsumen.tampil_data_konsumen;
import kel1.com.simato_mobile.View.CustomerService.cs_main_menu;
import kel1.com.simato_mobile.View.Owner.Supplier.tambah_data_supplier;
import kel1.com.simato_mobile.View.Owner.owner_main_menu;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextInputLayout textInputUsername, textInputPassword;
    private TextInputEditText input_username, input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field tidak boleh kosong!");
            return false;
        } else if (usernameInput.length() > 15 || usernameInput.length() < 6) {
            textInputUsername.setError("Username terdiri dari 6-15 karakter!");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field tidak boleh kosong!");
            return false;
        } else if (passwordInput.length() > 15 || passwordInput.length() < 6) {
            textInputPassword.setError("Password terdiri dari 6-15 karakter!");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateUsername() | !validatePassword()) {
            return;
        }
        else
        {
            btnLogin=findViewById(R.id.buttonLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();
                    Retrofit.Builder builder = new Retrofit
                            .Builder()
                            .baseUrl(ApiClient_Supplier.baseURL)
                            .addConverterFactory(GsonConverterFactory.create(gson));
                    Retrofit retrofit = builder.build();
                    ApiClient_Login apiClientLogin = retrofit.create(ApiClient_Login.class);

                    Call<ResponseBody> loginDAOCall = apiClientLogin.mobileauthenticate(input_username.getText().toString(),input_password.getText().toString());
                    loginDAOCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code()==201)
                            {
                                Log.d("Rsponse : ",response.message());
                                Toast.makeText(MainActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                                JSONObject jsonRes = null;
                                try {
                                    jsonRes = new JSONObject(response.body().string());
                                    Integer id_role = jsonRes.getJSONObject("data").getInt("id_role_fk");
                                    if(id_role==1)
                                    {
                                        Intent i = new Intent(MainActivity.this, owner_main_menu.class);
                                        startActivity(i);
                                    }
                                    else if (id_role==2)
                                    {
                                        Intent i = new Intent(MainActivity.this, cs_main_menu.class);
                                        startActivity(i);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                Log.d("Rsponse gagal : ",response.message());
                            }


                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(MainActivity.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
//                    Intent i = new Intent(MainActivity.this, owner_main_menu.class);
//                    startActivity(i);
                }
            });
        }
    }
}
