/*
 *
 * CREATED BY : Kerim ER
 *
 * CREATED DATE : 28.01.2020 : 00.20
 *
 */

package com.example.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Eleman implements View.OnClickListener {
    private Intent intent;
    private int zorluk = 0, oyuncuSayisi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // giriş ekranındaki ayarlamaları yapar
        intent = new Intent(getApplicationContext(), OyunEkrani.class);
        elemanAyarMain();
    }

    @Override
    public void onClick(View v) {
        // giriş ekranındaki tıklamaları çalıştırır
        switch (v.getId()) {
            case R.id.btn_tek_oyuncu:
                oyuncuSayisi = 1;
                intent.putExtra("ayar", zorluk + " " + oyuncuSayisi);
                startActivity(intent);
                finish();
                break;

            case R.id.btn_iki_oyuncu:
                oyuncuSayisi = 2;
                intent.putExtra("ayar", zorluk + " " + oyuncuSayisi);
                startActivity(intent);
                finish();
                break;

            case R.id.btn_kolay:
                btnKolay.setTextColor(Color.rgb(238, 118, 0));
                btnNormal.setTextColor(Color.WHITE);
                btnZor.setTextColor(Color.WHITE);
                zorluk = 0;
                break;

            case R.id.btn_normal:
                btnNormal.setTextColor(Color.rgb(238, 118, 0));
                btnKolay.setTextColor(Color.WHITE);
                btnZor.setTextColor(Color.WHITE);
                zorluk = 1;
                break;

            case R.id.btn_zor:
                btnZor.setTextColor(Color.rgb(238, 118, 0));
                btnNormal.setTextColor(Color.WHITE);
                btnKolay.setTextColor(Color.WHITE);
                zorluk = 2;
                break;
        }
    }
}
