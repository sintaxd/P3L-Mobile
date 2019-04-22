package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kel1.com.simato_mobile.View.Owner.owner_main_menu;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextInputLayout textInputUsername, textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  getSupportActionBar().hide();
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
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
                    Intent i = new Intent(MainActivity.this, owner_main_menu.class);
                    startActivity(i);
                }
            });
        }
    }
}
