package ru.vsu.cs.kg2022.borodin_d_n.task4.screen;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;

public class ScreenConverter {
    private double cornerX, cornerY, realW, realH;
    private int screenW, screenH;

    public ScreenPoint r2s(Vector3 v){
        int x = (int)((v.getX() - cornerX) * screenW / realW);
        int y = (int)((cornerY - v.getY()) * screenH / realH);
        return new ScreenPoint(x, y);
    }

    public Vector3 s2r(ScreenPoint p, float z){
        double x = cornerX + p.getX() * realW / screenW;
        double y = cornerY - p.getY() * realH / screenH;
        return new Vector3((float) x, (float) y, z);
    }

    public Vector3 s2r(ScreenPoint p){
        return s2r(p, 0);
    }

    public ScreenConverter(double cornerX, double cornerY, double realW, double realH, int screenW, int screenH) {
        this.cornerX = cornerX;
        this.cornerY = cornerY;
        this.realW = realW;
        this.realH = realH;
        this.screenW = screenW;
        this.screenH = screenH;
    }

    public double getCornerX() {
        return cornerX;
    }

    public void setCornerX(double cornerX) {
        this.cornerX = cornerX;
    }

    public double getCornerY() {
        return cornerY;
    }

    public void setCornerY(double cornerY) {
        this.cornerY = cornerY;
    }

    public double getRealW() {
        return realW;
    }

    public void setRealW(double realW) {
        this.realW = realW;
    }

    public double getRealH() {
        return realH;
    }

    public void setRealH(double realH) {
        this.realH = realH;
    }

    public int getScreenW() {
        return screenW;
    }

    public void setScreenW(int screenW) {
        this.screenW = screenW;
    }

    public int getScreenH() {
        return screenH;
    }

    public void setScreenH(int screenH) {
        this.screenH = screenH;
    }
}
