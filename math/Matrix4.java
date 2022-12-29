package ru.vsu.cs.kg2022.borodin_d_n.task4.math;

public class Matrix4 {
    private float[] matrix;
    private static final int SIZE = 4;

    public Matrix4(float[][] m){
        matrix = new float[16];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i*SIZE + j] = m[i][j];
            }
        }
    }

    private Matrix4(float[] arr){
        matrix = arr;
    }

    public float getAt(int row, int col){
        return matrix[row * SIZE + col];
    }

    public void setAt(int row, int col, float value){
        matrix[row * SIZE + col] = value;
    }

    public static Matrix4 zero(){
        return new Matrix4(new float[16]);
    }

    public static Matrix4 one(){
        Matrix4 m = zero();
        for (int i = 0; i < SIZE; i++) {
            m.setAt(i, i, 1);
        }
        return m;
    }

    public Matrix4 mul(float number){
        Matrix4 m = zero();
        for (int i = 0; i < this.matrix.length; i++) {
            m.matrix[i] = this.matrix[i] * number;
        }
        return m;
    }

    public Vector4 mul(Vector4 vector){
        float[] result = new float[4];
        for (int i = 0; i < SIZE; i++) {
            float sum = 0;
            for (int j = 0; j < SIZE; j++) {
                sum += this.getAt(i, j) * vector.at(j);
            }
            result[i] = sum;
        }
        return new Vector4(result[0], result[1], result[2], result[3]);
    }

    public Matrix4 mul(Matrix4 other){
        Matrix4 answer = Matrix4.zero();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                float sum = 0;
                for (int k = 0; k < SIZE; k++) {
                    sum += this.getAt(i, k) * other.getAt(k, j);
                }
                answer.setAt(i, j, sum);
            }
        }
        return answer;
    }

    public Matrix4 add(Matrix4 other){
        Matrix4 answer = Matrix4.zero();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                answer.setAt(i, j, this.getAt(i, j) + other.getAt(i, j));
            }
        }
        return answer;
    }
}
