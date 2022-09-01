package com.example.tictactoe;

public class BitisKontrol extends Eleman {
    private int[] bolge1, bolge2, bolge3;

    public boolean bitisKontrol(char[][] _tablo) {
        for (int _xo : xo) {
            for (int i = 0; i < _tablo.length; i++) {
                for (int j = 0; j < _tablo.length; j++) {
                    // dikey kontrol
                    if (i + 2 < _tablo.length && _tablo[i][j] == _xo && _tablo[i + 1][j] == _xo && _tablo[i + 2][j] == _xo) {
                        bolge1 = new int[] {i, j};
                        bolge2 = new int[] {i + 1, j};
                        bolge3 = new int[] {i + 2, j};
                        return true;
                    }

                    // yatay kontrol
                    else if (j + 2 < _tablo.length && _tablo[i][j] == _xo && _tablo[i][j + 1] == _xo && _tablo[i][j + 2] == _xo) {
                        bolge1 = new int[] {i, j};
                        bolge2 = new int[] {i, j + 1};
                        bolge3 = new int[] {i, j + 2};
                        return true;
                    }

                    // çapraz kontrol
                    /* şeklindeki kontrol
                        *
                          *
                            *
                     */
                    else if (i + 2 < _tablo.length && j + 2 < _tablo.length && _tablo[i][j] == _xo && _tablo[i + 1][j + 1] == _xo && _tablo[i + 2][j + 2] == _xo) {
                        bolge1 = new int[] {i, j};
                        bolge2 = new int[] {i + 1, j + 1};
                        bolge3 = new int[] {i + 2, j + 2};
                        return true;
                    }

                    /* şeklindeki kontrol
                            *
                          *
                        *
                     */
                    else if (i + 2 < _tablo.length && j + 2 < _tablo.length && _tablo[i][j + 2] == _xo && _tablo[i + 1][j + 1] == _xo && _tablo[i + 2][j] == _xo) {
                        bolge1 = new int[] {i, j + 2};
                        bolge2 = new int[] {i + 1, j + 1};
                        bolge3 = new int[] {i + 2, j};
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // set ve get fonksiyonları
    public int[] getBolge1() {
        return bolge1;
    }
    public int[] getBolge2() {
        return bolge2;
    }
    public int[] getBolge3() {
        return bolge3;
    }
}
