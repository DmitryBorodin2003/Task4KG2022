package ru.vsu.cs.kg2022.borodin_d_n.task4.models;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.IModel;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.util.ArrayList;
import java.util.List;

public class CylinderWithHole implements IModel {
    public final float r;
    public final float rHole;
    public final float h;

    public CylinderWithHole(float r, float rHole, float h) {
        this.r = r;
        this.rHole = rHole;
        this.h = h;
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> list = new ArrayList<>();

        int points_num = 50;
        List<Vector3> innerLow = getCirclePoints(points_num, rHole, 0, 0);
        List<Vector3> innerHigh = getCirclePoints(points_num, rHole, h, 0);

        List<Vector3> outerLow = getCirclePoints(points_num, r, 0, 0);
        List<Vector3> outerHigh = getCirclePoints(points_num, r, h, 0);


        for (int i = 0; i < points_num; i++) {
            int nextInd = i + 1 < points_num ? i + 1 : 0;

            List<Vector3> innerWalls = new ArrayList<>();
            innerWalls.add(innerLow.get(i));
            innerWalls.add(innerLow.get(nextInd));
            innerWalls.add(innerHigh.get(nextInd));
            innerWalls.add(innerHigh.get(i));

            list.add(new PolyLine3D(innerWalls, true));

            List<Vector3> outerWalls = new ArrayList<>();
            outerWalls.add(outerLow.get(i));
            outerWalls.add(outerLow.get(nextInd));
            outerWalls.add(outerHigh.get(nextInd));
            outerWalls.add(outerHigh.get(i));

            list.add(new PolyLine3D(outerWalls, true));

            List<Vector3> downFloor = new ArrayList<>();
            downFloor.add(innerLow.get(i));
            downFloor.add(outerLow.get(i));
            downFloor.add(outerLow.get(nextInd));
            downFloor.add(innerLow.get(nextInd));
            list.add(new PolyLine3D(downFloor, true));

            List<Vector3> upFloor = new ArrayList<>();
            upFloor.add(innerHigh.get(i));
            upFloor.add(outerHigh.get(i));
            upFloor.add(outerHigh.get(nextInd));
            upFloor.add(innerHigh.get(nextInd));
            list.add(new PolyLine3D(upFloor, true));
        }

        return list;
    }

    public static List<Vector3> getCirclePoints(int points_num, float radius, float high, float offsetAngle){
        List<Vector3> list = new ArrayList<>();
        for (int i = 0; i < points_num; i++) {
            double angle = offsetAngle + 2 * Math.PI * i / points_num;
            list.add(new Vector3((float) Math.cos(angle)*radius, (float) Math.sin(angle)*radius, high));
        }
        return list;
    }
}
