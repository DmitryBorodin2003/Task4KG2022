package ru.vsu.cs.kg2022.borodin_d_n.task4.third;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Matrix4;
import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector4;

public class Camera implements ICamera{

    private Matrix4 translate, scale, rotate, project;

    public Camera() {
        translate = Matrix4.one();
        scale = Matrix4.one();
        rotate = Matrix4.one();
        project = Matrix4.one();
    }

    public Vector3 w2s(Vector3 v) {
        return project.mul(translate.mul(rotate.mul(scale.mul(new Vector4(v, 1))))).asVector3();
    }

    public Matrix4 getTranslate() {
        return translate;
    }

    public void setTranslate(Matrix4 translate) {
        this.translate = translate;
    }

    public void modifyTranslate(Matrix4 delta) {
        this.translate = delta.mul(this.translate);
    }

    public Matrix4 getScale() {
        return scale;
    }

    public void setScale(Matrix4 scale) {
        this.scale = scale;
    }
    public void modifyScale(Matrix4 delta) {
        this.scale = delta.mul(this.scale);
    }

    public Matrix4 getRotate() {
        return rotate;
    }

    public void setRotate(Matrix4 rotate) {
        this.rotate = rotate;
    }
    public void modifyRotate(Matrix4 delta) {
        this.rotate = delta.mul(this.rotate);
    }

    public Matrix4 getProject() {
        return project;
    }

    public void setProject(Matrix4 project) {
        this.project = project;
    }

    public void modifyProject(Matrix4 delta) {
        this.project = delta.mul(this.project);
    }
}
