package objectgame.enemy;

import objectgame.Direction;
import objectgame.ObjectGame;
import origin.Camera;
import primary.EnvironmentConfig;

import java.awt.*;

public class Enemy implements ObjectGame {
    public final double speed = EnvironmentConfig.SPEED_ENEMY;
    public double positionX, positionY;
    public Direction direction;
    public Camera camera;

    public int timeChangeDirection = -1;

    public Enemy(double positionX, double positionY, Camera camera) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = new Direction();
        this.camera = camera;
        update();
    }

    @Override
    public void update() {
        if (timeChangeDirection-- < 0) {
            timeChangeDirection = 100;
            direction.setAllFalse();
            int ran = (int) (Math.random() * 4) % 4;
            if (ran == 0) direction.up = true;
            if (ran == 1) direction.down = true;
            if (ran == 2) direction.left = true;
            if (ran == 3) direction.right = true;
        }

        if (direction.up) positionY -= speed;
        if (direction.down) positionY += speed;
        if (direction.left) positionX -= speed;
        if (direction.right) positionX += speed;
    }

    @Override
    public void draw(Graphics g) {
        if (camera.canDraw((int) positionX, (int) positionY)) {
            g.setColor(Color.RED);
            g.fillRect((int) (positionX - camera.positionX), (int) (positionY - camera.positionY), EnvironmentConfig.PIXEL, EnvironmentConfig.PIXEL);
        }
    }
}
