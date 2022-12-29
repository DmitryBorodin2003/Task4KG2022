package ru.vsu.cs.kg2022.borodin_d_n.task4.models;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.IModel;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Parallelepiped implements IModel {
    private Vector3 LTF, RBN;

    public Parallelepiped(Vector3 LTF, Vector3 RBN) {
        this.LTF = LTF;
        this.RBN = RBN;
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> result = new LinkedList<>();
        result.add(new PolyLine3D(Arrays.asList(
                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ())), true));

        result.add(new PolyLine3D(Arrays.asList(
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ())), true));

        result.add(new PolyLine3D(Arrays.asList(
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ())), true));

        result.add(new PolyLine3D(Arrays.asList(
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())), true));

        result.add(new PolyLine3D(Arrays.asList(
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ())), true));

        result.add(new PolyLine3D(Arrays.asList(
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())), true));


        return result;
    }
}
