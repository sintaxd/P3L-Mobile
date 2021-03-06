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
import kel1.com.simato_mobile.SessionManager.SessionManager;
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
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);

        btnLogin=findViewById(R.id.buttonLogin);

        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn())
        {
            if(session.getIdRole().equalsIgnoreCase("1"))
            {
                Intent i = new Intent(MainActivity.this, owner_main_menu.class);
                startActivity(i);
            }
            else if (session.getIdRole().equalsIgnoreCase("2"))
            {
                Intent i = new Intent(MainActivity.this, cs_main_menu.class);
                startActivity(i);
            }
            else if (session.getIdRole().equalsIgnoreCase("3"))
            {
                Toast.makeText(this, "Kasir tidak memiliki akses device mobile!", Toast.LENGTH_SHORT).show();
            }
            else if (session.getIdRole().equalsIgnoreCase("4"))
            {
                Toast.makeText(this, "Montir tidak memiliki akses device mobile!", Toast.LENGTH_SHORT).show();
            }
        }
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
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();
                    Retrofit.Builder builder = new Retrofit
                            .Builder()
                            .baseUrl(ApiClient_Login.baseURL)
                            .addConverterFactory(GsonConverterFactory.create(gson));
                    Retrofit retrofit = builder.build();
                    ApiClient_Login apiClientLogin = retrofit.create(ApiClient_Login.class);

                    Call<ResponseBody> loginDAOCall = apiClientLogin.mobileauthenticate(input_username.getText().toString(),input_password.getText().toString());
                    loginDAOCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code()==201)
                            {
                                Log.d("Berhasil : ",response.message());
                                Toast.makeText(MainActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                                JSONObject jsonRes = null;
                                try {
                                    jsonRes = new JSONObject(response.body().string());
                                    String id_role = jsonRes.getJSONObject("data").getString("id_role_fk");
                                    String id_cabang = jsonRes.getJSONObject("data").getString("id_cabang_fk");
                                    String id_pegawai = jsonRes.getJSONObject("data").getString("id_pegawai");
                                    String username = jsonRes.getJSONObject("data").getString("username_pegawai");
                                    String password = jsonRes.getJSONObject("data").getString("password_pegawai");

                                    Log.d("ID Cabang: ",id_cabang);
                                   session.createLoginSessions(id_role,username,id_pegawai,id_cabang);
                                    if(id_role.equalsIgnoreCase("1"))
                                    {
                                        Intent i = new Intent(MainActivity.this, owner_main_menu.class);
                                        startActivity(i);
                                    }
                                    else if (id_role.equalsIgnoreCase("2")) {
                                        Intent i = new Intent(MainActivity.this, cs_main_menu.class);
                                        startActivity(i);
                                    }
                                    else if (id_role.equalsIgnoreCase("3"))
                                    {
                                        Toast.makeText(MainActivity.this, "Kasir tidak memiliki akses device mobile!", Toast.LENGTH_LONG).show();
                                    }
                                    else if (id_role.equalsIgnoreCase("4"))
                                    {
                                        Toast.makeText(MainActivity.this, "Montir tidak memiliki akses device mobile!", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Username atau Password salah!", Toast.LENGTH_SHORT).show();
                                Log.d("Rsponse gagal : ",response.message());
                            }


                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(MainActivity.this,  t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Response failure : ",t.getLocalizedMessage());
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
