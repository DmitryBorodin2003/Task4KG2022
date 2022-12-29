package ru.vsu.cs.kg2022.borodin_d_n.task4.math;


public class MatricesFactory {
    public static Matrix4 scale(float x, float y, float z){
        Matrix4 m = Matrix4.one();
        m.setAt(0, 0, x);
        m.setAt(1, 1, y);
        m.setAt(2, 2, z);
        return m;
    }

    public static Matrix4 scale(float s){
       return scale(s, s, s);
    }

    public static Matrix4 translate(Vector3 v){
        return translate(v.getX(), v.getY(), v.getZ());
    }
    public static Matrix4 translate(float x, float y, float z){
        Matrix4 m = Matrix4.one();
        m.setAt(0, 3, x);
        m.setAt(1, 3, y);
        m.setAt(2, 3, z);
        return m;
    }

    public static Matrix4 rotate(double alpha, int axisIndex){
        Matrix4 m = Matrix4.one();
        if (axisIndex < 0 || axisIndex > 2){
            return m;
        }
        int a1 = (axisIndex + 1) % 3;
        int a2 = (axisIndex + 2) % 3;

        m.setAt(a1, a1, (float) Math.cos(alpha));
        m.setAt(a1, a2, (float) Math.sin(alpha));
        m.setAt(a2, a1, (float) -Math.sin(alpha));
        m.setAt(a2, a2, (float) Math.cos(alpha));

        return m;
    }

    public static enum Axis {X, Y, Z};

    public static Matrix4 rotate(double alpha, Axis axis){
        return rotate(alpha, axis == Axis.X ? 0 : axis == Axis.Y ? 1 : 2);
    }


    public static Matrix4 centralProjection(float point, int axisIndex){
        Matrix4 m = Matrix4.one();
        if (axisIndex < 0 || axisIndex > 2){
            return m;
        }
        m.setAt(3, axisIndex, 1/point);
        return m;
    }

    public static Matrix4 centralProjection(float point, Axis axis){
        return centralProjection(point, axis == Axis.X ? 0 : axis == Axis.Y ? 1 : 2);
    }

}
