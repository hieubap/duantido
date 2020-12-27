package origin;

import objectgame.Direction;
import objectgame.ObjectGame;
import objectgame.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static origin.EnvironmentConfig.*;

public class Camera implements KeyListener, ObjectGame {
    private int BORDER_DISTANCE = 100;
    private double speed = SPEED_CAMERA;
    public int positionX, positionY;
    public int width, height;
    private Player player;

    public Direction direction;

    public Camera(int positionX, int positionY, int width, int height) { // player = null
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public Camera(int positionX, int positionY) {// player = null
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = WIDTH;
        this.height = HEIGHT;
        direction = new Direction();
    }

    public Camera(int positionX, int positionY, Player player) {
        this(positionX, positionY);
        this.player = player;
    }

    public boolean canDraw(int x, int y) {
        Rectangle rectangle = new Rectangle(positionX, positionY, WIDTH, HEIGHT);
        return rectangle.contains(x, y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

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

    @Override
    public void update() {
        if (direction.up && (player.positionY - positionY) < BORDER_DISTANCE) positionY -= speed;
        if (direction.down && (player.positionY - positionY) > HEIGHT-BORDER_DISTANCE- PIXEL) positionY += speed;
        if (direction.left && (player.positionX - positionX) < BORDER_DISTANCE) positionX -= speed;
        if (direction.right && (player.positionX - positionX) > WIDTH - BORDER_DISTANCE - PIXEL) positionX += speed;
    }

    @Override
    public void draw(Graphics g) {

    }
}
