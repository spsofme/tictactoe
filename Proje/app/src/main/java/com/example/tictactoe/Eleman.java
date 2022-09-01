package com.example.tictactoe;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Eleman extends AppCompatActivity {
    // tanımlamalar
    protected Button btnTekOyuncu, btnIkiOyuncu, btnKolay, btnNormal, btnZor, btnGeri, btnTekrar, btn1_1, btn1_2, btn1_3, btn2_1, btn2_2, btn2_3, btn3_1, btn3_2, btn3_3;
    protected Button[][] btnTablo;
    protected char[] xo = {'X', 'O'};
    protected int turSayisi = 0;
    protected boolean berabere = false;
    protected char[][] tablo = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    // giriş ekranındaki ayarlamaları yapar
    protected void elemanAyarMain() {
        // giriş ekranındaki butonların id lerini atar
        btnTekOyuncu = findViewById(R.id.btn_tek_oyuncu);
        btnIkiOyuncu = findViewById(R.id.btn_iki_oyuncu);
        btnKolay = findViewById(R.id.btn_kolay);
        btnNormal = findViewById(R.id.btn_normal);
        btnZor = findViewById(R.id.btn_zor);

        // giriş ekranındaki butonları etkinleştirir
        btnTekOyuncu.setOnClickListener((View.OnClickListener) this);
        btnIkiOyuncu.setOnClickListener((View.OnClickListener) this);
        btnKolay.setOnClickListener((View.OnClickListener) this);
        btnNormal.setOnClickListener((View.OnClickListener) this);
        btnZor.setOnClickListener((View.OnClickListener) this);

        // butonları bir diziye atadık
        btnTablo = new Button[][] {
                {btn1_1, btn1_2, btn1_3},
                {btn2_1, btn2_2, btn2_3},
                {btn3_1, btn3_2, btn3_3}
        };

        // varsayılan zorluk kolaydan başladığı için kolayın rengi turuncu olarak başlar
        btnKolay.setTextColor(Color.rgb(238, 118, 0));
    }

    // oyun ekranındaki ayarlamaları yapar
    protected void elemanAyarOyun() {
        // oyune kranındaki butonların id lerini atar
        btnGeri = findViewById(R.id.btn_geri);
        btn1_1 = findViewById(R.id.btn_1_1);
        btn1_2 = findViewById(R.id.btn_1_2);
        btn1_3 = findViewById(R.id.btn_1_3);
        btn2_1 = findViewById(R.id.btn_2_1);
        btn2_2 = findViewById(R.id.btn_2_2);
        btn2_3 = findViewById(R.id.btn_2_3);
        btn3_1 = findViewById(R.id.btn_3_1);
        btn3_2 = findViewById(R.id.btn_3_2);
        btn3_3 = findViewById(R.id.btn_3_3);
        btnTekrar = findViewById(R.id.btn_tekrar);

        // butonları bir diziye atadık
        btnTablo = new Button[][] {
                {btn1_1, btn1_2, btn1_3},
                {btn2_1, btn2_2, btn2_3},
                {btn3_1, btn3_2, btn3_3}
        };

        // oyun ekranındaki butonları aktifleştirir
        btnGeri.setOnClickListener((View.OnClickListener) this);
        for (Button[] _tablo : btnTablo) {
            for (Button _buton : _tablo) {
                _buton.setOnClickListener((View.OnClickListener) this);
            }
        }

        // oyun sona erdikten sonra gözükmesi gerek
        btnTekrar.setVisibility(View.INVISIBLE);
    }
}
