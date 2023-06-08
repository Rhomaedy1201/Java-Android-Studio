package com.example.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nama = findViewById(R.id.tv_namaMain);
        nama.setText(com.example.usingpreferences.Preferences.getLoggetInUser(getBaseContext()));

        findViewById(R.id.button_logoutMain).setOnClickListener((v) -> {
            Preferences.clearLoggedInUser(getBaseContext());
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
            finish();
        });
    }
}