package com.sth99.pxteditor.gui;

import com.sth99.pxteditor.color.Color;
import com.sth99.pxteditor.color.SingleColor;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorSquare extends JPanel {
    private short colorIndex;
    private short digit;
    private Color color;
    private static int DEFAULT_WIDTH = 20;
    private static int DEFAULT_HEIGHT = 20;
    public boolean disableClick = false;

    public boolean isDisableClick() {
        return disableClick;
    }

    public void setDisableClick(boolean disableClick) {
        this.disableClick = disableClick;
    }

    public short getColorIndex() {
        return colorIndex;
    }

    public void setColorIndex(short colorIndex) {
        this.colorIndex = colorIndex;
    }

    public ColorSquare(short colorIndex, short digit, final Color color) {
        super();
        this.colorIndex = colorIndex;
        this.digit = digit;
        this.color = color;
        this.setBackground(color.toAwtColor());
//        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void addClickListener(MouseAdapter adapter) {
        this.addMouseListener(adapter);
    }

    public short getDigit() {
        return digit;
    }

    public void setDigit(short digit) {
        this.digit = digit;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        if (disableClick) {
            this.setBackground(color.toAwtColor());
            return;
        }
        Color color1 = this.getColor();
        SingleColor singleColor = color1.getColors()[this.colorIndex];
        singleColor.reverseBinary(this.digit);
        this.setBackground(color1.toAwtColor());
        singleColor.reverseBinary(this.digit);
    }
}
