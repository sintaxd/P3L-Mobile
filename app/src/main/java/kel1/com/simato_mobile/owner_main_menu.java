package kel1.com.simato_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class owner_main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView pengelolaanData;
        TextView pengelolaanData1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main_menu);
        getSupportActionBar().hide();

        pengelolaanData=(ImageView)findViewById(R.id.img_pengelolaanData);
        pengelolaanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_main_menu.this, owner_pengelolaan_data.class);
                startActivity(i);
            }
        });
        pengelolaanData1=(TextView)findViewById(R.id.textView_pengelolaanData);
        pengelolaanData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(owner_main_menu.this, owner_pengelolaan_data.class);
                startActivity(i);
            }
        });
    }
}
