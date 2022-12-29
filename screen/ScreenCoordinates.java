package ru.vsu.cs.kg2022.borodin_d_n.task4.screen;

import java.util.List;

public class ScreenCoordinates {
    private int[] xx, yy;
    public ScreenCoordinates(List<ScreenPoint> points){
        xx = new int[points.size()];
        yy = new int[points.size()];
        int i = 0;
        for (ScreenPoint sp: points){
            xx[i] = sp.getX();
            yy[i] = sp.getY();
            i++;
        }
    }

    public int[] getXx() {
        return xx;
    }

    public int[] getYy() {
        return yy;
    }

    public int getLength(){
        return xx.length;
    }
}
