package com.sth99.pxteditor.color;

import com.sth99.pxteditor.gui.ColorPalette;
import com.sth99.pxteditor.gui.ColorSquare;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SingleColor {
    public static short BYTE_MAX = 0xFF;
    public static int BYTE_MOD = 0x100;
    public static short BYTE_0 = 0x00;
    public static float FLOAT_MAX = 1.0f;
    public static int BYTE_DIGIT = 8;
    public static short BIN_0 = 0x01;
    public static short BIN_1 = 0x02;
    public static short BIN_2 = 0x04;
    public static short BIN_3 = 0x08;
    public static short BIN_4 = 0x10;
    public static short BIN_5 = 0x20;
    public static short BIN_6 = 0x40;
    public static short BIN_7 = 0x80;
    public static short BIN[] = {BIN_0, BIN_1, BIN_2, BIN_3, BIN_4, BIN_5, BIN_6, BIN_7};

    private short value;

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = ((short) (value % BYTE_MOD));
    }

    public String getBinary() {
        StringBuffer buffer = new StringBuffer("00000000");
        for (int i = 0; i < BYTE_DIGIT; i++) {
            buffer.setCharAt(7 - i, (this.value & BIN[i]) > 0 ? '1' : '0');
        }
        return buffer.toString();
    }

    public void setBinary(int pos, int value) {
        pos = pos & BYTE_MAX;
        value = value % 2;
        if ((this.value & BIN[pos]) > 0) {
            if (value == 0)
                this.value -= BIN[pos];
        } else {
            if (value == 1)
                this.value += BIN[pos];
        }
    }

    public void reverseBinary(int pos) {
        pos = pos & BYTE_MAX;
        if ((this.value & BIN[pos]) > 0) {
            this.value -= BIN[pos];
        } else {
            this.value += BIN[pos];
        }
    }

    public SingleColor() {
        this.value = BYTE_MAX;
    }

    public SingleColor(short value) {
        this.value = ((short) (value % BYTE_MOD));
    }

    public String toString() {
        return value + "";
    }
}