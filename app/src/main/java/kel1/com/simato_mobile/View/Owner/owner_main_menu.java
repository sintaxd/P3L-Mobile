package kel1.com.simato_mobile.View.Owner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kel1.com.simato_mobile.MainActivity;
import kel1.com.simato_mobile.R;

public class owner_main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);
    }

    public void pengelolaan_data(View view) {
        Intent i= new Intent(owner_main_menu.this,owner_pengelolaan_data.class);
        startActivity(i);
    }
}
