package com.example.mapsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        EditText address = findViewById(R.id.et_address);
        Button search = findViewById(R.id.btn_search);

        final String text = address.getText().toString();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap = new Intent();
                openMap.setAction(Intent.ACTION_VIEW);
                if (text.length() > 0 && Character.isLetter(text.charAt(0))) {
                    Uri uri = Uri.parse("geo:55.704968, 69.625206");
                    openMap.setData(uri);
                    if (openMap.resolveActivity(getPackageManager()) != null) {
                        startActivity(openMap);
                    } else {
                        Log.d("Intent", "Browser not found");
                    }
                } else {
                    Uri uri = Uri.parse("geo:?q=Moscow");
                    openMap.setData(uri);
                    if (openMap.resolveActivity(getPackageManager()) != null) {
                        startActivity(openMap);
                    } else {
                        Log.d("Intent", "Browser not found");
                    }
                }
            }
        });
    }
}