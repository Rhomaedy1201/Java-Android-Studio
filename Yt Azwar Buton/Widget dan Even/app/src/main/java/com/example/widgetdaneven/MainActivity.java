package com.example.widgetdaneven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView judul;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        judul = findViewById(R.id.judul);
        input = findViewById(R.id.input);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence isian, int index, int i1, int i2) {
//                judul.setText(isian.toString());
//                judul.setText(input.getText().toString());
                judul.setText(String.valueOf(index+1));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void LihatJumlah(View view){
        Intent intent = new Intent(MainActivity.this, Penjumlahan.class);
        startActivity(intent);
    }
}