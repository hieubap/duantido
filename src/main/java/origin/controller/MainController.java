package origin.controller;

import map.Map;
import objectgame.Ball;
import map.EditMapTool;
import objectgame.ObjectGame;
import objectgame.Player;
import objectgame.enemy.Enemy;
import origin.Camera;
import origin.EnvironmentConfig;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainController extends JPanel {
    private Camera camera;
    Map map;

    private Ball[] ball;
    private JFrame frame;
    private List<ObjectGame> objectGameList;

    public MainController() {
        camera = new Camera(0, 0);
        map = new Map(camera);

        objectGameList = new ArrayList<>();
        Player player = new Player(100, 100,camera);
        objectGameList.add(player);
        objectGameList.add(new Enemy(100,100,camera));

        this.setFocusable(true);
        addKeyListener(camera);
        addKeyListener(player);

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
