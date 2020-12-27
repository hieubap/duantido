package asset;


import objectgame.Ball;
import primary.EnvironmentConfig;
import weapon.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController extends JPanel implements ActionListener {
    private int NUMBERBALL = 2;
//    private Camera camera;

    private Ball[] ball;
    private JFrame frame;
    private NormalShot normalShot;
    private FireShot fireShot;
    private WaterShot waterShot;
    private EarthShot earthShot;
    private MetalShot metalShot;
    private WoodShot woodShot;


    public MainController() {
        normalShot = new NormalShot(EnvironmentConfig.WIDTH / 2, 100, Direction.RIGHT);
        fireShot = new FireShot(EnvironmentConfig.WIDTH / 2, 200, Direction.LEFT);
        waterShot = new WaterShot(EnvironmentConfig.WIDTH / 2, 300, Direction.RIGHT);
        earthShot = new EarthShot(EnvironmentConfig.WIDTH / 2, 400, Direction.LEFT);
        metalShot = new MetalShot(EnvironmentConfig.WIDTH / 2, 500, Direction.RIGHT);
        woodShot = new WoodShot(EnvironmentConfig.WIDTH / 2, 600, Direction.LEFT);
//
//        camera = new Camera(0, 0);
//        this.setFocusable(true);
//        addKeyListener(camera);

        ball = new Ball[NUMBERBALL];

        for (int i = 0; i < NUMBERBALL; i++) {
            double x = Math.random() * (EnvironmentConfig.WIDTH - 100);
            double y = Math.random() * (EnvironmentConfig.HEIGHT - 100);
//            ball[i] = new Ball((float) x, (float) y, camera);
        }
        Timer timer = new Timer(40, this);
        timer.start();

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
