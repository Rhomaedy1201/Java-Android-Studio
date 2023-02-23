package com.example.widgetdaneven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Penjumlahan extends AppCompatActivity {

    EditText input1, input2;
    TextView hasil;
    Button hitung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjumlahan);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        hasil = findViewById(R.id.hasil);
        hitung = findViewById(R.id.hitung);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nilai1 = input1.getText().toString();
                String nilai2 = input2.getText().toString();

                int angka1 = Integer.valueOf(nilai1);
                int angka2 = Integer.valueOf(nilai2);
                int hasil_jumlah = Integer.valueOf(angka1 + angka2);

                hasil.setText(String.valueOf("Hasil : "+hasil_jumlah));
            }
        });

    }

}