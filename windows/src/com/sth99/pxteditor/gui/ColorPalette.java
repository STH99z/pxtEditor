package com.sth99.pxteditor.gui;

import com.sth99.pxteditor.color.Color;
import com.sth99.pxteditor.color.SingleColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPalette extends JPanel {
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    Color color;
    ColorSquare mainColorSquare;
    JPanel mainColorBack;
    ColorSquare[][] colorSquares;
    JTextArea[] colorTexts;
    JLabel[] colorBinarys;

    public static JFrame createFrame(Color color, int left, int top) {
        JFrame frame = new JFrame();
        frame.setContentPane(new ColorPalette(color));
        frame.setBounds(left, top, 400, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ((ColorPalette) frame.getContentPane()).refreshColors();
        return frame;
    }

    public ColorPalette() {
        this(new Color((short) 0, (short) 0, (short) 0));
    }

    public ColorPalette(Color color) {
        super();
        this.setLayout(null);
        this.color = color;
        mainColorSquare = new ColorSquare(SingleColor.BYTE_0, SingleColor.BYTE_0, color);
        mainColorSquare.disableClick = true;
        mainColorSquare.setBounds(1, 1, 80 - 2, 80 - 2);
        this.add(mainColorSquare);
        ColorSquareMousePressListener listener = new ColorSquareMousePressListener(this);
        colorSquares = new ColorSquare[4][8];
        for (short i = 0; i < 4; i++) {
            for (short j = 0; j < 8; j++) {
                colorSquares[i][j] = new ColorSquare(i, j, color);
                colorSquares[i][j].setBounds(221 - j * 20, i * 20 + 1, 18, 18);
                colorSquares[i][j].addClickListener(listener);
                this.add(colorSquares[i][j]);
            }
        }
        colorTexts = new JTextArea[4];
        for (int i = 0; i < 4; i++) {
            SingleColor singleColor1 = color.getColors()[i];
            colorTexts[i] = new JTextArea(singleColor1.toString());
            colorTexts[i].setBounds(241, i * 20 + 1, 48, 18);
            this.add(colorTexts[i]);
        }
        colorBinarys = new JLabel[4];
        String[] colorName = {"R ", "G ", "B ", "A "};
        for (int i = 0; i < 4; i++) {
            SingleColor singleColor1 = color.getColors()[i];
            colorBinarys[i] = new JLabel(colorName[i] + singleColor1.getBinary());
            colorBinarys[i].setBounds(291, i * 20, 78, 20);
            this.add(colorBinarys[i]);
        }
        mainColorBack = new MainColorBack();
        mainColorBack.setBounds(0, 0, 240, 80);
        this.add(mainColorBack);
    }

    public void refreshColors() {
        mainColorSquare.setColor(this.color);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                colorSquares[i][j].setColor(this.color);
            }
        }
        repaint();
    }

    public static void main(String[] args) {
        createFrame(new Color(0, 0, 0, 255), 200, 200);
    }

    class MainColorBack extends JPanel {
        public MainColorBack() {
        }

        public void paint(Graphics g) {
            super.paint(g);
            java.awt.Color color1, color2;
            color1 = java.awt.Color.GRAY;
            color2 = java.awt.Color.WHITE;
            for (int i = 0; i < this.getWidth() / 5; i++) {
                for (int j = 0; j < this.getHeight() / 5; j++) {
                    g.setColor((i + j) % 2 == 0 ? color1 : color2);
                    g.fillRect(i * 5, j * 5, 5, 5);
                }
            }
        }
    }
}

class ColorSquareMousePressListener extends MouseAdapter {
    private ColorPalette colorPalette;

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ColorSquare square = ((ColorSquare) e.getSource());
        if (square.disableClick)
            return;
        Color color1 = colorPalette.getColor();
        SingleColor singleColor = color1.getColors()[square.getColorIndex()];
        singleColor.reverseBinary(square.getDigit());
        String[] colorName = {"R ", "G ", "B ", "A "};
        for (int i = 0; i < 4; i++) {
            SingleColor singleColor1 = color1.getColors()[i];
            colorPalette.colorTexts[i].setText(singleColor1.toString());
            colorPalette.colorBinarys[i].setText(colorName[i] + singleColor1.getBinary());
        }
        colorPalette.refreshColors();
    }

    public ColorSquareMousePressListener(ColorPalette palette) {
        super();
        this.colorPalette = palette;
    }
}