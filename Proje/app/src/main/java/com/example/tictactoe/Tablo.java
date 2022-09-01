package com.example.tictactoe;

import android.widget.Button;

public class Tablo extends Eleman {
    public boolean tabloYerlestir(Button[][] _buttonTablo, Button _b, char[][] _tablo, char _karakter) {
        for (int i = 0; i < _tablo.length; i++) {
            for (int j = 0; j < _tablo.length; j++) {
                if (_buttonTablo[i][j].getId() == _b.getId() && _tablo[i][j] == ' ') {
                    _tablo[i][j] = _karakter;
                    _b.setText(_karakter + "");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean tabloYerlestir(Button[][] _btnTablo, char[][] _tablo, char _karakter, int _satir, int _sutun) {
        if (_tablo[_satir][_sutun] == ' ') {
            _tablo[_satir][_sutun] = _karakter;
            _btnTablo[_satir][_sutun].setText(_karakter + "");
            return true;
        }

        return false;
    }
}
