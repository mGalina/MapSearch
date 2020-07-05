package com.example.mapsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int COORDINATES_LENGTH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        final EditText address = findViewById(R.id.et_address);
        Button search = findViewById(R.id.btn_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMap = new Intent(Intent.ACTION_VIEW);
                final String text = address.getText().toString();

                Uri uri;
                if (text.length() > 0 && Character.isLetter(text.charAt(0))) {
                    uri = Uri.parse("geo:?q=" + text);
                } else {
                    String[] coordinates = text.split(",");
                    if (coordinates.length != COORDINATES_LENGTH) {
                        Toast.makeText(MainActivity.this, R.string.enter_two_coordinates, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    uri = Uri.parse("geo:" + coordinates[0] + "," + coordinates[1]);
                }

                openMap.setData(uri);
                if (openMap.resolveActivity(getPackageManager()) != null) {
                    startActivity(openMap);
                } else {
                    Toast.makeText(MainActivity.this, R.string.no_maps, Toast.LENGTH_SHORT).show();
                    Log.d("Intent", "Browser not found");
                }
            }
        });
    }
}