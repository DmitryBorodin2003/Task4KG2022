package ru.vsu.cs.kg2022.borodin_d_n.task4.math;

import java.util.Vector;

public class Vector4 {
    private float[] values;
    private static final int SIZE = 4;

    public Vector4(float x, float y, float z, float w) {
        this.values = new float[]{x, y, z, w};
    }

    public Vector4(float x, float y, float z) {
        this(x, y, z, 0);
    }

    public Vector4(Vector3 vector, float w) {
        this(vector.getX(), vector.getY(), vector.getZ(), w);
    }

    private Vector4(float[] arr){
        this.values = arr;
    }

    public Vector4(Vector3 vector) {
        this(vector.getX(), vector.getY(), vector.getZ(), 0);
    }

    public static Vector4 zero(){
        return new Vector4(new float[4]);
    }

    public Vector4 mul(float number){
        float[] result = new float[4];
        for (int i = 0; i < SIZE; i++) {
            result[i] = this.at(i) * number;
        }
        return new Vector4(result);
    }

    public Vector4 add(Vector4 other){
        float[] result = new float[4];
        for (int i = 0; i < SIZE; i++) {
            result[i] = this.at(i) + other.at(i);
        }
        return new Vector4(result);
    }


    private static final float EPS = 1e-12f;

    public Vector4 normalized(){
        if (Math.abs(getW()) <= EPS){
            return new Vector4(getX(), getY(), getZ(), 0);
        } else {
            return new Vector4(getX()/getW(), getY()/getW(), getZ()/getW(), 1f);
        }
    }

    public Vector3 asVector3(){
        Vector4 result = (Math.abs(getW()) < EPS) ? this : normalized();
        return new Vector3(result.getX(), result.getY(), result.getZ());
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

    public float getW() {
        return values[3];
    }

    public float at(int index){
        return values[index];
    }
}
