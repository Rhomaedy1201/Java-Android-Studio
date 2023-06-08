package com.example.testquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView kuis;
    RadioGroup rg;
    RadioButton pilihanA, pilihanB, pilihanC, pilihanD;
    int nomor = 0;
    public static int hasil, bener, salah;

    // Pertanyaan
    String[] pertanyaan_kuis = new String[]{
            "1. Presiden Indonesia yang keenam adalah",
            "2. Lambang negara indonesia adalah",
            "3. Ibu kota Indonesia adalah",
            "4. Lagu kebanggaan Indonesia adalah",
            "5. Bendera negara indonsia adalah"
    };

    // Pilihan jawaban a,b,c,d
    String[] pilihan_jawaban = new String[]{
            "Soekarno", "Habibie", "Susilo Bambang Yudhoyono", "Joko widodo",
            "Gajah putih", "Garuda", "Macan", "Elang",
            "Jakarta", "Bogor", "Tanggerang", "Bekasi",
            "Indonesia Raya", "tanah airku", "Indonesia pusaka", "Indonesia merdeka",
            "Merah biru putih", "Merah putih", "Putih merah", "Belang-belang"
    };

    //Jawaban benar
    String[] jawaban_benar = new String[]{
            "Susilo Bambang Yudhoyono",
            "Garuda",
            "Jakarta",
            "Indonesia Raya",
            "Merah putih"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kuis = findViewById(R.id.kuis);
        rg = findViewById(R.id.pilihan);
        pilihanA = findViewById(R.id.pilihanA);
        pilihanB = findViewById(R.id.pilihanB);
        pilihanC = findViewById(R.id.pilihanC);
        pilihanD = findViewById(R.id.pilihanD);

        kuis.setText(pertanyaan_kuis[nomor]);
        pilihanA.setText(pilihan_jawaban[0]);
        pilihanB.setText(pilihan_jawaban[1]);
        pilihanC.setText(pilihan_jawaban[2]);
        pilihanD.setText(pilihan_jawaban[3]);

        rg.check(0);
        bener = 0;
        salah = 0;
    }

    public void next(View view){
        if (pilihanA.isChecked() || pilihanB.isChecked() || pilihanC.isChecked() || pilihanD.isChecked()){
            RadioButton jawaban_user = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            String ambil_jawaban_user = jawaban_user.getText().toString();
            rg.check(0);
            if (ambil_jawaban_user.equalsIgnoreCase(jawaban_benar[nomor])) bener++;
            else salah ++;
            nomor ++;
            if (nomor < pertanyaan_kuis.length){
                kuis.setText(pertanyaan_kuis[nomor]);
                pilihanA.setText(pilihan_jawaban[(nomor * 4) + 0]);
                pilihanB.setText(pilihan_jawaban[(nomor * 4) + 1]);
                pilihanC.setText(pilihan_jawaban[(nomor * 4) + 2]);
                pilihanD.setText(pilihan_jawaban[(nomor * 4) + 3]);
            }else{
                hasil = bener * 20;
                Intent selesai =  new Intent(getApplicationContext(), HasilKuis.class);
                startActivity(selesai);
            }
        }else{
            Toast.makeText(this, "Kamu jawab dulu", Toast.LENGTH_LONG).show();
        }
    }
}