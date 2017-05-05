package com.sth99.pxteditor.color;

public class Color {
    private SingleColor r, g, b, a;

    public Color(int r, int g, int b, int a) {
        this(new SingleColor((short) r), new SingleColor((short) g), new SingleColor((short) b), new SingleColor((short) a));
    }

    public Color() {
        this(SingleColor.BYTE_MAX, SingleColor.BYTE_MAX, SingleColor.BYTE_MAX, SingleColor.BYTE_MAX);
    }

    public Color(SingleColor r, SingleColor g, SingleColor b, SingleColor a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(short r, short g, short b) {
        this(new SingleColor(r), new SingleColor(g), new SingleColor(b), new SingleColor(SingleColor.BYTE_MAX));
    }

    public SingleColor getR() {

        return r;
    }

    public void setR(SingleColor r) {
        this.r = r;
    }

    public SingleColor getG() {
        return g;
    }

    public void setG(SingleColor g) {
        this.g = g;
    }

    public SingleColor getB() {
        return b;
    }

    public void setB(SingleColor b) {
        this.b = b;
    }

    public SingleColor getA() {
        return a;
    }

    public void setA(SingleColor a) {
        this.a = a;
    }

    public SingleColor[] getColors() {
        return new SingleColor[]{r, g, b, a};
    }

    public java.awt.Color toAwtColor() {
        return new java.awt.Color(r.getValue(), g.getValue(), b.getValue(), a.getValue());
    }

    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
