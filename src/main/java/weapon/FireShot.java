package weapon;

import objectgame.ObjectGame;
import centre.EnvironmentVariable;
import centre.ImageManager;

import java.awt.*;

public class FireShot implements ObjectGame {
    private final int pixel = EnvironmentVariable.PIXEL;
    private double speed = EnvironmentVariable.SPEED_FIRE_SHOT;

    private double positionX;
    private double positionY;
    private DirectionWeapon direction;

    public FireShot(double positionX, double positionY, DirectionWeapon direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
    }

    @Override
    public void update() {

        if (positionX > 200) {
            switch (direction) {
                case UP -> {
                    positionY -= speed;
                    break;
                }
                case DOWN -> {
                    positionY += speed;
                    break;
                }
                case LEFT -> {
                    positionX -= speed;
                    break;
                }
                case RIGHT -> {
                    positionX += speed;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImageManager.fireShotImage, (int) positionX, (int) positionY, pixel*4, pixel*3, null);
    }
}
