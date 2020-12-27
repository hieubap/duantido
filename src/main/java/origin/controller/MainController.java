package origin.controller;

import objectgame.Ball;
import objectgame.ObjectGame;
import objectgame.Player;
import objectgame.enemy.Enemy;
import origin.Camera;
import primary.EnvironmentConfig;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainController extends JPanel {
    private Camera camera;
    private EditMapTool map;

    private Ball[] ball;
    private JFrame frame;
    private List<ObjectGame> objectGameList;

    public MainController() {
        map = new EditMapTool();

        Player player = new Player(EnvironmentConfig.WIDTH/2-100, EnvironmentConfig.HEIGHT/2,map);
        camera = player.getCamera();
        map.setCamera(camera);

        objectGameList = new ArrayList<>();
        objectGameList.add(player);
        objectGameList.add(new Enemy(100,300,camera, map));

        this.setFocusable(true);
        addKeyListener(camera);
        addKeyListener(map);
        addKeyListener(player);
        addMouseListener(map);

        // set frame
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

        map.drawMap(g);

        for (ObjectGame objectGame : objectGameList) {
            objectGame.draw(g);
        }
    }

    public void update() {
        camera.update();
        for (ObjectGame objectGame : objectGameList) {
            objectGame.update();
        }
    }
}
