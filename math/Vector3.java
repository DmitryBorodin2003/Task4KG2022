package ru.vsu.cs.kg2022.borodin_d_n.task4.math;

public class Vector3 {
    private float[] values;

    public Vector3(float x, float y, float z) {
        this.values = new float[]{x, y, z};
    }

    public float getX() {
        return values[0];
    }

    public float getY() {
        return values[1];
    }

    public float getZ() {
        return values[2];
    }

    public float at(int index){
        return values[index];
    }

    private static final float EPS = 1e-8f;
    public float length(){
        float len = getX()*getX() + getY()*getY() + getZ()*getZ();
        if (Math.abs(len) < EPS){
            return 0;
        }
        return (float) Math.sqrt(len);
    }

}
