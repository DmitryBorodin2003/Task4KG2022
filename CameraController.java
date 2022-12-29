package ru.vsu.cs.kg2022.borodin_d_n.task4;

import ru.vsu.cs.kg2022.borodin_d_n.task4.math.MatricesFactory;
import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Matrix4;
import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector3;
import ru.vsu.cs.kg2022.borodin_d_n.task4.math.Vector4;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenConverter;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenCoordinates;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenPoint;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.Camera;

import javax.swing.*;
import java.awt.event.*;

public class CameraController implements MouseListener, MouseMotionListener, MouseWheelListener {

    public interface RepaintRequiredListener{
        void shouldRepaint();
    }

    private RepaintRequiredListener listener = null;

    public void setRepaintRequiredListener(RepaintRequiredListener l){
        listener = l;
    }
    protected void onRepaint(){
        if (listener != null){
            listener.shouldRepaint();
        }
    }
    private Camera cam;
    private ScreenConverter sc;

    public CameraController(Camera cam, ScreenConverter sc) {
        this.cam = cam;
        this.sc = sc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    ScreenPoint lastPoint = null;
    boolean left = false, right = false, middle = false;
    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = new ScreenPoint(e.getX(), e.getY());
        if (SwingUtilities.isLeftMouseButton(e)){
            left = true;
        }
        if (SwingUtilities.isRightMouseButton(e)){
            right = true;
        }
        if (SwingUtilities.isMiddleMouseButton(e)){
            middle = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastPoint = null;
        if (SwingUtilities.isLeftMouseButton(e)){
            left = false;
        }
        if (SwingUtilities.isRightMouseButton(e)){
            right = false;
        }
        if (SwingUtilities.isMiddleMouseButton(e)){
            middle = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint current = new ScreenPoint(e.getX(), e.getY());
        int dx = current.getX() - lastPoint.getX();
        int dy = current.getY() - lastPoint.getY();
        if (left){
            double da = dx * Math.PI / 180;
            double db = dy * Math.PI / 180;
            Matrix4 delta = MatricesFactory.rotate(da, MatricesFactory.Axis.Y).mul(MatricesFactory.rotate(db, MatricesFactory.Axis.X));
            cam.modifyRotate(delta);
        }
        if (right){
            Vector4 z = new Vector4(sc.s2r(new ScreenPoint(0, 0), 0));
            Vector4 d = new Vector4(sc.s2r(new ScreenPoint(dx, dy), 0));
            Vector3 actualDelta = d.add(z.mul(-1f)).asVector3();
            cam.modifyTranslate(MatricesFactory.translate(actualDelta));
        }
        if (middle){
            Vector4 z = new Vector4(sc.s2r(new ScreenPoint(0, 0), 0));
            Vector4 d = new Vector4(sc.s2r(new ScreenPoint(dx, dy), 0));
            Vector3 actualDelta = d.add(z.mul(-1f)).asVector3();
            float dz = actualDelta.length();
            if (dy < 0){
                dz = -dz;
            }
            cam.modifyTranslate(MatricesFactory.translate(0, 0, dz));
        }

        lastPoint = current;
        onRepaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int delta = e.getWheelRotation();
        if (e.isControlDown()){
            cam.modifyProject(MatricesFactory.centralProjection(delta*5f, MatricesFactory.Axis.Z));
        } else {
            float factor = 1;
            float scale = delta < 0 ? 1.1f : 0.9f;
            int counter = delta < 0 ? -delta : delta;
            while (counter-- > 0){
                factor *= scale;
            }
            cam.modifyScale(MatricesFactory.scale(factor));
        }
        onRepaint();
    }
}
