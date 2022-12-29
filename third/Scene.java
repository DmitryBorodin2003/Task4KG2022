package ru.vsu.cs.kg2022.borodin_d_n.task4.third;

import ru.vsu.cs.kg2022.borodin_d_n.task4.draw.IDrawer;
import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    private Color bgColor;

    public Scene(Color bgColor) {
        this.bgColor = bgColor;
    }

    private List<IModel> models = new ArrayList<>();
    public List<IModel> getModels(){
        return models;
    }

    public void drawWorld(ICamera cam, IDrawer d){
        List<PolyLine3D> allLines = new LinkedList<>();
        for (IModel m: models){
            for (PolyLine3D pl: m.getLines()){
                List<Vector3> nv = new LinkedList<>();
                for (Vector3 v: pl.getPoints()){
                    nv.add(cam.w2s(v));
                }
                allLines.add(new PolyLine3D(nv, pl.isClosed()));
            }
        }
        d.clear(bgColor);
        d.draw(allLines);
    }
}
