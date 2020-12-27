package origin.controller;

import enemy.Enemy;
import map.EditMapTool;
import objectgame.ObjectGame;
import origin.Camera;
import origin.EnvironmentConfig;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainController extends JPanel {
    private Camera camera;
    private EditMapTool map;
    private JFrame frame;
    private List<ObjectGame> objectGameList;

    public MainController() {
        map = new EditMapTool();

        initObjectGame();
        this.setFocusable(true);
        addKeyListener(camera);
        addKeyListener(map);
        addMouseListener(map);

        // set frame
        frame = new JFrame("T.A.N.K");
        frame.setSize(EnvironmentConfig.WIDTH, EnvironmentConfig.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
    }

    public void initObjectGame() {
        objectGameList = new ArrayList<>();

        Player player = new Player(EnvironmentConfig.WIDTH / 2 - 100, EnvironmentConfig.HEIGHT / 2, map,objectGameList);
        camera = player.getCamera();
        map.setCamera(camera);

        objectGameList.add(player);
        objectGameList.add(new Enemy(100, 300, camera, map));

        addKeyListener(player);
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

        for (int i=0;i<objectGameList.size();i++){
            objectGameList.get(i).update();
            if (objectGameList.get(i).isRemove())
            {
                System.out.println("remove " + objectGameList.size());
                objectGameList.remove(i--);
            }
        }
    }
}
