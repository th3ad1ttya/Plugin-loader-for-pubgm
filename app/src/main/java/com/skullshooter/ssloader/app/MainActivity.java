package com.skullshooter.ssloader.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_elements);

        final TextView ttl = (TextView) findViewById(R.id.ttl_skull);
        ttl.setText("SkullShooter Loader v"+BuildConfig.VERSION_NAME);
        final RelativeLayout selection_32bit = (RelativeLayout) findViewById(R.id.selection_32bit);
        final RelativeLayout selection_64bit = (RelativeLayout) findViewById(R.id.selection_64bit);
        selection_32bit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        selection_64bit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}