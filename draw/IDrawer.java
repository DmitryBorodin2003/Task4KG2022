package ru.vsu.cs.kg2022.borodin_d_n.task4.draw;

import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.awt.*;
import java.util.List;

public interface IDrawer {
    void draw(List<PolyLine3D> lines);
    void clear(Color c);
}
