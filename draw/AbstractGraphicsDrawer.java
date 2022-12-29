package ru.vsu.cs.kg2022.borodin_d_n.task4.draw;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenConverter;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenCoordinates;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenPoint;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraphicsDrawer implements IDrawer{
    private Graphics2D gr;
    private ScreenConverter sc;

    public AbstractGraphicsDrawer(Graphics2D gr, ScreenConverter sc) {
        this.gr = gr;
        this.sc = sc;
    }

    public Graphics2D getGraphics() {
        return gr;
    }

    public ScreenConverter getScreenConverter() {
        return sc;
    }

    @Override
    public void draw(List<PolyLine3D> lines) {
        Color prev = getGraphics().getColor();
        //  getGraphics().setColor(new Color(255, 0, 0, 100));
        getGraphics().setColor(Color.black);

        List<PolyLine3D> accepted = new ArrayList<>();
        IFilter<PolyLine3D> f = getFilter();
        for (PolyLine3D pl: lines){
            if (f.accept(pl)){
                accepted.add(pl);
            }
        }
        accepted.sort(getComparator());

        for (PolyLine3D line: accepted){
            drawOne(line);
        }
        getGraphics().setColor(prev);
    }

    @Override
    public void clear(Color c) {
        Color prev = getGraphics().getColor();
        getGraphics().setColor(c);
        getGraphics().fillRect(0, 0, getScreenConverter().getScreenW(), getScreenConverter().getScreenH());
        getGraphics().setColor(prev);
    }

    protected abstract void drawOne(PolyLine3D pl);
    protected abstract Comparator<PolyLine3D> getComparator();
    protected abstract IFilter<PolyLine3D> getFilter();
}
