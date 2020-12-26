package origin;

import objectgame.Direction;
import objectgame.ObjectGame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener, ObjectGame {
    private double speed = EnvironmentConfig.SPEED_CAMERA;
    public int positionX, positionY;
    public int width,height;

    public Direction direction;

    public Camera(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public Camera(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = EnvironmentConfig.WIDTH;
        this.height = EnvironmentConfig.HEIGHT;
        direction = new Direction();
    }

    public boolean canDraw(int x,int y){
        Rectangle rectangle = new Rectangle(positionX,positionY,EnvironmentConfig.WIDTH,EnvironmentConfig.HEIGHT);
        return rectangle.contains(x,y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.up=true;
            case KeyEvent.VK_DOWN -> direction.down=true;
            case KeyEvent.VK_LEFT -> direction.left=true;
            case KeyEvent.VK_RIGHT -> direction.right=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_UP -> direction.up=false;
            case KeyEvent.VK_DOWN -> direction.down=false;
            case KeyEvent.VK_LEFT -> direction.left=false;
            case KeyEvent.VK_RIGHT -> direction.right=false;
        }
    }

    @Override
    public void update() {
        if (direction.up) positionY-=speed;
        if (direction.down) positionY+=speed;
        if (direction.left) positionX-=speed;
        if (direction.right) positionX+=speed;
    }

    @Override
    public void draw(Graphics g) {

    }
}
