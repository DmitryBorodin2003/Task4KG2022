package ru.vsu.cs.kg2022.borodin_d_n.task4.models;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.IModel;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.PolyLine3D;

import java.util.ArrayList;
import java.util.List;

/* 19. (30) Написать программу построения зубчатого колеса со спицами.
Пользователь задаёт параметры зубчатого колеса: толщина, количество зубьев, все необходимые радиусы (их будет три). */

public class Wheel implements IModel {
    private final float rHole;
    private final float rInner;
    private final float rWheel;
    private final float rWhihTeeth;

    private final float outerIn;

    private final float depth;

    private final int teethAmount;
    public Wheel(float depth, float rHole, float rInner, float rWheel, float rWhihTeeth, int teethAmount) {
        this.rHole = rHole;
        this.rInner = rInner;
        this.rWheel = rWheel;
        this.rWhihTeeth = rWhihTeeth;
        this.outerIn = rWheel*0.7f;
        this.teethAmount = teethAmount;
        this.depth = depth;
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> list = new ArrayList<>();

        list.addAll(new CylinderWithHole(rHole, rInner, depth).getLines());

        list.addAll(new CylinderWithHole(outerIn, rWheel, depth).getLines());

        list.addAll(spitsaGenerate());

        list.addAll(teethGenerate(teethAmount));

        return list;
    }

    private List<PolyLine3D> teethGenerate(int teethAmount){
        List<PolyLine3D> list = new ArrayList<>();
        for (int i = 0; i < teethAmount; i++) {
            float angle = (float) (2f * Math.PI * i / teethAmount);
            float angle2 = angle + (float) (2f * Math.PI / teethAmount);
            float angleC = (angle + angle2) / 2f;

            Vector3 point1 = getCirclePoint(rWheel, angle);
            Vector3 pointC = getCirclePoint(rWhihTeeth, angleC);
            Vector3 point2 = getCirclePoint(rWheel, angle2);

            Vector3 point1High = new Vector3(point1.getX(), point1.getY(), depth);
            Vector3 pointCHigh = new Vector3(pointC.getX(), pointC.getY(), depth);
            Vector3 point2High = new Vector3(point2.getX(), point2.getY(), depth);

            List<Vector3> teethDown = new ArrayList<>();
            teethDown.add(point1);
            teethDown.add(pointC);
            teethDown.add(point2);

            list.add(new PolyLine3D(teethDown, true));

            List<Vector3> teethUp = new ArrayList<>();
            teethUp.add(point1High);
            teethUp.add(pointCHigh);
            teethUp.add(point2High);

            list.add(new PolyLine3D(teethUp, true));

            List<Vector3> wall1 = new ArrayList<>();
            wall1.add(point1);
            wall1.add(point1High);
            wall1.add(pointCHigh);
            wall1.add(pointC);

            list.add(new PolyLine3D(wall1, true));

            List<Vector3> wall2 = new ArrayList<>();
            wall2.add(point2);
            wall2.add(point2High);
            wall2.add(pointCHigh);
            wall2.add(pointC);

            list.add(new PolyLine3D(wall2, true));
        }

        return list;
    }

    private List<PolyLine3D> spitsaGenerate(){
        List<PolyLine3D> list = new ArrayList<>();
        float spitsaAngleSize = (float) Math.PI / 3;

        for (int i = 0; i < 5; i++) {
            float angle = (float) (2 * Math.PI * i / 5);
            float angle2 = angle + spitsaAngleSize;
            float angleC = (angle + angle2) / 2f;

            Vector3 p1Inner = getCirclePoint(rInner, angle);
            Vector3 p2Inner = getCirclePoint(rInner, angle2);
            Vector3 p1InnerHigh = new Vector3(p1Inner.getX(), p1Inner.getY(), depth);
            Vector3 p2InnerHigh = new Vector3(p2Inner.getX(), p2Inner.getY(), depth);
            float dX = p2Inner.getX() - p1Inner.getX();
            float dY = p2Inner.getY() - p1Inner.getY();

            Vector3 cOuter = getCirclePoint(outerIn, angleC);
            Vector3 p1Outer = new Vector3(cOuter.getX() - dX/2, cOuter.getY() - dY/2, 0);
            Vector3 p2Outer = new Vector3(cOuter.getX() + dX/2, cOuter.getY() + dY/2, 0);
            Vector3 p1OuterHigh = new Vector3(p1Outer.getX(), p1Outer.getY(), depth);
            Vector3 p2OuterHigh = new Vector3(p2Outer.getX(), p2Outer.getY(), depth);

            List<Vector3> rayDown = new ArrayList<>();
            rayDown.add(p1Inner);
            rayDown.add(p1Outer);
            rayDown.add(p2Outer);
            rayDown.add(p2Inner);

            list.add(new PolyLine3D(rayDown, true));

            List<Vector3> rayUp = new ArrayList<>();
            rayUp.add(p1InnerHigh);
            rayUp.add(p1OuterHigh);
            rayUp.add(p2OuterHigh);
            rayUp.add(p2InnerHigh);

            list.add(new PolyLine3D(rayUp, true));

            List<Vector3> wall1 = new ArrayList<>();
            wall1.add(p1Inner);
            wall1.add(p1InnerHigh);
            wall1.add(p1OuterHigh);
            wall1.add(p1Outer);

            list.add(new PolyLine3D(wall1, true));

            List<Vector3> wall2 = new ArrayList<>();
            wall2.add(p2Inner);
            wall2.add(p2InnerHigh);
            wall2.add(p2OuterHigh);
            wall2.add(p2Outer);

            list.add(new PolyLine3D(wall2, true));
        }

        return list;
    }

    private static Vector3 getCirclePoint(float radius, float angle){
        return new Vector3((float) Math.cos(angle)*radius, (float) Math.sin(angle)*radius, 0);
    }


}
