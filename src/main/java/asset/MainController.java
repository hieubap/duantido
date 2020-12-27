package asset;


import origin.Camera;
import origin.EnvironmentConfig;
import weapon.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController extends JPanel implements ActionListener {
    private int NUMBERBALL = 2;
//    private Camera camera;
    private JFrame frame;
    private NormalShot normalShot;
    private FireShot fireShot;
    private WaterShot waterShot;
    private EarthShot earthShot;
    private MetalShot metalShot;
    private WoodShot woodShot;


    public MainController() {
        normalShot = new NormalShot(EnvironmentConfig.WIDTH / 2, 100, DirectionWeapon.RIGHT,new Camera(0,0));
        fireShot = new FireShot(EnvironmentConfig.WIDTH / 2, 200, DirectionWeapon.LEFT);
        waterShot = new WaterShot(EnvironmentConfig.WIDTH / 2, 300, DirectionWeapon.RIGHT);
        earthShot = new EarthShot(EnvironmentConfig.WIDTH / 2, 400, DirectionWeapon.LEFT);
        metalShot = new MetalShot(EnvironmentConfig.WIDTH / 2, 500, DirectionWeapon.RIGHT);
        woodShot = new WoodShot(EnvironmentConfig.WIDTH / 2, 600, DirectionWeapon.LEFT);
//
//        camera = new Camera(0, 0);
//        this.setFocusable(true);
//        addKeyListener(camera);

        frame = new JFrame("T.A.N.K");

        frame.setSize(EnvironmentConfig.WIDTH, EnvironmentConfig.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(this);

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(Picture.ballImage, 0, 0, 1000, 800, null);
//        for (Ball b : ball) {
//            b.draw(g);
//        }
        fireShot.draw(g);
        normalShot.draw(g);
        waterShot.draw(g);
        metalShot.draw(g);
        woodShot.draw(g);
        earthShot.draw(g);
    }

    public void update() {
//        for (Ball b : ball) {
//            b.update();
//        }
        normalShot.update();
//        fireShot.update();
        waterShot.update();
        metalShot.update();
        woodShot.update();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
}
