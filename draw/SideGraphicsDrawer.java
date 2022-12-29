package ru.vsu.cs.kg2022.borodin_d_n.task4.draw;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenConverter;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenCoordinates;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenPoint;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.awt.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SideGraphicsDrawer extends AbstractGraphicsDrawer{
    public SideGraphicsDrawer(Graphics2D gr, ScreenConverter sc) {
        super(gr, sc);
    }

    @Override
    protected void drawOne(PolyLine3D pl) {
        List<ScreenPoint> spList = new LinkedList<>();
        for (Vector3 v: pl.getPoints()){
            spList.add(getScreenConverter().r2s(v));
        }
        if (spList.size() == 1){
            getGraphics().fillRect(spList.get(0).getX(), spList.get(0).getY(), 1, 1);
        } else if (spList.size() == 2) {
            getGraphics().drawLine(spList.get(0).getX(), spList.get(0).getY(), spList.get(1).getX(), spList.get(1).getY());
        } else if (spList.size() > 2) {
            ScreenCoordinates coords = new ScreenCoordinates(spList);
            if (pl.isClosed()){
                getGraphics().fillPolygon(coords.getXx(), coords.getYy(), coords.getLength());
            } else {
                getGraphics().drawPolyline(coords.getXx(), coords.getYy(), coords.getLength());
            }
        }
    }

    @Override
    protected Comparator<PolyLine3D> getComparator() {
        return new Comparator<PolyLine3D>() {
            private static final float EPS = 1e-8f;
            @Override
            public int compare(PolyLine3D a, PolyLine3D b) {
                float d = b.avgZ() - a.avgZ();
                if (Math.abs(d) < EPS){
                    return 0;
                }
                return d < 0 ? -1 : 1;
            }
        };
    }

    @Override
    protected IFilter<PolyLine3D> getFilter() {
        return new IFilter<PolyLine3D>() {
            @Override
            public boolean accept(PolyLine3D val) {
                return true;
            }
        };
    }
}

