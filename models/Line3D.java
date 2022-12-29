package ru.vsu.cs.kg2022.borodin_d_n.task4.models;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.IModel;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.util.Arrays;
import java.util.List;

public class Line3D implements IModel {
    private Vector3 p1, p2;

    public Line3D(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Vector3 getP1() {
        return p1;
    }

    public void setP1(Vector3 p1) {
        this.p1 = p1;
    }

    public Vector3 getP2() {
        return p2;
    }

    public void setP2(Vector3 p2) {
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return Arrays.asList(new PolyLine3D(Arrays.asList(new Vector3(p1.getX(), p1.getY(), p1.getZ()), new Vector3(p2.getX(), p2.getY(), p2.getZ())), false));
    }
}
