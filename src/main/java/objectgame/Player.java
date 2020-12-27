package objectgame;

import map.Map;
import origin.Camera;
import origin.EnvironmentConfig;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends KeyAdapter implements ObjectGame {
    public double speed = EnvironmentConfig.SPEED_PLAYER;
    public double positionX, positionY;

    private Map map;
    private Camera camera;
    public Direction direction;

    public Player(double positionX, double positionY, Camera camera,Map map) {
        this.positionX = positionX;
        this.positionY = positionY;
        direction = new Direction();
        this.camera = camera;
        this.map = map;
    }
    public Player(double positionX, double positionY,Map map) {
        this.positionX = positionX;
        this.positionY = positionY;
        direction = new Direction();
        this.camera = new Camera(0,0,this);
        this.map = map;
    }


    @Override
    public void update() {
        if (direction.up && map.canMove((int)positionX,(int)(positionY-speed))) positionY -= speed;
        if (direction.down && map.canMove((int)positionX,(int)(positionY+speed))) positionY += speed;
        if (direction.left && map.canMove((int)(positionX-speed),(int)(positionY))) positionX -= speed;
        if (direction.right && map.canMove((int)(positionX+speed),(int)(positionY))) positionX += speed;

    }

    @Override
    public void draw(Graphics g) {
        g.fillRect((int) (positionX - camera.positionX), (int) (positionY - camera.positionY), EnvironmentConfig.PIXEL, EnvironmentConfig.PIXEL);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.up = true;
            case KeyEvent.VK_DOWN -> direction.down = true;
            case KeyEvent.VK_LEFT -> direction.left = true;
            case KeyEvent.VK_RIGHT -> direction.right = true;
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
