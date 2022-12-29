package ru.vsu.cs.kg2022.borodin_d_n.task4;

import ru.vsu.cs.kg2022.borodin_d_n.task4.draw.EdgeGraphicsDrawer;
import ru.vsu.cs.kg2022.borodin_d_n.task4.draw.IDrawer;
import ru.vsu.cs.kg2022.borodin_d_n.task4.models.Wheel;
import ru.vsu.cs.kg2022.borodin_d_n.task4.screen.ScreenConverter;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.Camera;
import ru.vsu.cs.kg2022.borodin_d_n.task4.third.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements CameraController.RepaintRequiredListener {

    private ScreenConverter sc = new ScreenConverter(-1, 1, 2, 2, 800, 600);
    private Scene scene;
    private Camera cam;
    private CameraController cc;
    public Wheel wheel = new Wheel(0.2f, 0.1f, 0.2f, 1f, 1.1f, 100);

    public DrawPanel(){
        cam = new Camera();
        cc = new CameraController(cam, sc);

        this.addMouseListener(cc);
        this.addMouseMotionListener(cc);
        this.addMouseWheelListener(cc);
        cc.setRepaintRequiredListener(this);

        scene = new Scene(Color.white);
        //scene.getModels().add(new Line3D(new Vector3(0, 0, 0), new Vector3(1, 0, 0)));
        //scene.getModels().add(new Line3D(new Vector3(0, 0, 0), new Vector3(0, 1, 0)));
        //scene.getModels().add(new Line3D(new Vector3(0, 0, 0), new Vector3(0, 0, 1)));

        scene.getModels().add(wheel);
    }

    @Override
    public void paint(Graphics g) {
        scene.getModels().remove(scene.getModels().size() - 1);
        scene.getModels().add(wheel);

        sc.setScreenW(getWidth());
        sc.setScreenH(getHeight());

        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bi_g = bi.createGraphics();

        //EdgeGraphicsDrawer - не закрашивает фигуру
        //SideGraphicsDrawer - закрашивает фигуру
        IDrawer d = new EdgeGraphicsDrawer(bi_g, sc);
        scene.drawWorld(cam, d);
        bi_g.dispose();
        g.drawImage(bi, 0, 0, null);
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }

    public void     setWheel(Wheel w){
        wheel = w;
    }
}
