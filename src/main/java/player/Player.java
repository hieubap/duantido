package player;

import map.Map;
import objectgame.ObjectGame;
import centre.Camera;
import centre.EnvironmentVariable;
import centre.ImageManager;
import centre.controller.MainController;
import weapon.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class Player extends KeyAdapter implements ObjectGame {
    public double speed = EnvironmentVariable.SPEED_PLAYER;
    public double positionX, positionY;
    public List<Weapon> objectGameList;

    private Map map;
    private Camera camera;
    public Direction direction;
    private TypeWeapon typeWeapon = TypeWeapon.NORMAL;
    private int typeWeaponNumber = 1;
    private MainController mainController;

    public Player(double positionX, double positionY, Camera camera, Map map) {
        this.positionX = positionX;
        this.positionY = positionY;
        direction = new Direction();
        this.camera = camera;
        this.map = map;
    }

    public Player(double positionX, double positionY, Map map, MainController mainController) {
        this.mainController = mainController;
        this.positionX = positionX;
        this.positionY = positionY;
        direction = new Direction();
        this.camera = new Camera(0, 0, this);
        this.map = map;
    }

    @Override
    public void update() {
        if (direction.up && map.canMove((int) positionX, (int) (positionY - speed))) positionY -= speed;
        if (direction.down && map.canMove((int) positionX, (int) (positionY + speed))) positionY += speed;
        if (direction.left && map.canMove((int) (positionX - speed), (int) (positionY))) positionX -= speed;
        if (direction.right && map.canMove((int) (positionX + speed), (int) (positionY))) positionX += speed;
    }

    public void createWeapon(DirectionWeapon directionWeapon) {
        switch (typeWeapon) {
            case NORMAL: {
                mainController.addWeapon(new NormalShot(positionX, positionY, directionWeapon, camera));
//                objectGameList.add(new NormalShot(positionX, positionY, directionWeapon, camera));
                break;
            }
            case METAL: {
                mainController.addWeapon(new MetalShot(positionX, positionY, directionWeapon, camera));
//                objectGameList.add(new MetalShot(positionX, positionY, directionWeapon, camera));
                break;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        switch (typeWeapon) {
            case NORMAL: {
                g.drawImage(ImageManager.normalShotImage, 0, EnvironmentVariable.HEIGHT - 100, 80, 80, null);
                break;
            }
            case METAL: {
                g.drawImage(ImageManager.metalShotImage, 0, EnvironmentVariable.HEIGHT - 100, 80, 80, null);
                break;
            }
        }
        g.setColor(Color.ORANGE);
        g.fillRect((int) (positionX - camera.positionX), (int) (positionY - camera.positionY), EnvironmentVariable.PIXEL, EnvironmentVariable.PIXEL);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.up = true;
            case KeyEvent.VK_DOWN -> direction.down = true;
            case KeyEvent.VK_LEFT -> direction.left = true;
            case KeyEvent.VK_RIGHT -> direction.right = true;
            case KeyEvent.VK_Q -> {
                if (++typeWeaponNumber > 2)
                    typeWeaponNumber = 1;
                if (typeWeaponNumber == 1)
                    typeWeapon = TypeWeapon.NORMAL;
                if (typeWeaponNumber == 2)
                    typeWeapon = TypeWeapon.METAL;
                break;
            }
            case KeyEvent.VK_W -> {
                createWeapon(DirectionWeapon.UP);
            }
            case KeyEvent.VK_S -> {
                createWeapon(DirectionWeapon.DOWN);
            }
            case KeyEvent.VK_A -> {
                createWeapon(DirectionWeapon.LEFT);
            }
            case KeyEvent.VK_D -> {
                createWeapon(DirectionWeapon.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.up = false;
            case KeyEvent.VK_DOWN -> direction.down = false;
            case KeyEvent.VK_LEFT -> direction.left = false;
            case KeyEvent.VK_RIGHT -> direction.right = false;
        }
    }

    public Camera getCamera() {
        return camera;
    }
}
