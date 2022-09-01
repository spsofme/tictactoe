package com.example.tictactoe;

import android.widget.Button;
import java.util.Random;

public class Bot extends Eleman {
    private Random rand = new Random();
    private Tablo t = new Tablo();
    private int satir, sutun;

    // --------------------------------------------------------------------------------------------- Kolay Bot : Rastegele oynar
    public void botKolay(Button[][] _btnTablo, char[][] _tablo) {
        do {
            satir = rand.nextInt(_tablo.length);
            sutun = rand.nextInt(_tablo.length);
        } while(! t.tabloYerlestir(_btnTablo, _tablo, 'O', satir, sutun));
    }

    // --------------------------------------------------------------------------------------------- Normal Bot : Kazanabiliyorsa direkt oraya oynar
    public void botNormal(Button[][] _btnTablo, char[][] _tablo) {
        for (int i = 0; i < _tablo.length; i++) {
            for (int j = 0; j < _tablo.length; j++) {

                // yatay hamle
                if (j + 2 < _tablo.length) {
                    if (_tablo[i][j] == 'O' && _tablo[i][j + 1] == 'O' && _tablo[i][j + 2] != 'X') { // o o -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 2);
                        return;
                    } else if (_tablo[i][j] == 'O' && _tablo[i][j + 2] == 'O' && _tablo[i][j + 1] != 'X') { // o - o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 1);
                        return;
                    } else if (_tablo[i][j + 1] == 'O' && _tablo[i][j + 2] == 'O' && _tablo[i][j] != 'X') { // - o o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);
                        return;
                    }
                }

                // dikey hamle
                if (i + 2 < _tablo.length) {
                    if (_tablo[i][j] == 'O' && _tablo[i + 1][j] == 'O' && _tablo[i + 2][j] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j);          // o
                        return;                                                                      // -
                    }
                    else if (_tablo[i][j] == 'O' && _tablo[i + 2][j] == 'O' && _tablo[i + 1][j] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j);               // -
                        return;                                                                           // o
                    }
                    else if (_tablo[i + 1][j] == 'O' && _tablo[i + 2][j] == 'O' && _tablo[i][j] != 'X') { // -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);                          // o
                        return;                                                                           // o
                    }
                }
                // çapraz hamle
                if (i + 2 < _tablo.length && j + 2 < _tablo.length) {
                    if (_tablo[i][j] == 'O' && _tablo[i + 1][j + 1] == 'O' && _tablo[i + 2][j + 2] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j + 2);       //   o
                        return;                                                                              //     -
                    }
                    else if (_tablo[i][j] == 'O' && _tablo[i + 2][j + 2] == 'O' && _tablo[i + 1][j + 1] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j + 1);            //   -
                        return;                                                                                   //     o
                    }
                    else if (_tablo[i + 1][j + 1] == 'O' && _tablo[i + 2][j + 2] == 'O' && _tablo[i][j] != 'X') { // -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);                                  //   o
                        return;                                                                                   //     o
                    }
                    else if (_tablo[i][j + 2] == 'O' && _tablo[i + 1][j + 1] == 'O' && _tablo[i + 2][j] != 'X') { //     o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j);                       //   o
                        return;                                                                                   // -
                    }
                    else if (_tablo[i][j + 2] == 'O' && _tablo[i + 2][j] == 'O' && _tablo[i + 1][j + 1] != 'X') { //     o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j + 1);            //   -
                        return;                                                                                   // o
                    }
                    else if (_tablo[i + 2][j] == 'O' && _tablo[i + 1][j + 1] == 'O' && _tablo[i][j + 2] != 'X') { //     -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 2);                      //   o
                        return;                                                                                  // o
                    }
                }
            }
        }

        // eğer kazanamıyorsa rastgele bir yere oynasın
        botKolay(_btnTablo, _tablo);
    }

    // --------------------------------------------------------------------------------------------- Zor Bot : Kazanabiliyorsa oraya oynar ama kazanamayıp oyuncunun
    //                                                                                                         kazanmasını engelleyebiliyorsa oraya oynar
    public void botZor(Button[][] _btnTablo, char[][] _tablo) {
        for (int i = 0; i < _tablo.length; i++) {
            for (int j = 0; j < _tablo.length; j++) {
                if (j + 2 < _tablo.length) {
                    // yatay bitirme
                    if (_tablo[i][j] == 'O' && _tablo[i][j + 1] == 'O' && _tablo[i][j + 2] != 'X') { // o o -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 2);
                        return;
                    } else if (_tablo[i][j] == 'O' && _tablo[i][j + 2] == 'O' && _tablo[i][j + 1] != 'X') { // o - o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 1);
                        return;
                    } else if (_tablo[i][j + 1] == 'O' && _tablo[i][j + 2] == 'O' && _tablo[i][j] != 'X') { // - o o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);
                        return;
                    }

                    // yatay engelleme
                    else if (_tablo[i][j] == 'X' && _tablo[i][j + 1] == 'X' && _tablo[i][j + 2] != 'O') { // x x -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 2);
                        return;
                    } else if (_tablo[i][j] == 'X' && _tablo[i][j + 2] == 'X' && _tablo[i][j + 1] != 'O') { // x - x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 1);
                        return;
                    } else if (_tablo[i][j + 1] == 'X' && _tablo[i][j + 2] == 'X' && _tablo[i][j] != 'O') { // - x x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);
                        return;
                    }
                }

                if (i + 2 < _tablo.length) {
                    // dikey bitirme
                    if (_tablo[i][j] == 'O' && _tablo[i + 1][j] == 'O' && _tablo[i + 2][j] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j);          // o
                        return;                                                                      // -
                    }
                    else if (_tablo[i][j] == 'O' && _tablo[i + 2][j] == 'O' && _tablo[i + 1][j] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j);               // -
                        return;                                                                           // o
                    }
                    else if (_tablo[i + 1][j] == 'O' && _tablo[i + 2][j] == 'O' && _tablo[i][j] != 'X') { // -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);                          // o
                        return;                                                                           // o
                    }

                    // dikey engelleme
                    else if (_tablo[i][j] == 'X' && _tablo[i + 1][j] == 'X' && _tablo[i + 2][j] != 'O') { // x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j);               // x
                        return;                                                                           // -
                    }
                    else if (_tablo[i][j] == 'X' && _tablo[i + 2][j] == 'X' && _tablo[i + 1][j] != 'O') { // x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j);               // -
                        return;                                                                           // x
                    }
                    else if (_tablo[i + 1][j] == 'X' && _tablo[i + 2][j] == 'x' && _tablo[i][j] != 'O') { // -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);                          // x
                        return;                                                                           // x
                    }
                }

                if (i + 2 < _tablo.length && j + 2 < _tablo.length) {
                    // çapraz bitirme
                    if (_tablo[i][j] == 'O' && _tablo[i + 1][j + 1] == 'O' && _tablo[i + 2][j + 2] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j + 2);       //   o
                        return;                                                                              //     -
                    }
                    else if (_tablo[i][j] == 'O' && _tablo[i + 2][j + 2] == 'O' && _tablo[i + 1][j + 1] != 'X') { // o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j + 1);            //   -
                        return;                                                                                   //     o
                    }
                    else if (_tablo[i + 1][j + 1] == 'O' && _tablo[i + 2][j + 2] == 'O' && _tablo[i][j] != 'X') { // -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);                                  //   o
                        return;                                                                                   //     o
                    }
                    else if (_tablo[i][j + 2] == 'O' && _tablo[i + 1][j + 1] == 'O' && _tablo[i + 2][j] != 'X') { //     o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j);                       //   o
                        return;                                                                                   // -
                    }
                    else if (_tablo[i][j + 2] == 'O' && _tablo[i + 2][j] == 'O' && _tablo[i + 1][j + 1] != 'X') { //     o
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j + 1);            //   -
                        return;                                                                                   // o
                    }
                    else if (_tablo[i + 2][j] == 'O' && _tablo[i + 1][j + 1] == 'O' && _tablo[i][j + 2] != 'X') { //     -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 2);                       //   o
                        return;                                                                                   // o
                    }

                    // çapraz engelleme
                    else if (_tablo[i][j] == 'X' && _tablo[i + 1][j + 1] == 'X' && _tablo[i + 2][j + 2] != 'O') { // x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j + 2);            //   x
                        return;                                                                                   //     -
                    }
                    else if (_tablo[i][j] == 'X' && _tablo[i + 2][j + 2] == 'X' && _tablo[i + 1][j + 1] != 'O') { // x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j + 1);            //   -
                        return;                                                                                   //     x
                    }
                    else if (_tablo[i + 1][j + 1] == 'X' && _tablo[i + 2][j + 2] == 'X' && _tablo[i][j] != 'O') { // -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j);                                  //   x
                        return;                                                                                   //     x
                    }
                    else if (_tablo[i][j + 2] == 'X' && _tablo[i + 1][j + 1] == 'X' && _tablo[i + 2][j] != 'O') { //     x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 2, j);                       //   x
                        return;                                                                                   // -
                    }
                    else if (_tablo[i][j + 2] == 'X' && _tablo[i + 2][j] == 'X' && _tablo[i + 1][j + 1] != 'O') { //     x
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i + 1, j + 1);            //   -
                        return;                                                                                   // x
                    }
                    else if (_tablo[i + 2][j] == 'X' && _tablo[i + 1][j + 1] == 'X' && _tablo[i][j + 2] != 'O') { //     -
                        t.tabloYerlestir(_btnTablo, _tablo, 'O', i, j + 2);                       //   x
                        return;                                                                                   // x
                    }
                }
            }
        }

        // eğer herhangi biryere oynayamadıysa rastgele oynasın
        botKolay(_btnTablo, _tablo);
    }
}
