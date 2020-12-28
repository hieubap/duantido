package centre.controller;

import centre.Camera;
import centre.EnvironmentVariable;
import enemy.Enemy;
import map.EditMapTool;
import objectgame.Box;
import objectgame.Chest;
import objectgame.Gate;
import objectgame.ObjectGame;
import player.Player;
import weapon.Weapon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static centre.EnvironmentVariable.PIXEL;

public class MainController extends JPanel {
    private Camera camera;
    private EditMapTool map;
    private JFrame frame;
    public List<ObjectGame> objectGameList;
    private List<Enemy> enemyList;
    public List<Weapon> weaponList;

    public MainController() {
        map = new EditMapTool();

        initObjectGame();
        this.setFocusable(true);
        addKeyListener(camera);
        addKeyListener(map);
        addMouseListener(map);

        // set frame
        frame = new JFrame("T.A.N.K");
        frame.setSize(EnvironmentVariable.WIDTH, EnvironmentVariable.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
    }

    public void initObjectGame() {
        objectGameList = new ArrayList<>();

        Player player = new Player(EnvironmentVariable.WIDTH / 2 - 100, EnvironmentVariable.HEIGHT / 2, map, this);
        camera = player.getCamera();
        map.setCamera(camera);

        weaponList = new ArrayList<>();
        enemyList = new ArrayList<>();

        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));
        enemyList.add(new Enemy(100, 300, camera, map));

        objectGameList.add(new Box( 100, 100, PIXEL, PIXEL, 7, camera, 7));
        objectGameList.add(new Box( 300, 100, PIXEL, PIXEL, 7, camera, 18));
        objectGameList.add(new Box( 500, 100, PIXEL, PIXEL, 7, camera,3));
        objectGameList.add(new Box( 700, 300, PIXEL, PIXEL, 7, camera, 12));
        objectGameList.add(new Gate(64 / 2, 64 * 10, PIXEL * 3, PIXEL * 2, 5, camera, 10));
        objectGameList.add(new Gate( 64 * 10 + 32, 64 * 10, PIXEL * 3, PIXEL * 2, 5, camera, 10));
        objectGameList.add(new Chest( 64 *5, 64 * 15, PIXEL, PIXEL, 6, camera, 10));
        objectGameList.add(new Chest( 64 * 4, 64 * 15, PIXEL, PIXEL, 6, camera, 10));

        objectGameList.add(player);

        addKeyListener(player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(EnvironmentVariable.COLOR_BACKGROUND_BLACK);

        map.drawMap(g);

        for (ObjectGame objectGame : objectGameList) {
            objectGame.draw(g);
        }
        for (Enemy enemy : enemyList) {
            enemy.draw(g);
        }
        for (Weapon weapon : weaponList) {
            weapon.draw(g);
        }
    }

    public void update() {
        camera.update();
        for (int i = 0; i < objectGameList.size(); i++) {
            objectGameList.get(i).update();
        }
        for (Weapon weapon : weaponList) {
            weapon.update();
        }
        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        // collision
        for (int i = 0; i < weaponList.size(); i++) {
            for (int j = 0; j < enemyList.size(); j++) {
                Weapon w = weaponList.get(i);
                if (w.isCollection(enemyList.get(j))) {
                    enemyList.remove(j);
                    weaponList.remove(i);
                    break;
                }
            }
        }
    }

    public void addWeapon(Weapon weapon) {
        weaponList.add(weapon);
    }
}
