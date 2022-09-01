package com.example.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class OyunEkrani extends Eleman implements View.OnClickListener {
    private Tablo tabloClass = new Tablo();
    private BitisKontrol bk = new BitisKontrol();
    private Bot bot = new Bot();
    private Bundle extras;
    private boolean tekOyuncuMod;
    private int zorluk;
    Intent intent2;
    private int renkYesil = Color.rgb(0, 214, 21),
                renkKirmizi = Color.rgb(255, 0, 0);

    /* Zorluk Ayarları
     * 0 -> kolay
     * 1 -> normal
     * 2 -> zor
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oyun_ekrani);

        // bazı değerlerin eşitlenmesi
        extras = getIntent().getExtras(); // intent ile veri aktarımı
        intent2 = new Intent(getApplicationContext(), OyunEkrani.class);

        // oyun ekranındaki ayarlamaları yapar
        elemanAyarOyun();


        try {
            if ((extras.getString("ayar").charAt(0) + "").equals("")) { // zorluk bir değere eşit değilse zorluğu 0 yap
                zorluk = 0;
                Log.e("UYARI", "'extras.getString(\"ayar\").charAt(0) + \"\" == null' çalıştı");
            }
            else
                zorluk = Integer.parseInt(extras.getString("ayar").charAt(0) + "");

            // tekOyuncuMod değerini ayarlar
            if (extras.getString("ayar").charAt(2) == '1')
                tekOyuncuMod = true;
            else
                tekOyuncuMod = false;

        } catch (Exception e) {}

        // uygulama çalışırken bize bilgiler verir
        Log.e("UYARI", zorluk + " - " + tekOyuncuMod);
    }

    // --------------------------------------------------------------------------------------------- tıklanma olayları
    @Override
    public void onClick(View v) {
        // oyun ekranındaki tıklamaları çalıştırır
        if (v.getId() == btnGeri.getId()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (v.getId() == btnTekrar.getId()) {
            intent2.putExtra("ayar", zorluk + " " + extras.getString("ayar").charAt(2));
            startActivity(intent2);
            finish();
        } else
            for (int i = 0; i < btnTablo.length; i++) {
                for (int j = 0; j < btnTablo.length; j++) {
                    if (tekOyuncuMod && v.getId() == btnTablo[i][j].getId()) { // tek oyuncu ise
                        tekOyuncu(i, j);
                    } else if ((! tekOyuncuMod) && v.getId() == btnTablo[i][j].getId()) { // iki oyunculu ise
                        ikiOyuncu(i, j);
                    }
                }
            }
    }

    // --------------------------------------------------------------------------------------------- tek oyuncu fonksiyounu
    private void tekOyuncu(int _satir, int _sutun) {
        if ((! berabere) && (! bk.bitisKontrol(tablo))) { // Sıra oyuncuda
            if (tabloClass.tabloYerlestir(btnTablo, btnTablo[_satir][_sutun], tablo, 'X')) {

                // tur sayısını 1 arttırma
                turSayisi++;

                if (bk.bitisKontrol(tablo)) { // X kazandı ise
                    Toast.makeText(getApplicationContext(), R.string.kazandiniz, Toast.LENGTH_LONG).show();
                    btnTekrar.setOnClickListener(this);
                    btnTekrar.setVisibility(View.VISIBLE);
                    btnTablo[bk.getBolge1()[0]][bk.getBolge1()[1]].setTextColor(renkYesil);
                    btnTablo[bk.getBolge2()[0]][bk.getBolge2()[1]].setTextColor(renkYesil);
                    btnTablo[bk.getBolge3()[0]][bk.getBolge3()[1]].setTextColor(renkYesil);
                } else if (tablo.length + 2 <= turSayisi) { // berabere olup olmadığını kontrol eder
                    Toast.makeText(getApplicationContext(), R.string.berabere, Toast.LENGTH_LONG).show();
                    btnTekrar.setOnClickListener(this);
                    btnTekrar.setVisibility(View.VISIBLE);
                } else if (! berabere && ! bk.bitisKontrol(tablo)) { // botun sırası

                    // botun hamlesi
                    switch (zorluk) {
                        case 0:
                            bot.botKolay(btnTablo, tablo);
                            break;
                        case 1:
                            bot.botNormal(btnTablo, tablo);
                            break;
                        case 2:
                            bot.botZor(btnTablo, tablo);
                            break;
                    }

                    if (bk.bitisKontrol(tablo)) { // Bot kazandı ise
                        Toast.makeText(getApplicationContext(), R.string.kaybettiniz, Toast.LENGTH_LONG).show();
                        btnTekrar.setOnClickListener(this);
                        btnTekrar.setVisibility(View.VISIBLE);

                        btnTablo[bk.getBolge1()[0]][bk.getBolge1()[1]].setTextColor(renkKirmizi);
                        btnTablo[bk.getBolge2()[0]][bk.getBolge2()[1]].setTextColor(renkKirmizi);
                        btnTablo[bk.getBolge3()[0]][bk.getBolge3()[1]].setTextColor(renkKirmizi);
                    }
                }
            }
        }
    }

    // --------------------------------------------------------------------------------------------- iki oyuncu fonksiyounu
    private void ikiOyuncu(int _satir, int _sutun) {
        if (turSayisi % 2 == 0 && (! berabere) && (! bk.bitisKontrol(tablo))) { // Sıra x de
            if (tabloClass.tabloYerlestir(btnTablo, btnTablo[_satir][_sutun], tablo, xo[turSayisi % 2])) { // tıklanmış bir bölgeye tıklşayınca turSayisi artmasın diye

                // tur sayısını 1 arttırma
                turSayisi++;

                if (bk.bitisKontrol(tablo)) { // X kazandı ise
                    Toast.makeText(getApplicationContext(), R.string.kazanan_x, Toast.LENGTH_LONG).show();
                    btnTekrar.setOnClickListener(this);
                    btnTekrar.setVisibility(View.VISIBLE);
                    btnTablo[bk.getBolge1()[0]][bk.getBolge1()[1]].setTextColor(renkYesil);
                    btnTablo[bk.getBolge2()[0]][bk.getBolge2()[1]].setTextColor(renkYesil);
                    btnTablo[bk.getBolge3()[0]][bk.getBolge3()[1]].setTextColor(renkYesil);
                } else if (tablo.length * tablo.length == turSayisi) { // berabere olup olmadığını kontrol eder
                    Toast.makeText(getApplicationContext(), R.string.berabere, Toast.LENGTH_LONG).show();
                    btnTekrar.setOnClickListener(this);
                    btnTekrar.setVisibility(View.VISIBLE);
                }
            }
        } else if ((! berabere) && (! bk.bitisKontrol(tablo))) { // Sıra O da
            if (tabloClass.tabloYerlestir(btnTablo, btnTablo[_satir][_sutun], tablo, xo[turSayisi % 2])) { // tıklanmış bir bölgeye tılşayınca turSayisi artmasın diye
                if (bk.bitisKontrol(tablo)) { // O kazandı ise
                    Toast.makeText(getApplicationContext(), R.string.kazanan_o, Toast.LENGTH_LONG).show();
                    btnTekrar.setOnClickListener(this);
                    btnTekrar.setVisibility(View.VISIBLE);
                    btnTablo[bk.getBolge1()[0]][bk.getBolge1()[1]].setTextColor(renkYesil);
                    btnTablo[bk.getBolge2()[0]][bk.getBolge2()[1]].setTextColor(renkYesil);
                    btnTablo[bk.getBolge3()[0]][bk.getBolge3()[1]].setTextColor(renkYesil);
                    return;
                }
                turSayisi++;
            }
        }
    }
}
